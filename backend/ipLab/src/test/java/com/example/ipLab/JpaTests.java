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

import java.util.ArrayList;
import java.util.List;

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
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());
        Assertions.assertEquals("example", storeService.getStore(store.getId()).getStoreName());
        storeService.updateStore(store.getId(), "newName");
        Assertions.assertEquals("newName", storeService.getStore(store.getId()).getStoreName());
        Assertions.assertEquals("newName", storeService.deleteStore(store.getId()).getStoreName());

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();
    }

    @Test
    void testCustomer(){
        orderService.deleteAllOrders();
        var list = orderService.getAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());
        Assertions.assertEquals("2", customerService.getCustomer(c.getId()).getFirstName());
        Assertions.assertEquals("1", customerService.updateCustomer(c.getId(), c.getLastName(), "1", c.getMiddleName()).getFirstName());
        Assertions.assertEquals("1", customerService.deleteCustomer(c.getId()).getFirstName());

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();
    }

    @Test
    void testProduct(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = productService.addProduct("product");
        Assertions.assertEquals("product", p.getName());

        Assertions.assertEquals("product", storeService.addProduct(store.getId(), p.getId()).getName());
        Assertions.assertEquals("product", storeService.getProductFromStore(p.getId(), store.getId()).getName());

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();
    }

    @Test
    void testOrder(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();

        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p = productService.addProduct("product");
        storeService.addProduct(store.getId(), p.getId());
        Assertions.assertEquals("product", p.getName());

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());

        Ordered order = orderService.addOrder(p, c, 5);
        Assertions.assertEquals("5", Integer.toString(order.getQuantity()));
        Assertions.assertEquals("5", Integer.toString(orderService.getOrder(order.getId()).getQuantity()));

        Assertions.assertEquals("6", Integer.toString(orderService.updateOrder(order.getId(), 6).getQuantity()));
        Assertions.assertEquals("6", Integer.toString(orderService.deleteOrder(order.getId()).getQuantity()));

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();
    }
    @Test
    void FilterOrderTest(){
        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();


        Store store = storeService.addStore("example");
        Assertions.assertEquals("example", store.getStoreName());

        Product p1 = productService.addProduct("product");
        Product p2 = productService.addProduct("product2");
        storeService.addProduct(store.getId(), p1.getId());
        Assertions.assertEquals("product", p1.getName());

        storeService.addProduct(store.getId(), p2.getId());
        Assertions.assertEquals("product2", p2.getName());

        Customer c = customerService.addCustomer("1", "2", "3");
        Assertions.assertEquals("2", c.getFirstName());

        Ordered order1 = orderService.addOrder(p1, c, 0);
        Ordered order2 = orderService.addOrder(p2, c, 6);
        Ordered order3 = orderService.addOrder(p1, c, 2);
        Ordered order4 = orderService.addOrder(p2, c, 2);
        Ordered order5 = orderService.addOrder(p1, c, 3);
        //List<Ordered> expectedResult = new ArrayList<>();
        //expectedResult.add(order3);
        //expectedResult.add(order5);
        //orderService.getAllOrders();
        //Assertions.assertEquals(expectedResult, orderService.getOrdersWithProduct(p1.getId(), 1, 5));

        orderService.deleteAllOrders();
        customerService.deleteAllCustomers();
        productService.deleteAllProducts();
        storeService.deleteAllStores();
    }
}
