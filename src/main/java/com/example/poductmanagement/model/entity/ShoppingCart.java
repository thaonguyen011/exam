package com.example.poductmanagement.model.entity;

public class ShoppingCart {
    private int id;
    private int productID;
    private int quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public ShoppingCart(int id, int productID, int quantity) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
