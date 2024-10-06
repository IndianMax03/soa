package com.tickets.api.tickets_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "venues")
public class Venue implements Comparable<Venue> {

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
    private long capacity; //Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VenueType type; //Поле не может быть null

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address; //Поле не может быть null

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
