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
                        editOrder();
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
            Order currentOrder = view.getNewOrderInfo(service.getAllProducts());

            // check date
            if (service.isTodayOrFuture(orderDate) && service.validateOrderData(currentOrder)) {

                // set order number

                currentOrder.setOrderNumber(service.newOrderNumber(orderDate));

                // calc rest of order properties
                service.calculateOrderProperties(currentOrder);

                // print object
                view.displayOrder(currentOrder);
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
            } else {
                hasErrors = true;
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

    private void editOrder() {

        // get order number and date
        String orderDate = view.getOrderDate();
        String orderNumberString = view.getOrderNumber();

        // check date

        // get the current order if it exists
        Order currentOrder = service.getOrder(orderDate, Integer.parseInt(orderNumberString));

        // update order with new info
        Order updatedOrder = view.getUpdatedOrderInfo(currentOrder);

        if (service.validateOrderData(updatedOrder)) {
            service.calculateOrderProperties(updatedOrder);

            view.displayOrder(updatedOrder);

            if (view.confirmation()) {
                service.editOrder(orderDate, updatedOrder);
            }
        }

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
