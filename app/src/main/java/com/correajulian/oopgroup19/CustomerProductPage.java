package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_page);

        TextView text = findViewById(R.id.textView);

        String s = getIntent().getExtras().getString("item");

        text.setText(s);
    }
}