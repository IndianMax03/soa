package com.brigada.tickets_ejb.model;

import com.brigada.general.model.enums.VenueType;
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

@ToString
@Getter
@Setter
@Entity
@Table(name = "venues")
public class Venue implements Serializable {
    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @DecimalMin(value = "0", inclusive = false)
    @Column(name = "capacity")
    @Min(1)
    private long capacity; //Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "venue_type")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private VenueType type; //Поле не может быть null

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address; //Поле не может быть null

}
