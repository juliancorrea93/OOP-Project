package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomerProductView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_view);

        String user = getIntent().getExtras().getString("user");
        //user.printUserInfo();
        String[] userinfo = user.split(",");
        Customer customer = new Customer(userinfo);
        System.out.println(customer.toString());

    }
}