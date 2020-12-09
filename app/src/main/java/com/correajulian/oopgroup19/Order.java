package com.correajulian.oopgroup19;

public class Order extends Product{
    public Order(Product p, int qty) {
        super(p);
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public float totalPrice() {
        return getPrice() * qty;
    }

    public void setQty(int newqty) {
        qty = newqty;
    }

    @Override
    public String toString() {
        return product_name + ": " + qty + ". Individual price: " + price + ", Total price: " + totalPrice() + ". Sold by " + seller_name + '.';
    }
    private int qty;
}
