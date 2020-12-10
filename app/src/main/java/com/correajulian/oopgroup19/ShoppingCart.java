package com.correajulian.oopgroup19;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    /**
     * initializes new shopping cart
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }
    /**
     * @return summation of products in cart
     */
    public float getTotal() {
        float total = 0;
        for (int i = 0; i < this.items.size(); i++) {
            total += this.items.get(i).getPrice() * this.quantities.get(i);
        }
        return total;
    }
    /**
     * @return string representation of the cart
     */
    public String getItemList() {
        ArrayList<String> products = new ArrayList<>();

        for (int i = 0; i < this.items.size(); i++) {
            Float f = items.get(i).getPrice();
            products.add(items.get(i).getName() + " $" + f.toString() + " Quantity: " + quantities.get(i));
        }
        Float f = getTotal();

        return products.toString() + "\nTotal: $" + f.toString();
    }
    /**
     * add product to cart
     * @param product exiting product
     * @param quantity number of that product
     * @return true if item was added
     */
    protected boolean addNewItem(Product product, int quantity) {
        if (!this.items.isEmpty()) {
            for (int i = 0; i < this.items.size(); i++) {
                if (this.items.get(i).getName().equals(product.getName()) && quantity == 1) {
                    int tmp = quantities.get(i).intValue();
                    tmp += 1;
                    quantities.set(i, tmp);
                    return true;
                }
                else if (this.items.get(i).getName().equals(product.getName()) && quantity > 1) {
                    int tmp = quantities.get(i).intValue();
                    tmp += quantity;
                    quantities.set(i,tmp);
                    return true;
                }
            }
            //adding first
            if (quantity > 1) {
                quantities.add(quantity);
                return items.add(product);
            }
            else{
                quantities.add(1);
                return items.add(product);
            }
        }
        else {
            if (quantity > 1) {
                quantities.add(quantity);
                return items.add(product);
            }
            else{
                quantities.add(1);
                return items.add(product);
            }
        }
    }
    /**
     * empties cart
     */
    public void clearCart() {
        items.clear();
        quantities.clear();
    }

    /**
     * Gets cart items
     * @return
     */
    public ArrayList getItems() {
        return items;
    }

    /**
     * Gets quantities for cart items
     * @return
     */
    public ArrayList getQuantities() {
        return quantities;
    }

    private ArrayList<Product> items;
    private ArrayList<Integer> quantities;
}
