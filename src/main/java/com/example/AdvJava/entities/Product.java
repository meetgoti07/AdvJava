package com.example.AdvJava.entities;

import jakarta.persistence.*;

@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private double productPrice;
    private String productDescription;

    public int getproductId() {
        return productId;
    }
    public void setproductId(int productId) {
        this.productId = productId;
    }
    public String getproductName() {
        return productName;
    }
    public void setproductName(String productName) {
        this.productName = productName;
    }
    public double getproductPrice() {
        return productPrice;
    }
    public void setproductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public String getproductDescription() {
        return productDescription;
    }
    public void setproductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", productDescription=" + productDescription
                + "]";
    }

}
