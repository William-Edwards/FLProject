package com.flooringmastery;

import com.flooringmastery.controller.FlooringMasteryController;
import com.flooringmastery.ui.FlooringMasteryView;
import com.flooringmastery.ui.UserIO;
import com.flooringmastery.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        FlooringMasteryController controller = new FlooringMasteryController(myView);
        controller.run();
    }
}
