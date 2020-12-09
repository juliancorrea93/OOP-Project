package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SellerMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main_menu);

        System.out.println("We are in the seller main menu");
    }
}