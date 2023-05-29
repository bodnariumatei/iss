package com.iss.theatre.model;

public class Seat {
    private int id;
    private String area;
    private int row;
    private int number;
    private float price;

    public Seat(){}

    public Seat(String area, int row, int number, float price) {
        this.area = area;
        this.row = row;
        this.number = number;
        this.price = price;
    }

    public Seat(int id, String area, int row, int number, float price) {
        this.id = id;
        this.area = area;
        this.row = row;
        this.number = number;
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
