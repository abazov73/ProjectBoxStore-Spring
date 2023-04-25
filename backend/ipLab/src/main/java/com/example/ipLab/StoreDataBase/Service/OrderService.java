package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Exceptions.OrderedNotFoundException;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Repositories.OrderedRepository;
import com.example.ipLab.StoreDataBase.util.validation.ValidatorUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderService {
    private final OrderedRepository orderedRepository;
    private final ValidatorUtil validatorUtil;

    public OrderService(OrderedRepository orderedRepository,
                        ValidatorUtil validatorUtil,
                        ProductService productService){
        this.productService = productService;
        this.orderedRepository = orderedRepository;
        this.validatorUtil = validatorUtil;
    }

    private ProductService productService;

    @Transactional
    public Ordered addOrder(Product product, Customer customer, int quantity){
        final Ordered order = new Ordered(quantity);
        validatorUtil.validate(order);
        product.AddOrdered(order);
        customer.AddOrdered(order);
        orderedRepository.save(order);
        return order;
    }

    @Transactional()
    public Ordered getOrder(Long id){
        return orderedRepository.findById(id).orElseThrow(() -> new OrderedNotFoundException(id));
    }

    @Transactional
    public List<Ordered> getAllOrders(){
        return orderedRepository.findAll();
    }

    @Transactional
    public Ordered updateOrder(Long id, int quantity){
        final Ordered order = getOrder(id);
        order.setQuantity(quantity);
        validatorUtil.validate(order);
        return orderedRepository.save(order);
    }

    @Transactional
    public Ordered deleteOrder(Long id){
        final Ordered order = getOrder(id);
        order.getCustomer().getOrders().remove(order);
        orderedRepository.delete(order);
        return order;
    }
    @Transactional
    public void deleteAllOrders(){
        //for (var order:
        //     getAllOrders()) {
        //    order.removeProduct();
        //    order.removeCustomer();
        //}
        orderedRepository.deleteAll();
    }

    //product section
    @Transactional()
    public Product getProduct(Long orderId){
        Ordered order = getOrder(orderId);
        return order.getProduct();
    }
}
