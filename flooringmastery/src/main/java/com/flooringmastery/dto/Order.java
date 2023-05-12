package com.flooringmastery.dto;

import java.math.BigDecimal;

public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost; // rest are calculated
    private BigDecimal laborCost;
    private BigDecimal taxCost;
    private BigDecimal total;

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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setTaxCost(BigDecimal taxCost) {
        this.taxCost = taxCost;
    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    // override the tostring methosd so when a order object is prited its in a nice
    // format

    @Override
    public String toString() {
        return "Order Number: " + orderNumber + "\n"
                + "Customer Name: " + customerName + "\n"
                + "State: " + state + "\n"
                + "Tax Rate: " + taxRate + "\n"
                + "Product Type: " + productType + "\n"
                + "Area: " + area + "\n"
                + "Cost Per Square Foot: " + costPerSquareFoot + "\n"
                + "Labor Cost Per Square Foot: " + laborCostPerSquareFoot + "\n"
                + "Material Cost: " + materialCost + "\n"
                + "Labor Cost: " + laborCost + "\n"
                + "Tax: " + taxCost + "\n"
                + "Total: " + total + "\n";
    }

}
