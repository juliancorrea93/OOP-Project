package com.correajulian.oopgroup19;

import java.util.ArrayList;

public class ShoppingCart {
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }
    public float getTotal() {
        float total = 0;
        for (int i = 0; i < this.items.size(); i++) {
            total += this.items.get(i).getPrice() * this.quantities.get(i);
        }
        return total;
    }
    //TODO: Revise method to include quantity or compare Product strings, probably best to compare product Strings
    public String getItemList() {
        ArrayList<String> products = new ArrayList<>();

        for (int i = 0; i < this.items.size(); i++) {
            Float f = items.get(i).getPrice();
            products.add(items.get(i).getName() + " $" + f.toString() + " Quantity: " + quantities.get(i));
        }

        return products.toString();
    }
    protected boolean addNewItem(Product product) {
        if (!this.items.isEmpty()) {
            for (int i = 0; i < this.items.size(); i++) {
                if (this.items.get(i).getName().equals(product.getName())) {
                    int tmp = quantities.get(i).intValue();
                    tmp += 1;
                    quantities.set(i, tmp);
                    return true;
                }
            }
            quantities.add(1);
            return items.add(product);
        }
        else {
            quantities.add(1);
            return items.add(product);
        }
    }
    private ArrayList<Product> items;
    private ArrayList<Integer> quantities;
}
