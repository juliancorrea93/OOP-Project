package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class CheckOut extends AppCompatActivity implements Serializable {
    TextView card_num;
    TextView cvc;
    TextView expdate;
    TextView cust_name;
    EditText num;
    EditText cvc_nums;
    EditText expdate_input;
    EditText name_input;
    Button transaction;
    TextView cartinfo;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        //ShoppingCart shoppingCart = (ShoppingCart) getIntent().getSerializableExtra("cart");
        Customer customer = (Customer) getIntent().getSerializableExtra("customer");
        products = (ArrayList<Product>) getIntent().getSerializableExtra("products");
        System.out.println(customer.getCartInfo());

        card_num = findViewById(R.id.custnum);
        cvc = findViewById(R.id.custcvc);
        expdate = findViewById(R.id.custexp);
        cust_name = findViewById(R.id.custname);
        num = findViewById(R.id.numinput);
        cvc_nums = findViewById(R.id.cvcinput);
        expdate_input = findViewById(R.id.expinput);
        name_input = findViewById(R.id.nameinput);
        transaction = findViewById(R.id.transaction);

        cartinfo = findViewById(R.id.CartInfo);

        cartinfo.setText(customer.getCartInfo());


        transaction.setOnClickListener(v -> {
            try{
                Long number = Long.parseLong(num.getText().toString());
                Integer cvc_num = Integer.parseInt(cvc_nums.getText().toString());
                String exp_date = expdate_input.getText().toString();
                CreditCard card = new CreditCard(number.longValue(),exp_date, cvc_num.intValue());
                if (card.Charge(customer.getCart(), number.longValue(),exp_date, cvc_num.intValue())) {
                    Product[] p = new Product[customer.getCart().getItems().size()];

                    for (int i = 0; i < customer.getCart().getItems().size(); i++) {

                        for (Product item : products) {
                            if (item.equals((Product) customer.getCart().getItems().get(i))) {
                                p[i] = item;
                            }
                        }
                        p[i] = products.get(i);
                    }
                    Inventory i = new Inventory(p);
                    i.updateInventory(customer.getCart().getItems(), customer.getCart().getQuantities(), products, getApplicationContext());


                    Toast.makeText(getApplicationContext(), "Transaction complete!", Toast.LENGTH_LONG).show();
                    customer.clearCart();
                    Intent intent = new Intent(CheckOut.this, SelectStoreFront.class);
                    intent.putExtra("user", customer);
                    startActivity(intent);
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Make sure card number and cvc numbers are correct", Toast.LENGTH_LONG).show();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}