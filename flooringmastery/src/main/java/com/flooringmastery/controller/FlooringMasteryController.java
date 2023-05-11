package com.flooringmastery.controller;

import com.flooringmastery.ui.FlooringMasteryView;

public class FlooringMasteryController {
    private FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryView view) {
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        System.out.println("LIST ORDER");
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

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
