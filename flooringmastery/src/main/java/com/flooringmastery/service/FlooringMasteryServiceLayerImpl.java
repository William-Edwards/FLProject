package com.flooringmastery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private OrderDao orderDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    public FlooringMasteryServiceLayerImpl(OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public void createOrder(String orderDate, Order order) {
        // validate info based on specs
        validateOrderData(order);

        // calulate rest of the order properties, order num max+1
        order = calculateOrderProperties(order);

        // passed everything
        orderDao.addOrder(orderDate, order);

    }

    private Order calculateOrderProperties(Order order) {
        return null;
    }

    @Override
    public List<Order> getAllOrder(String orderDate) {
        return orderDao.getAllOrder(orderDate);
    }

    @Override
    public Order editOrder(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        return orderDao.getOrder(orderDate, orderNumber);
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) {
        return orderDao.removeOrder(orderDate, orderNumber);
    }

    private boolean validateOrderData(Order order) {
        // validate name
        String name = order.getCustomerName();
        if (name.matches("^[a-zA-Z0-9.,]+$") == false || name.isBlank() == true) {
            System.out.println("name is invalid");
            return false;
        }

        Set<String> taxStateAbbreviation = taxDao.getAllTax().keySet();
        String state = order.getState();
        if (taxStateAbbreviation.contains(state) == false) {
            System.out.println("state is invalid");
            return false;
        }

        // get list of product names
        List<Product> products = productDao.getAllProduct();
        List<String> productNames = new ArrayList<>();

        for (Product currentProduct : products) {
            productNames.add(currentProduct.getProductType());
        }

        // validate product names

        if (productNames.contains(order.getProductType()) == false || order.getProductType().isBlank() == true) {
            System.out.println("product type is invlaid");
        }

        return true;

    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProduct();
    }

}
