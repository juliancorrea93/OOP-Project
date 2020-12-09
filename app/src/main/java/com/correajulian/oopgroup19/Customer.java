package com.correajulian.oopgroup19;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public Customer(String[] user) {
        super(user);
        cart = new ShoppingCart();
    }
    public void checkOut() {
        float sum = cart.getTotal();
        //TODO: get abstract credit card return true to push transaction through
    }
    public void addProductToCart(Product product, int quantity) {
        cart.addNewItem(product, quantity);
    }
    private ShoppingCart cart;
}
