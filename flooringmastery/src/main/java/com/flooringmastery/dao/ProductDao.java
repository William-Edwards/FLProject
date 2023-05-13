package com.flooringmastery.dao;

import java.util.List;

import com.flooringmastery.dto.Product;

public interface ProductDao {

    /**
     * Returns the product objects in the products.txt file and return as a list
     *
     *
     * @return list contain all the product objects
     */

    List<Product> getAllProduct();
}
