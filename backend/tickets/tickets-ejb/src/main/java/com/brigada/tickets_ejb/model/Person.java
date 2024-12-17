package com.brigada.tickets_ejb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person implements Serializable {
    private static final long serialVersionUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @NotBlank
    @Column(name = "username")
    private String username;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

}
