package com.correajulian.oopgroup19;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public File getFile(){
        return f;
    }

    public ArrayList<Product> items = new ArrayList<>();
    private File f = new File("inventory.txt");
}
