package com.flooringmastery.service;

import java.util.List;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

public interface FlooringMasteryServiceLayer {
    void createOrder(String orderDate, Order order);

    List<Order> getAllOrder(String orderDate);

    Order editOrder(Order order);

    Order getOrder(String orderDate, int orderNumber);

    Order removeOrder(String orderDate, int orderNumber);

    List<Product> getAllProducts();

    boolean isTodayOrFuture(String orderDate);

    boolean validateOrderData(Order order);

    Order calculateOrderProperties(Order order);

    int newOrderNumber(String orderDate);

}
