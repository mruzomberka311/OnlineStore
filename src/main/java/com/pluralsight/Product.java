package com.pluralsight;

public class Product {
    private String name;
    private String id;
    private double price;
    private String department;

    public Product( String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return id + "|" + name + "|" + price;
    }
}




