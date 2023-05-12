package com.flooringmastery.ui;

import java.util.List;

import com.flooringmastery.dto.Order;

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

    public void displayDisplayAllBanner() {
        io.print("=== Display All Orders ===");
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            // print all order info
            System.out.println(currentOrder);
        }
    }

    public String getOrderDate() {
        return io.readString("Please enter the date in the format MMDDYYYY.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
}
