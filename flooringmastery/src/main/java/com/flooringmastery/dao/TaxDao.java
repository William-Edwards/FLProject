package com.flooringmastery.dao;

import java.util.Map;

import com.flooringmastery.dto.Tax;

public interface TaxDao {

    /**
     * Returns the tax objects in the taxes.txt file and return as a map
     *
     *
     * @return map contain all the tax objects
     */

    Map<String, Tax> getAllTax();
}
