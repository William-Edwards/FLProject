package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flooringmastery.dto.Product;

public class ProductDaoFileImpl implements ProductDao {

    public static final String PRODUCTS_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<Product> getAllProduct() {
        loadProducts();
        return new ArrayList<Product>(products.values());
    }

    private void loadProducts() {
        Scanner scanner = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));

            String currentLine;

            Product currentProduct;

            scanner.nextLine();

            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();
                currentProduct = unmarshallProduct(currentLine);

                products.put(currentProduct.getProductType(), currentProduct);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Products.txt not found");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }

    private Product unmarshallProduct(String productAsString) {

        String[] productTokens = productAsString.split(DELIMITER);

        Product productFromFile = new Product();

        // set state abb
        productFromFile.setProductType(productTokens[0]);

        // set state name
        productFromFile.setCostPerSquareFoot(new BigDecimal(productTokens[1]));

        // set state Product rate
        productFromFile.setLaborCostPerSquareFoot(new BigDecimal(productTokens[2]));

        return productFromFile;

    }
}
