package com.correajulian.oopgroup19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SelectStoreFront extends AppCompatActivity {
    private ListView listView;

    class StoreFrontAdapter extends ArrayAdapter {
        ArrayList<Seller> sellers;
        Context context;
        StoreFrontAdapter(Context c, ArrayList<Seller> seller_arr) {
            super(c,R.layout.seller_tiles, seller_arr);
            sellers = seller_arr;
            context = c;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.seller_tiles, parent, false);
            TextView name = row.findViewById(R.id.name);

            name.setText(sellers.get(position).getUsername());
            return row;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_store_front);

        String user = getIntent().getExtras().getString("user");
        //user.printUserInfo();
        String[] userinfo = user.split(",");
        Customer customer = new Customer(userinfo);
        Toast.makeText(getApplicationContext(),"Welcome " + customer.getUsername() + "!", Toast.LENGTH_LONG).show();

        StoreFrontAdapter adapter = new StoreFrontAdapter(getApplicationContext(),getSellers());
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView name = view.findViewById(R.id.name);
            Intent intent = new Intent(SelectStoreFront.this, CustomerProductView.class);
            intent.putExtra("seller", (String)name.getText());
            startActivity(intent);
        });
    }
    private ArrayList<Seller> getSellers() {
        ArrayList<User> users = new ArrayList<>();

        AssetManager am = getResources().getAssets();

        ArrayList<Seller> sellers = new ArrayList<>();

        try {
            InputStream is = am.open("users.txt");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                String[] userinfo = line.split(",");
                users.add(new User(userinfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.forEach(u -> {
            if (u.getUserType() == 1) {
                String[] tmp = u.toString().split(",");
                sellers.add(new Seller(tmp));
            }

        });
        return sellers;
    }
}