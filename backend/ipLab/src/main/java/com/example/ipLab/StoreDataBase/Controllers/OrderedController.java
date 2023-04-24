package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.OrderedDTO;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.StoreDataBase.Service.OrderService;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OrderedController {
    private final OrderService orderedService;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderedController(OrderService orderedService, ProductService productService, CustomerService customerService){
        this.productService = productService;
        this.customerService = customerService;
        this.orderedService = orderedService;
    }

    @GetMapping("/{id}")
    public OrderedDTO getOrdered(@PathVariable Long id){
        return new OrderedDTO(orderedService.getOrder(id));
    }

    @GetMapping
    public List<OrderedDTO> getOrdereds(){
        return  orderedService.getAllOrders().stream()
                .map(OrderedDTO::new)
                .toList();
    }

    @PostMapping
    public OrderedDTO createOrdered(@RequestParam("productId") Long productId,
                                    @RequestParam("customerId") Long customerId,
                                    @RequestParam("quantity") Integer quantity){
        final Ordered ordered = orderedService.addOrder(productService.getProduct(productId), customerService.getCustomer(customerId), quantity);
        return new OrderedDTO(ordered);
    }

    @PutMapping("/{id}")
    public OrderedDTO updateOrdered(@RequestParam("quantity") Integer quantity,
                                    @PathVariable Long id){
        return new OrderedDTO(orderedService.updateOrder(id, quantity));
    }

    @DeleteMapping("/{id}")
    public OrderedDTO deleteOrdered(@PathVariable Long id){
        return new OrderedDTO(orderedService.deleteOrder(id));
    }

    @DeleteMapping()
    public void deleteAllOrdereds(){
        orderedService.deleteAllOrders();
    }
}
