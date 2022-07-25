package com.poli.movies.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String title;

    private String director;

    private Integer rating;
}
