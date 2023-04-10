package com.example.ipLab;

import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.StoreDataBase.Service.OrderService;
import com.example.ipLab.StoreDataBase.Service.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaTests {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    StoreService storeService;
    @Test
    void testStore(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());
        Assertions.assertEquals("example", storeService.getStore(store.getId()).getStoreName());
        storeService.updateStore(store.getId(), "newName");
        Assertions.assertEquals("newName", storeService.getStore(store.getId()).getStoreName());
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testCustomer(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());
        Assertions.assertEquals("2", customerService.getCustomer(c.getId()).getFirstName());

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testProduct(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = storeService.addProduct(store.getId(), "product");
        Assertions.assertEquals("product", p.getName());

        Assertions.assertEquals("product", storeService.getProduct(p.getId(), store.getId()).getName());

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testOrder(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = storeService.addProduct(store.getId(), "product");
        Assertions.assertEquals("product", p.getName());

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());

        Ordered order = orderService.addOrder(store, p, c, 5);
        Assertions.assertEquals("5", Integer.toString(order.getQuantity()));
        Assertions.assertEquals("5", Integer.toString(orderService.getOrder(order.getId()).getQuantity()));

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }
}
