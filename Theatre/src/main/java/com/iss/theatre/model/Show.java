package com.iss.theatre.model;

public class Show {
    private int id;
    private String name;
    private int duration;

    public Show(){}

    public Show(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public Show(int id, String name, int duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " (" + duration + " minutes)";
    }
}
