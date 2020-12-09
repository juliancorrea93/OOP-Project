package com.correajulian.oopgroup19;

import java.util.ArrayList;

public class ShoppingCart {
    public ShoppingCart(ArrayList<Product> item_list) {
        this.items = item_list;
    }
    public float getTotal() {
        float total = 0;
        for (Product product : items) {
            total += product.getPrice();
        }
        return total;
    }
    //TODO: Revise method to include quantity or compare Product strings, probably best to compare product Strings
    public String getItemList() {
        ArrayList<String> products = new ArrayList<>();
        items.forEach(item -> {
            products.add(items.toString());
        });

        return "";
    }
    protected boolean addNewItem(Product product) {
        return items.add(product);
        //potential area for problems, monitor this one
    }
    private ArrayList<Product> items = new ArrayList<>();
}
