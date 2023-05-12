package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Order> getAllOrder(String orderDate) {

        // read file

        try {
            loadOrder(orderDate);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ArrayList<Order>(orders.values());

    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order editOrder(String orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void loadOrder(String orderDate) throws FileNotFoundException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Orders_" + orderDate + ".txt")));
        } catch (FileNotFoundException e) {
            scanner = new Scanner(new BufferedReader(new FileReader(newOrderFile(orderDate))));
        }

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

        scanner.close();

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
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileName;
    }

}
