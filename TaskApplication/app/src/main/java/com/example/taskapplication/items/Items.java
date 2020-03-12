package com.example.taskapplication.items;

import java.io.Serializable;

public class Items implements Serializable {

    private String itemName;
    private double cost;
    private double avilQty;
    private double requestedQty;
    private int id;

    public Items(String itemName, double cost, double avilQty, int id) {
        this.itemName = itemName;
        this.cost = cost;
        this.avilQty = avilQty;
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getAvilQty() {
        return avilQty;
    }

    public void setAvilQty(double avilQty) {
        this.avilQty = avilQty;
    }

    public double getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(double requestedQty) {
        this.requestedQty = requestedQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
