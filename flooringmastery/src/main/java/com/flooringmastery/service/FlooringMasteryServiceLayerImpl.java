package com.flooringmastery.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
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
        // passed everything
        orderDao.addOrder(orderDate, order);

    }

    @Override
    public Order calculateOrderProperties(Order order) {
        // calcs rest of order properties

        // gets taxrate from the state abbrev property in the order object
        BigDecimal taxRate = taxDao.getAllTax().get(order.getState()).getTaxRate();

        String productType = order.getProductType();
        List<Product> productList = productDao.getAllProduct();

        // stream to get correct product object, 5 marks pls eugene!!
        Product currentProduct = productList.stream()
                .filter(product -> product.getProductType().equals(productType)).findAny().orElse(null);

        BigDecimal laborCostPerSquareFoot = currentProduct.getLaborCostPerSquareFoot();
        BigDecimal costPerSquareFoot = currentProduct.getCostPerSquareFoot();

        // calc last properties
        BigDecimal materialCost = order.getArea().multiply(costPerSquareFoot).setScale(2, RoundingMode.FLOOR);
        BigDecimal laborCost = order.getArea().multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.FLOOR);

        BigDecimal taxRateNew = taxRate.divide(new BigDecimal(100)).setScale(2, RoundingMode.FLOOR);
        BigDecimal taxCost = (materialCost.add(laborCost)).multiply(taxRateNew).setScale(2, RoundingMode.FLOOR);

        BigDecimal total = materialCost.add(laborCost).add(taxCost).setScale(2, RoundingMode.FLOOR);

        // set all values

        order.setTaxRate(taxRate);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTaxCost(taxCost);
        order.setTotal(total);

        return order;
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

    @Override
    public boolean validateOrderData(Order order) {
        // validate name
        String name = order.getCustomerName();
        if (!name.matches("^[a-zA-Z0-9.,]+$") || name.isBlank()) {
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
    public boolean isTodayOrFuture(String orderDate) {
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        // Convert the input string to a LocalDate
        LocalDate inputDate = LocalDate.parse(orderDate, formatter);

        // Get today's date
        LocalDate today = LocalDate.now();

        // Check if the input date is today or in the future
        return !inputDate.isBefore(today);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProduct();
    }

    @Override
    public int newOrderNumber(String orderDate) {
        // get list of orders
        List<Order> orderList = orderDao.getAllOrder(orderDate);

        OptionalInt largestOrderNumber = orderList.stream().mapToInt(Order::getOrderNumber).max();

        if (largestOrderNumber.isPresent()) {
            return largestOrderNumber.getAsInt() + 1;
        } else {
            return 1;
        }
    }

}
