package com.poli.bookings.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Movies")
public class Movies {

    @Id
    private Long id;

    @ManyToMany(mappedBy="movies")
    private List<Bookings> bookings;
}
