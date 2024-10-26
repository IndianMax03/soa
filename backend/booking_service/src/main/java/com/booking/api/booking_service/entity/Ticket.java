package com.booking.api.booking_service.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {

    @XmlElement
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой

    @XmlElement
    private Coordinates coordinates; //Поле не может быть null

    @XmlElement
    private String creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @XmlElement(name = "sold")
    private boolean isSold;

    @XmlElement
    private double price; //Значение поля должно быть больше 0

    @XmlElement
    private TicketType type; //Поле не может быть null

    @XmlElement
    private Venue venue; //Поле может быть null

    public Ticket() {
    }

    public Ticket(long id, String name, Coordinates coordinates, String creationDate, boolean isSold, double price, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.isSold = isSold;
        this.price = price;
        this.type = type;
        this.venue = venue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
