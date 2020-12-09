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

public class CustomerProductPage extends AppCompatActivity implements Serializable {
    private ShoppingCart cart;
    private Product product;
    private EditText edit;
    private TextView tv;
    private Button addtoCart;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_page);
        cart = (ShoppingCart) getIntent().getSerializableExtra("cart");
        product = (Product) getIntent().getSerializableExtra("clicked product");

        edit = findViewById(R.id.qty);
        tv = findViewById(R.id.productname);
        img = findViewById(R.id.product_img);
        addtoCart = findViewById(R.id.addCart);

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

        addtoCart.setOnClickListener(v -> {
            try {
                Integer i = Integer.parseInt(edit.getText().toString());
                if (i.intValue() < 1) {
                    Toast.makeText(getApplicationContext(), "Please input an integer equal to, or greater than 1", Toast.LENGTH_LONG).show();
                }
                else {
                    cart.addNewItem(product, i);
                    //make Toast msg to say added to cart
                    Intent intent = new Intent();
                    intent.putExtra("cart", cart);
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