package com.poli.showtimes.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
public class ShowtimeDTO {

    private Long id;

    @NotNull(message = "El campo date no puede estar nulo")
    private Date date;

    private List<MoviesDTO> movies;

}
