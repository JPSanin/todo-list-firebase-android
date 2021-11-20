package com.example.todolist;

public class Item {
    private String itemId;
    private String owner;
    private String itemName;
    private String itemDescription;
    private String date;
    private String status;

    public Item() {
    }

    public Item(String itemId, String owner, String itemName, String itemDescription, String date, String status) {
        this.itemId = itemId;
        this.owner = owner;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.date = date;
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
