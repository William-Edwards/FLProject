package com.flooringmastery;

import com.flooringmastery.controller.FlooringMasteryController;
import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.OrderDaoFileImpl;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.ProductDaoFileImpl;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.dao.TaxDaoFileImpl;
import com.flooringmastery.service.FlooringMasteryServiceLayer;
import com.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.flooringmastery.ui.FlooringMasteryView;
import com.flooringmastery.ui.UserIO;
import com.flooringmastery.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        OrderDao MyOrderDao = new OrderDaoFileImpl();
        ProductDao myProductDao = new ProductDaoFileImpl();
        TaxDao myTaxDao = new TaxDaoFileImpl();
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(MyOrderDao, myProductDao, myTaxDao);
        FlooringMasteryController controller = new FlooringMasteryController(myView, myService);
        controller.run();
    }
}
