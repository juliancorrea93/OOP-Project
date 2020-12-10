package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class SellerUpdatePage extends AppCompatActivity implements Serializable {
    private Product product;
    private EditText edit;
    private TextView tv;
    private Button addtoQty;
    private ImageView img;
    private Seller seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_update_page);
        product = (Product) getIntent().getSerializableExtra("clicked product");
        seller = (Seller) getIntent().getSerializableExtra("seller");
        edit = findViewById(R.id.qty);
        tv = findViewById(R.id.productname);
        img = findViewById(R.id.product_img);
        addtoQty = findViewById(R.id.addQuantity);

        edit.setText("1");
        tv.setText(product.getName());
        try {
            InputStream stream = getAssets().open(product.getPath());
            Drawable drawable = Drawable.createFromStream(stream, null);
            img.setImageDrawable(drawable);
        }
        catch (IOException ex) {
            System.out.println("Image failed to load");
        }
        addtoQty.setOnClickListener(v -> {
            try {
                Integer i = Integer.parseInt(edit.getText().toString());
                if (i.intValue() < 1) {
                    Toast.makeText(getApplicationContext(), "Please input an integer equal to, or greater than 1", Toast.LENGTH_LONG).show();
                }
                else {
                    //seller method to add to inventory
                    Toast.makeText(getApplicationContext(), "Added quantities", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("seller", seller);
                    setResult(2, intent);
                    finish();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Please input a valid integer greater than, or equal to 1", Toast.LENGTH_LONG).show();
            }

        });
    }
}