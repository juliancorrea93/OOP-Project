package com.correajulian.oopgroup19;

import java.io.FileReader;
import java.io.IOException;

public class Seller extends User {
    public Seller(String user, String pass) {
        super(user, pass);
        loadInventory();
    }

    public Seller(String[] userinfo) {
        super(userinfo);
        loadInventory();
    }

    public void addProduct(Product p){
        items.addProduct(p);
    }

    public void delProduct(Product p){
        items.delProduct(p);
    }

    private void loadInventory(){
        try{
            FileReader fr = new FileReader(items.getFile());
            char[] cbuf = new char[100];
            fr.read(cbuf);
            fr.close();
            int spaceCount = 0;
            for(int i = 0; i < 100; i++){
                if(cbuf[i] == ' '){
                    spaceCount++;
                }
            }
            String temp = String.valueOf(cbuf);
            String[] strs = temp.split(" ");
            String[][] strs1 = new String[spaceCount][strs.length];
            for(int i = 0; i < strs1.length; i++){
                for(int k = 0; k < strs[i].length(); k++){
                    strs1[k] = strs[i].split(",");
                }
            }
            for(int i = 0; i < strs1.length; i++){
                Product p = new Product(strs1[i][0], Float.parseFloat(strs1[i][0]), strs1[i][0], strs1[i][0]);
                items.addProduct(p);
            }
        } catch (IOException e) {}
    }

    private Inventory items = new Inventory();
}
