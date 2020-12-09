package com.correajulian.oopgroup19;

public class Product {
    public Product(String name, float value, String path, String seller) {
        this.product_name = name;
        this.price = value;
        this.imgpath = path;
        this.seller_name = seller;
    }
    public float getPrice() {
        return this.price;
    }
    public String getName() {
        return this.product_name;
    }
    public String toString() {
        Float f = (Float) this.price;

        return this.product_name + "," + f.toString() + "," + this.imgpath + "," + this.seller_name;
    }
    private String product_name;
    private float price;
    private String imgpath;
    private String seller_name;
    private int quantity;
}
