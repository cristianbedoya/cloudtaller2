package com.poli.movies.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingsDTO {

    private Long id;

    private Long userid;

    private Long showtimeid;

    private List<MovieDTO> movies;
}