package com.flooringmastery.service;

import java.util.List;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dto.Order;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private OrderDao dao;

    public FlooringMasteryServiceLayerImpl(OrderDao dao) {
        this.dao = dao;
    }

    @Override
    public void createOrder(String orderDate, Order order) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Order> getAllOrder(String orderDate) {
        return dao.getAllOrder(orderDate);
    }

    @Override
    public Order editOrder(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) {
        // TODO Auto-generated method stub
        return null;
    }

}
