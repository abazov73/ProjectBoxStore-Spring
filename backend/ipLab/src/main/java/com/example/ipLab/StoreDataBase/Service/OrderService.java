package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ordered addOrder(Store store, Product product, Customer customer, int quantity){
        final Ordered order = new Ordered(store, product, customer, quantity);
        order.getStore().getOrders().add(order);
        order.getCustomer().getOrders().add(order);
        order.getProduct().getOrders().add(order);
        em.persist(order);
        return order;
    }

    @Transactional()
    public Ordered getOrder(Long id){
        Ordered order = em.find(Ordered.class, id);
        if (order == null){
            throw new EntityNotFoundException(String.format("Ordered with id = %s isn't found", id));
        }
        return order;
    }

    @Transactional
    public List<Ordered> getAllOrders(){
        return em.createQuery("get p from Ordered p", Ordered.class).getResultList();
    }

    @Transactional
    public Ordered updateOrder(Long id, int quantity){
        final Ordered order = getOrder(id);
        order.setQuantity(quantity);
        return em.merge(order);
    }

    @Transactional
    public Ordered deleteOrder(Long id){
        final Ordered order = getOrder(id);
        order.getStore().getOrders().remove(order);
        order.getCustomer().getOrders().remove(order);
        em.remove(order);
        return order;
    }
    @Transactional
    public void deleteAllOrders(){
        em.createQuery("delete from Ordered");
    }

    //product section
    @Transactional()
    public Product getProduct(Long productId, Long orderId){
        Ordered order = em.find(Ordered.class, orderId);
        if (order != null) {
            return order.getProduct();
        }
        else throw new EntityNotFoundException(String.format("Product with id = %s isn't found", productId));
    }

    @Transactional
    public Product updateProduct(Long orderId, Long productId, String productName){
        if (!StringUtils.hasText(productName)){
            throw new IllegalArgumentException("Product name is null or empty");
        }
        final Product product = getProduct(productId, orderId);
        if (product == null){
            throw new EntityNotFoundException(String.format("Product with id = %s isn't found", productId));
        }
        product.setName(productName);
        return em.merge(product);
    }

    @Transactional
    public Product deleteProduct(Long orderId, Long productId){
        final Product product = getProduct(productId, orderId);
        if (product == null){
            throw new EntityNotFoundException(String.format("Product with id = %s isn't found", productId));
        }
        var order = product.getOrders().stream().filter(or -> or.getId() == orderId).findFirst();
        if (order.isPresent()) order.get().setProduct(null);
        else throw new EntityNotFoundException(String.format("Order with id = %s isn't found", orderId));
        return product;
    }
}
