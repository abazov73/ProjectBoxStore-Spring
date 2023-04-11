package com.example.ipLab;

import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.StoreDataBase.Service.OrderService;
import com.example.ipLab.StoreDataBase.Service.ProductService;
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
    @Autowired
    ProductService productService;
    @Test
    void testStore(){
        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());
        Assertions.assertEquals("example", storeService.getStore(store.getId()).getStoreName());
        storeService.updateStore(store.getId(), "newName");
        Assertions.assertEquals("newName", storeService.getStore(store.getId()).getStoreName());
        Assertions.assertEquals("newName", storeService.deleteStore(store.getId()).getStoreName());
        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testCustomer(){
        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());
        Assertions.assertEquals("2", customerService.getCustomer(c.getId()).getFirstName());
        Assertions.assertEquals("1", customerService.updateCustomer(c.getId(), c.getLastName(), "1", c.getMiddleName()).getFirstName());
        Assertions.assertEquals("1", customerService.deleteCustomer(c.getId()).getFirstName());

        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testProduct(){
        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = productService.addProduct("product");
        Assertions.assertEquals("product", p.getName());

        Assertions.assertEquals("product", storeService.addProduct(store.getId(), p.getId()).getName());
        Assertions.assertEquals("product", storeService.getProductFromStore(p.getId(), store.getId()).getName());
        Assertions.assertEquals("productUpd", productService.updateProduct(p.getId(), "productUpd").getName());
        Assertions.assertEquals("productUpd", storeService.deleteProductFromStore(store.getId(), p.getId()).getName());

        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }

    @Test
    void testOrder(){
        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = productService.addProduct("product");
        storeService.addProduct(store.getId(), p.getId());
        Assertions.assertEquals("product", p.getName());

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());

        Ordered order = orderService.addOrder(store, p, c, 5);
        Assertions.assertEquals("5", Integer.toString(order.getQuantity()));
        Assertions.assertEquals("5", Integer.toString(orderService.getOrder(order.getId()).getQuantity()));

        Assertions.assertEquals("6", Integer.toString(orderService.updateOrder(order.getId(), 6).getQuantity()));
        Assertions.assertEquals("6", Integer.toString(orderService.deleteOrder(order.getId()).getQuantity()));

        productService.deleteAllProducts();
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        storeService.deleteAllStores();
    }
}
