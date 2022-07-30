package com.poli.bookings.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userid;

    private Long showtimeid;

    @JoinTable(
            name = "rel_bookings_movies",
            joinColumns = @JoinColumn(name = "FK_BOOKINGS", nullable = false),
            inverseJoinColumns = @JoinColumn(name="FK_MOVIES", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Movies> movies;
}
