package com.poli.bookings.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviesDTO {

    private Long id;

    private String title;

    private String director;

    private Integer rating;
}