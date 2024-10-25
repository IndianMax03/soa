package com.booking.api.booking_service.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement
    private long id;

    @XmlElement
    private String username;

    @XmlElement
    private String password;

    @XmlElement
    private double balance;

    @XmlElement
    private List<Ticket> tickets;

    public Person() {
    }

    public Person(long id, String username, String password, double balance, List<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.tickets = tickets;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
