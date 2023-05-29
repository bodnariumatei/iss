package com.iss.theatre.model;

public class Reservation {
    private int id;
    private User client;
    private Representation representation;
    private Seat[] seats;

    public Reservation(User client, Representation representation, Seat[] seats) {
        this.client = client;
        this.representation = representation;
        this.seats = seats;
    }

    public Reservation(int id, User client, Representation representation, Seat[] seats) {
        this.id = id;
        this.client = client;
        this.representation = representation;
        this.seats = seats;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
