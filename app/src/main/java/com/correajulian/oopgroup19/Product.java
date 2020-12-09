package com.correajulian.oopgroup19;

import java.io.Serializable;

public class Product implements Serializable {
    public Product(Product p) {
        this.product_name = p.getName();
        this.price = p.getPrice();
        this.imgpath = p.getPath();
        this.seller_name = p.getSeller();
    }
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
    public String getSeller() {
        return this.seller_name;
    }
    public String getPath() {
        return this.imgpath;
    }
    public String toString() {
        Float f = this.price;

        return this.product_name + "," + f.toString() + "," + this.imgpath + "," + this.seller_name;
    }
    protected final String product_name;
    protected final float price;
    protected final String imgpath;
    protected final String seller_name;
    //protected int quantity;
}
