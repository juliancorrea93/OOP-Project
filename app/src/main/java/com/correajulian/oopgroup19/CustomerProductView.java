package com.correajulian.oopgroup19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerProductView extends AppCompatActivity {

    private ListView lv;
    private ShoppingCart sc;

    class ProductViewAdapter extends ArrayAdapter<Product> {
        Context context;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<String>(); // use wrapper in product if needed for String conversion

        ProductViewAdapter(Context c, ArrayList<Product> products) {
            //change arraylist products to an invertory
            super(c, R.layout.product_tiles, products);
            this.context = c;
            //Image view for product icon will need to be added here, maybe just pass the path
            for (int i = 0; i < products.size(); i++) {
                this.names.add(products.get(i).getName());
            }
            for (int i = 0; i < products.size(); i++) {
                Float f = products.get(i).getPrice();
                this.prices.add(f.toString());
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.product_tiles, parent, false);

            TextView name = row.findViewById(R.id.name);
            TextView price = row.findViewById(R.id.price);
            Button addItem = row.findViewById(R.id.addtocart);
            name.setOnClickListener(v -> {
                Intent intent = new Intent(CustomerProductView.this ,CustomerProductPage.class);
                intent.putExtra("item", "hello world");
                startActivity(intent);
            });
            addItem.setOnClickListener(v -> {
                Toast.makeText(getApplicationContext(), name.getText() + " has been added to cart", Toast.LENGTH_LONG).show();
            });
            name.setText(names.get(position));
            price.setText(prices.get(position));

            return row;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_view);

        String user = getIntent().getExtras().getString("user");
        //user.printUserInfo();
        String[] userinfo = user.split(",");
        Customer customer = new Customer(userinfo);
        System.out.println(customer.toString());

        lv = findViewById(R.id.listView);

        ArrayList<Product> products = new ArrayList<>();

        Product p1 = new Product("Fridge", 450, "C:\\Users\\Julian", "Amazon");
        Product p2 = new Product("Detergent", 21, "C:\\Users\\Julian", "Tide");
        Product p3 = new Product("Hot Sauce", 5, "C:\\Users\\Julian", "Fran");

        products.add(p1);
        products.add(p2);
        products.add(p3);

        ProductViewAdapter adapter = new ProductViewAdapter(getApplicationContext(), products);
        lv.setAdapter(adapter);

    }
}