package com.correajulian.oopgroup19;

public class Customer extends User {
    public Customer(String[] user) {
        super(user);
    }
    public void checkOut() {
        float sum = cart.getTotal();
        //TODO: get abstract credit card return true to push transaction through
    }
    public void addProductToCart(Product product) {
        cart.addNewItem(product);
    }
    private ShoppingCart cart;
}
