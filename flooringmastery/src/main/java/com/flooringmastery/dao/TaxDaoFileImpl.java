package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.flooringmastery.dto.Tax;

public class TaxDaoFileImpl implements TaxDao {

    public static final String TAXES_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    private Map<String, Tax> taxes = new HashMap<>();

    @Override
    public Map<String, Tax> getAllTax() {
        loadTaxes();
        return taxes;
    }

    private void loadTaxes() {
        Scanner scanner = null;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAXES_FILE)));

            String currentLine;

            Tax currentTax;

            scanner.nextLine();

            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();
                currentTax = unmarshallTax(currentLine);

                taxes.put(currentTax.getStateAbbreviation(), currentTax);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Taxes.txt not found");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }

    private Tax unmarshallTax(String taxAsString) {

        String[] taxTokens = taxAsString.split(DELIMITER);

        Tax taxFromFile = new Tax();

        // set state abb
        taxFromFile.setStateAbbreviation(taxTokens[0]);

        // set state name
        taxFromFile.setStateName(taxTokens[1]);

        // set state tax rate
        taxFromFile.setTaxRate(new BigDecimal(taxTokens[2]));

        return taxFromFile;

    }

}
