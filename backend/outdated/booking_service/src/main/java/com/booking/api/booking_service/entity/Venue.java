package com.booking.api.booking_service.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlRootElement(name = "venue")
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue implements Comparable<Venue> {

    @XmlElement
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой

    @XmlElement
    private long capacity; //Значение поля должно быть больше 0

    @XmlElement
    private VenueType type; //Поле не может быть null

    @XmlElement
    private Address address; //Поле не может быть null

    public Venue() {
    }

    public Venue(int id, String name, long capacity, VenueType type, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int compareTo(Venue o) {
        if (this.id == o.id && this.id != 0) {
            return 0;
        }
        return Long.compare(this.capacity, o.capacity);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Venue venue = (Venue) object;
        if (this.id == venue.id && this.id != 0) {
            return true;
        }
        return capacity == venue.capacity && Objects.equals(name, venue.name) && type == venue.type && Objects.equals(address, venue.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, type, address);
    }
}
