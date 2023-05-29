package com.iss.theatre.model;

import java.time.LocalDateTime;

public class Representation{
    private int id;
    private Show show;
    private LocalDateTime date;
    private int noSeats;

    public Representation(){}

    public Representation(Show show, LocalDateTime date, int noSeats){
        this.show = show;
        this.date = date;
        this.noSeats = noSeats;
    }

    public Representation(int id, Show show, LocalDateTime date, int noSeats){
        this.id = id;
        this.show = show;
        this.date = date;
        this.noSeats = noSeats;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
