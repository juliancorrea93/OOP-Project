package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class CustomerProductPage extends AppCompatActivity implements Serializable {
    private ShoppingCart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_page);
        cart = (ShoppingCart) getIntent().getSerializableExtra("cart");
        System.out.println(cart.getItemList());
    }
}