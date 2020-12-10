package com.correajulian.oopgroup19;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    /**
     * creates a new Customer
     * @param user string array with username, password, and account type
     */
    public Customer(String[] user) {
        super(user);
        cart = new ShoppingCart();
    }
    /**
     * creates a new customer
     * @param user an existing User instance
     */
    public Customer(User user) {
        super(user);
        cart = new ShoppingCart();
    }
    /**
     * @return total of the products in the cart
     */
    public float getBill() {
        float sum = cart.getTotal();
        return sum;
    }
    /**
     * @param product product to be added to the cart
     * @param quantity how many of the product to be added
     */
    public void addProductToCart(Product product, int quantity) {
        cart.addNewItem(product, quantity);
    }
    /**
     * @return string formatting of the cart
     */
    public String getCartInfo() {
        return cart.getItemList();
    }
    /**
     * @return the cart
     */
    public ShoppingCart getCart() {
        return cart;
    }
    /**
     * empties cart
     */
    public void clearCart() {
        cart.clearCart();
    }
    private ShoppingCart cart;
}
