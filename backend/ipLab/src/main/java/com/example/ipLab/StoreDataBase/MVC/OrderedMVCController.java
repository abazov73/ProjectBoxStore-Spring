package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.DTO.OrderedDTO;
import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.Model.CustomUser;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.StoreDataBase.Service.OrderService;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderedMVCController {
    private final OrderService orderedService;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderedMVCController(OrderService orderedService, ProductService productService, CustomerService customerService){
        this.productService = productService;
        this.customerService = customerService;
        this.orderedService = orderedService;
    }

    @GetMapping
    public String getOrdereds(Model model, @AuthenticationPrincipal CustomUser user) {
        if (user.getRole() == UserRole.USER){
            model.addAttribute("orders",
                    orderedService.getOrdersByCustomerId(user.getUserID()).stream()
                            .map(OrderedDTO::new)
                            .toList());
        }
        else {
            model.addAttribute("orders",
                    orderedService.getAllOrders().stream()
                            .map(OrderedDTO::new)
                            .toList());
        }
        return "order";
    }

    @GetMapping(value = {"/edit/", "/edit/{id}"})
    public String editOrdered(@PathVariable(required = false) Long id,
                              Model model) {
        model.addAttribute("orderDTO", new OrderedDTO());
        model.addAttribute("customers", customerService.getAllCustomers().stream().map(CustomerDTO::new).toList());
        model.addAttribute("products", productService.getAllProductsWithStores().stream().map(ProductDTO::new).toList());
        return "order-edit";
    }

    @PostMapping(value = {"/", "/{id}"})
    public String saveOrdered(@RequestParam(value = "productId") Long productId,
                              @RequestParam(value = "customerId") Long  customerId,
                              @ModelAttribute @Valid OrderedDTO orderedDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ordered-edit";
        }
        orderedService.addOrder(productService.getProduct(productId), customerService.getCustomer(customerId), orderedDto.getQuantity());
        return "redirect:/order";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrdered(@PathVariable Long id) {
        orderedService.deleteOrder(id);
        return "redirect:/order";
    }
}
