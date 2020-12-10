package com.correajulian.oopgroup19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class CustomerProductView extends AppCompatActivity implements Serializable {

    private ListView lv;
    Button checkOut;
    Customer customer;
    private String seller;

    class ProductViewAdapter extends ArrayAdapter<Product> {
        Context context;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>(); // use wrapper in product if needed for String conversion
        ArrayList<String> img_names= new ArrayList<>();
        ArrayList<Product> items;


        ProductViewAdapter(Context c, ArrayList<Product> products) {
            //change arraylist products to an invertory
            super(c, R.layout.product_tiles, products);
            this.items = products;
            this.context = c;
            //Image view for product icon will need to be added here, maybe just pass the path
            for (int i = 0; i < products.size(); i++) {
                this.img_names.add(products.get(i).getPath());
            }
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
            ImageView imageView = row.findViewById(R.id.product_image);
            TextView name = row.findViewById(R.id.name);
            TextView price = row.findViewById(R.id.price);
            Button addItem = row.findViewById(R.id.addtocart);
            name.setOnClickListener(v -> {
                Intent intent = new Intent(CustomerProductView.this ,CustomerProductPage.class);
                Product p = new Product(items.get(position));
                intent.putExtra("clicked product", p);
                intent.putExtra("customer", customer);
                startActivityForResult(intent, 2);
            });
            addItem.setOnClickListener(v -> {
                Product p = new Product(items.get(position));
                customer.addProductToCart(p,1);
                Toast.makeText(getApplicationContext(), name.getText() + " has been added to cart", Toast.LENGTH_LONG).show();
            });
            try {
                InputStream stream = getAssets().open(img_names.get(position));
                Drawable drawable = Drawable.createFromStream(stream, null);
                imageView.setImageDrawable(drawable);
            }
            catch (IOException ex) {
                System.out.println("Image failed to load");
            }
            name.setText(names.get(position));
            price.setText(prices.get(position));

            return row;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_view);

        seller = getIntent().getExtras().getString("seller");

        lv = findViewById(R.id.listView);

        ArrayList<Product> products;

        customer = (Customer) getIntent().getSerializableExtra("customer");

        checkOut = findViewById(R.id.checkout);

        products = getProducts();

        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).getSeller().equals(seller)) {
                System.out.println("Removing " + products.get(i).getName());
                products.remove(i);
            }
        }

        ProductViewAdapter adapter = new ProductViewAdapter(getApplicationContext(), products);
        lv.setAdapter(adapter);
        checkOut.setOnClickListener(v-> {
            Intent intent = new Intent(CustomerProductView.this, CheckOut.class);
            intent.putExtra("customer", customer);
            intent.putExtra("seller", seller);
            startActivity(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2) {
            customer = (Customer) data.getSerializableExtra("customer");
            System.out.println(customer.getCartInfo());
        }
    }
    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        AssetManager am = getResources().getAssets();

        try {
            InputStream is = am.open("inventory.txt");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                Product p = new Product(arr[0],Float.parseFloat(arr[1]), arr[2],arr[3],Integer.parseInt(arr[4]));
                if (p.getQuantity() > 1) {
                    products.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}