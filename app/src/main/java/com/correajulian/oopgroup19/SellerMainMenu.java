package com.correajulian.oopgroup19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import java.util.ArrayList;

public class SellerMainMenu extends AppCompatActivity {
    private ListView lv;
    private Seller seller;
    class InventoryAdapter extends ArrayAdapter {
        Context context;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>(); // use wrapper in product if needed for String conversion
        ArrayList<String> img_names= new ArrayList<>();
        ArrayList<Product> items;
        InventoryAdapter(Context c, ArrayList<Product> products) {
            super(c, R.layout.seller_tiles, products);
            this.items = products;
            this.context = c;
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
            View row = layoutInflater.inflate(R.layout.seller_main_tiles, parent, false);
            ImageView imageView = row.findViewById(R.id.product_image);
            TextView name = row.findViewById(R.id.name);
            TextView price = row.findViewById(R.id.price);
            name.setOnClickListener(v -> {
                Intent intent = new Intent(SellerMainMenu.this ,SellerUpdatePage.class);
                Product p = new Product(items.get(position));
                intent.putExtra("clicked product", p);
                intent.putExtra("seller", seller);
                startActivityForResult(intent, 2);
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
        setContentView(R.layout.activity_seller_main_menu);

        System.out.println("We are in the seller main menu");
        seller = (Seller) getIntent().getSerializableExtra("seller");
        //Inventory inventory = new Inventory(getProducts());
        lv = findViewById(R.id.listview_seller);


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