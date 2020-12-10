package com.correajulian.oopgroup19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private String name;
    private String psword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
            name = user.getText().toString();
            psword = pass.getText().toString();
            User tmp = new User(name, psword);
            ArrayList<User> users = fetchUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(tmp.getUsername()) && users.get(i).getPassword().equals(tmp.getPassword())) {
                    tmp = users.get(i);
                    if (tmp.getUserType() == 0) {
                        GoToCustomerView(tmp);
                    }
                    else if (tmp.getUserType() == 1) {
                        GoToSellerView(tmp);
                    }
                }
            }
        });
    }
    private void GoToCustomerView(User user) {
        Intent intent = new Intent(MainActivity.this, SelectStoreFront.class);
        user.printUserInfo();
        intent.putExtra("user", new Customer(user));
        startActivity(intent);
    }
    private void GoToSellerView(User user) {
        Intent intent = new Intent(MainActivity.this, SellerMainMenu.class);
        user.printUserInfo();
        intent.putExtra("user", new Seller(user));
        startActivity(intent);
    }
    private ArrayList<User> fetchUsers() {
        ArrayList<User> users = new ArrayList<>();

        AssetManager am = getResources().getAssets();

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

        return users;
    }
}