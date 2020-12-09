package com.correajulian.oopgroup19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CustomerProductView extends AppCompatActivity implements Serializable {

    private ListView lv;
    private ShoppingCart sc;

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
                intent.putExtra("cart", sc);
                startActivity(intent);
            });
            addItem.setOnClickListener(v -> {
                Product p = new Product(items.get(position));
                sc.addNewItem(p,1);
                System.out.println(sc.getItemList());
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

        String seller = getIntent().getExtras().getString("seller");

        lv = findViewById(R.id.listView);

        ArrayList<Product> products = new ArrayList<>();

        sc = new ShoppingCart();

        //read in products here
        Product p1 = new Product("Fridge", 450, "fridge.jpeg", "Amazon");
        Product p2 = new Product("Detergent", 21, "detergent.jpg", "Amazon");
        Product p4 = new Product("Hot Sauce", 5 , "hotsauce.jpg", "Amazon");
        //next shouldn't be shown
        Product p3 = new Product("Dog", 250, "jake.jpg", "Fran Dog Breeder");

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);

        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).getSeller().equals(seller)) {
                System.out.println("Removing " + products.get(i).getName());
                products.remove(i);
            }
        }

        ProductViewAdapter adapter = new ProductViewAdapter(getApplicationContext(), products);
        lv.setAdapter(adapter);

    }
}