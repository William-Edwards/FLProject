package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flooringmastery.dto.Order;

public class OrderDaoFileImpl implements OrderDao {

    public static final String DELIMITER = ",";
    public static final String HEADERS = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    private Map<String, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(String orderDate, Order order) {
        // check if orderdate exists, if not create new
        File file = new File("Orders_" + orderDate + ".txt");
        if (file.exists()) {
            loadOrder(orderDate);
        } else {
            newOrderFile(orderDate);
            loadOrder(orderDate);
        }

        // int to string
        String orderNumberString = String.valueOf(order.getOrderNumber());

        // put into hashmap
        Order newOrder = orders.put(orderNumberString, order);

        writeOrder(orderDate);

        return newOrder;
    }

    @Override
    public List<Order> getAllOrder(String orderDate) {

        // read file get order list by date clear hashmap

        loadOrder(orderDate);
        List<Order> orderList = new ArrayList<Order>(orders.values());
        orders.clear();
        return orderList;

    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {

        // read file and get order by date and number then clear hashmap

        loadOrder(orderDate);
        Order order = orders.get(String.valueOf(orderNumber));
        orders.clear();
        return order;
    }

    @Override
    public Order editOrder(String orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) {
        loadOrder(orderDate);
        String orderNumberString = String.valueOf(orderNumber);
        Order removedOrder = orders.remove(orderNumberString);
        writeOrder(orderDate);
        return removedOrder;
    }

    private void loadOrder(String orderDate) {
        Scanner scanner = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Orders_" + orderDate + ".txt")));

            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentOder holds the most recent student unmarshalled
            Order currentOrder;

            scanner.nextLine();

            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into a order
                currentOrder = unmarshallOrder(currentLine);

                // convert int to string

                String orderNumberString = String.valueOf(currentOrder.getOrderNumber());

                // put order in hashmap with order number as the key

                orders.put(orderNumberString, currentOrder);
            }
        } catch (FileNotFoundException e) {

            // creates new base file
            // scanner = new Scanner(new BufferedReader(new
            // FileReader(newOrderFile(orderDate))));
            System.out.println(" Error file not found");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }

    private Order unmarshallOrder(String orderAsText) {
        // read from file to order object

        String[] orderTokens = orderAsText.split(DELIMITER);

        Order orderFromFile = new Order();

        // Setting OrderNumber
        orderFromFile.setOrderNumber(Integer.parseInt(orderTokens[0]));

        // Setting CustomerName
        orderFromFile.setCustomerName(orderTokens[1]);

        // Setting State
        orderFromFile.setState(orderTokens[2]);

        // Setting TaxRate
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));

        // Setting ProductType
        orderFromFile.setProductType(orderTokens[4]);

        // Setting Area
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));

        // Setting CostPerSquareFoot
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));

        // Setting LaborCostPerSquareFoot
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));

        // Setting MaterialCost
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));

        // Setting LaborCost
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));

        // Setting Tax
        orderFromFile.setTaxCost(new BigDecimal(orderTokens[10]));

        // Setting Total
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));

        return orderFromFile;
    }

    private String newOrderFile(String orderDate) {
        // create new order file with date

        String fileName = "Orders_" + orderDate + ".txt";

        try {
            File file = new File(fileName);

            if (file.createNewFile()) {
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println(HEADERS);
                    System.out.println("New file created");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred. File creation failed");
            e.printStackTrace();
        }

        return fileName;
    }

    private void writeOrder(String orderDate) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("Orders_" + orderDate + ".txt"));
            out.println(HEADERS);

            String orderAsText;
            List<Order> orderList = new ArrayList<Order>(orders.values());
            for (Order currentOrder : orderList) {
                // turn order to string
                orderAsText = marshallOrder(currentOrder);
                // write order to file
                out.println(orderAsText);
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Could not save student data");
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    private String marshallOrder(Order order) {

        // Convert our Order object into a text string for file writing
        String orderAsText = order.getOrderNumber() + DELIMITER;
        orderAsText += order.getCustomerName() + DELIMITER;
        orderAsText += order.getState() + DELIMITER;
        orderAsText += order.getTaxRate() + DELIMITER;
        orderAsText += order.getProductType() + DELIMITER;
        orderAsText += order.getArea() + DELIMITER;
        orderAsText += order.getCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getLaborCostPerSquareFoot() + DELIMITER;
        orderAsText += order.getMaterialCost() + DELIMITER;
        orderAsText += order.getLaborCost() + DELIMITER;
        orderAsText += order.getTaxCost() + DELIMITER;
        orderAsText += order.getTotal();

        // We have now turned an order to text!
        return orderAsText;
    }

}
