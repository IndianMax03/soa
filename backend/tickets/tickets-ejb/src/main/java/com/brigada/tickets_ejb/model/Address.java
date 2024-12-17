package com.brigada.tickets_ejb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    private static final long serialVersionUID = 14L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Length(min = 10)
    @Column(name = "zip_code")
    private String zipCode; //Длина строки должна быть не меньше 10, Поле не может быть null

}
