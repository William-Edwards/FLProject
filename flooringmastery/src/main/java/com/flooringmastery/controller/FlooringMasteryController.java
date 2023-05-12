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
                        System.out.println("ADD ORDER");
                        break;
                    case 3:
                        System.out.println("EDIT ORDER");
                        break;
                    case 4:
                        System.out.println("REMOVE ORDER");
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
        view.displayDisplayAllBanner();
        List<Order> orderList = service.getAllOrder(orderDate);
        view.displayOrderList(orderList);

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
