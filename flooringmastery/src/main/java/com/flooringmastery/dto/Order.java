package com.flooringmastery.dto;

import java.math.BigDecimal;

public class Order {
    private int orderNumber;
    private String customerName;
    private Tax tax;
    private Product product;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal taxCost;
    private BigDecimal total;

    // Constructor
    public Order(int orderNumber, String customerName, Tax tax, Product product, BigDecimal area) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.tax = tax;
        this.product = product;
        this.area = area;

        /*
         * handling for field calcs
         * 
         * MaterialCost = (Area * CostPerSquareFoot)
         * LaborCost = (Area * LaborCostPerSquareFoot)
         * Tax = (MaterialCost + LaborCost) * (TaxRate/100)
         * 
         * Tax rates are stored as whole numbers
         * Total = (MaterialCost + LaborCost + Tax)
         */

        this.materialCost = area.multiply(product.getCostPerSquareFoot()); // need to check scale(2) for these
        this.laborCost = area.multiply(product.getLaborCostPerSquareFoot());
        BigDecimal subTotal = this.materialCost.add(this.laborCost);
        this.taxCost = subTotal.multiply(tax.getTaxRate().divide(new BigDecimal("100")));
        this.total = subTotal.add(taxCost);
    }

    // Getters and Setters
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
