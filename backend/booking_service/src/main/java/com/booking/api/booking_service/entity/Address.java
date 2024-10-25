package com.booking.api.booking_service.entity;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement
    private long id;

    @XmlElement
    private String zipCode; //Длина строки должна быть не меньше 10, Поле не может быть null

    public Address(long id, String zipCode) {
        this.id = id;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Address address = (Address) object;
        if (this.id == address.id && this.id != 0) {
            return true;
        }
        return Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }

}
