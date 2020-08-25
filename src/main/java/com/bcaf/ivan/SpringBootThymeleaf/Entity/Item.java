package com.bcaf.ivan.SpringBootThymeleaf.Entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Item {
    private int id;
    private String itemName;
    private int itemType;
    private String description;
    private double price;
    private int stock;
    private String createdDate;
    private String updatedDate;

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY, HH:mm");
        if(updatedDate!=null)
            this.updatedDate = dateFormat.format(updatedDate);
        else
            this.updatedDate="";
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY, HH:mm");
        this.createdDate = dateFormat.format(createdDate);
    }
}
