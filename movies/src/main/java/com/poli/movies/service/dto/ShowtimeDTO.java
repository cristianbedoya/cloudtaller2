package com.poli.movies.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ShowtimeDTO {

    private Long id;

    private Date date;

    private List<MovieDTO> movies;

}