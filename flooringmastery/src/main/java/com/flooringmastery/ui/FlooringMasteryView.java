package com.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.List;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

public class FlooringMasteryView {
    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayAllBanner() {
        io.print("=== Display All Orders ===");
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            // print all order info
            System.out.println(currentOrder);
        }
    }

    public void displayOrder(Order order) {
        System.out.println(order);
    }

    public Order getNewOrderInfo(List<Product> productList) {
        // prompt user for order info
        String customerName = io.readString("Please enter full name");
        String state = io.readString("Please enter state abbreviation?");

        // display products info
        for (Product currentProduct : productList) {
            // print all product info
            System.out.println(currentProduct);
        }

        // more prompts
        String productType = io.readString("Please enter a product type");
        BigDecimal area = io.readBigDecimal("Please enter a area, minimum order is 100sq ft",
                new BigDecimal("100.00"), new BigDecimal("1000000000.00"));

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        return currentOrder;

    }

    public Order getUpdatedOrderInfo(Order currentOrder) {
        // prompt for info
        String customerName = io.readString("Please enter full name");
        String state = io.readString("Please enter state abbreviation?");
        String productType = io.readString("Please enter a product type");
        BigDecimal area = io.readBigDecimal("Please enter a area, minimum order is 100sq ft",
                new BigDecimal("100.00"), new BigDecimal("1000000000.00"));

        if (!customerName.isBlank()) {
            currentOrder.setCustomerName(customerName);
        }
        if (!state.isBlank()) {
            currentOrder.setState(state);
        }
        if (!productType.isBlank()) {
            currentOrder.setProductType(productType);
        }

        // same implementation for big deciaml, check if blank
        currentOrder.setArea(area);

        return currentOrder;

    }

    public void displayCreateBanner() {
        io.print("=== Create Order ===");
    }

    public String getOrderDate() {
        // must be in future or today

        return io.readString("Please enter the date in the format MMDDYYYY.");
    }

    public String getOrderNumber() {
        return io.readString("Please enter the order number");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public boolean confirmation() {
        String answer = io.readString("Would you like to proceed? Y/N?");
        if (answer.equals("Y")
                || answer.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
