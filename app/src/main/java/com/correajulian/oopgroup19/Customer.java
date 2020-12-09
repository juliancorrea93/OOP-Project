package com.correajulian.oopgroup19;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public Customer(String[] user) {
        super(user);
        cart = new ShoppingCart();
    }
    public Customer(User user) {
        super(user);
        cart = new ShoppingCart();
    }
    public float getBill() {
        float sum = cart.getTotal();
        return sum;
    }
    public void addProductToCart(Product product, int quantity) {
        cart.addNewItem(product, quantity);
    }
    public String getCartInfo() {
        return cart.getItemList();
    }
    public ShoppingCart getCart() {
        return cart;
    }
    public void clearCart() {
        cart.clearCart();
    }
    private ShoppingCart cart;
}
