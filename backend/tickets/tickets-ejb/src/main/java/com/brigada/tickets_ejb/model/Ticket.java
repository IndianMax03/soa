package com.brigada.tickets_ejb.model;

import com.brigada.general.model.enums.TicketType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 11L;

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
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id")
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    @Column(name = "creation_date")
    private java.time.LocalDateTime creationDate = LocalDateTime.now(); // Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "is_sold")
    private boolean isSold;

    @DecimalMin(value = "0", inclusive = false)
    @Column(name = "price")
    @Min(0)
    private double price; //Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ticket_type")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private TicketType type; //Поле не может быть null

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", referencedColumnName = "id")
    private Venue venue; //Поле может быть null

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

}
