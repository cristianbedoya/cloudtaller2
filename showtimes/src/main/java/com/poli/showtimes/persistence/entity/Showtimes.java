package com.poli.showtimes.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Showtimes")
public class Showtimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @JoinTable(
            name = "rel_showtimes_movies",
            joinColumns = @JoinColumn(name = "FK_SHOWTIMES", nullable = false),
            inverseJoinColumns = @JoinColumn(name="FK_MOVIES", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Movies> movies;
}
