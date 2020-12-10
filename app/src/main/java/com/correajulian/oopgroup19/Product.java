package com.correajulian.oopgroup19;

import java.io.Serializable;

public class Product implements Serializable {
    /**
     * creates a new product
     * @param p existing product
     */
    public Product(Product p) {
        this.product_name = p.getName();
        this.price = p.getPrice();
        this.imgpath = p.getPath();
        this.seller_name = p.getSeller();
    }
    /**
     * creates a nwe product
     * @param name name of product
     * @param value cost of product
     * @param path image path of product
     * @param seller which seller sells this item
     * @param quantity how many of the poducts are available
     */
    public Product(String name, float value, String path, String seller, int quantity) {
        this.product_name = name;
        this.price = value;
        this.imgpath = path;
        this.seller_name = seller;
        this.quantity = quantity;
    }
    /**
     * @return product price
     */
    public float getPrice() {
        return this.price;
    }
    /**
     * @return product name
     */
    public String getName() {
        return this.product_name;
    }
    /**
     * @return product seller
     */
    public String getSeller() {
        return this.seller_name;
    }
    /**
     * @return image path
     */
    public String getPath() {
        return this.imgpath;
    }
    /**
     * @return number of product in stock
     */
    public int getQuantity() {return this.quantity;}
    /**
     * @return string representation of product
     */
    public String toString() {
        Float f = this.price;
        Integer i = this.quantity;

        return this.product_name + "," + f.toString() + "," + this.imgpath + "," + this.seller_name + ","+i.toString();
    }

    /**
     * sets the new available quantity
     * @param qty quantity amount
     */
    public void setQuantity(int qty) {quantity = qty;}

    /**
     * New equals method to check if products are the same
     * @param p Product to compare against this
     * @return true or false depending on equivalence
     */
    public boolean equals(Product p) {
        return product_name.equals(p.product_name) && seller_name.equals(p.seller_name);
    }
    protected final String product_name;
    protected final float price;
    protected final String imgpath;
    protected final String seller_name;
    protected int quantity;
}
