package com.tickets.api.tickets_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    @Column(name = "creation_date")
    private java.time.LocalDateTime creationDate = LocalDateTime.now(); //FIXME Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @DecimalMin(value = "0", inclusive = false)
    @Column(name = "price")
    private double price; //Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TicketType type; //Поле не может быть null

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "venue_id")
    private Venue venue; //Поле может быть null

}
