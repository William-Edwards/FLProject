package com.flooringmastery.controller;

import java.util.List;

import com.flooringmastery.dto.Order;
import com.flooringmastery.service.FlooringMasteryServiceLayer;
import com.flooringmastery.ui.FlooringMasteryView;

public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        viewOrders();
                        break;
                    case 2:
                        createOrder();
                        break;
                    case 3:
                        System.out.println("EDIT ORDER");
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        System.out.println("EXPORT DATA");
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (NumberFormatException e) {
            // need a custom exception
            System.out.println("error in menu selection");
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    // view orders based on date needs validation
    private void viewOrders() {
        String orderDate = view.getOrderDate();
        view.displayAllBanner();
        List<Order> orderList = service.getAllOrder(orderDate);
        view.displayOrderList(orderList);
    }

    private void createOrder() {
        view.displayCreateBanner();
        boolean hasErrors = false;
        do {
            // get and store order date
            String orderDate = view.getOrderDate();
            // get some new order info and return order object
            Order currentOrder = view.getNewOrderInfo();
            // display product list and get product type and area, return order object
            currentOrder = view.getProuctAreaInfo(currentOrder, service.getAllProducts());

            // print object

            // confirmation to proceed
            if (view.confirmation()) {
                try {
                    service.createOrder(orderDate, currentOrder);
                    // success
                    hasErrors = false;

                } catch (Exception e) {
                    hasErrors = true;
                    System.out.println("error creating order");
                }
            }
        } while (hasErrors);
    }

    private void removeOrder() {
        // get and store order date
        String orderDate = view.getOrderDate();

        // get and store order number
        int orderNumber = Integer.parseInt(view.getOrderNumber());

        // display and confirmation
        view.displayOrder(service.getOrder(orderDate, orderNumber));

        if (view.confirmation()) {
            // remove order
            service.removeOrder(orderDate, orderNumber);
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
