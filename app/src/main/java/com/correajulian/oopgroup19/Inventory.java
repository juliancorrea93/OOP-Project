package com.correajulian.oopgroup19;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Inventory implements Serializable {
    public Inventory(Product ... p){
        items.addAll(Arrays.asList(p));
        this.saveInit();
    }

    public void addProduct(Product p){
        items.add(p);
        this.saveAppend();
    }

    public void delProduct(Product p){
        items.remove(p);
        this.saveAppend();
    }

    public int size(){
        return items.size();
    }

    public Product get(int i){
        try{
            return items.get(i);
        }
        catch(Exception e){
            return null;
        }
    }

    private void saveInit(){
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            FileWriter wr = new FileWriter(f);
            for(int i = 0; i < items.size(); i++){
                wr.write(items.get(i).toString() + " ");
                wr.close();
            }
        } catch (IOException e) {}
    }

    private void saveAppend(){
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            FileWriter wr = new FileWriter(f, true);
            for(int i = 0; i < items.size(); i++){
                wr.append(items.get(i).toString() + " ");
                wr.close();
            }
        } catch (IOException e) {}
    }

    /**
     * Updatese the inventory file
     * @param purchase
     * @param quantities
     */
    public void updateInventory(ArrayList<Product> purchase, ArrayList<Integer> quantities, ArrayList<Product> all, Context c) throws IOException {
        ArrayList<String> prodstrings = new ArrayList<>();
        for (int i = 0; i < purchase.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).equals(purchase.get(i))) {
                    System.out.println(items.get(j).getQuantity());
                    System.out.println(quantities.get(i));
                    items.get(j).setQuantity(items.get(j).getQuantity() - quantities.get(i));
                    prodstrings.add(items.get(j).toString());
                }
                else {
                    prodstrings.add(items.get(j).toString());
                }
            }
        }
        //saveInit();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(c.openFileOutput("inventory.txt", Context.MODE_PRIVATE));


        for (String str: prodstrings) {
            System.out.println(str);
            outputStreamWriter.write(str + System.lineSeparator());
        }
        outputStreamWriter.close();
    }

    public ArrayList<Product> getInventory() {
        return this.items;
    }

    public File getFile(){
        return f;
    }

    public ArrayList<Product> items = new ArrayList<>();
    private File f = new File("inventory.txt");
}
