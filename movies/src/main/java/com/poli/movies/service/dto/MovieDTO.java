package com.poli.movies.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    @NotEmpty(message = "El titulo no puede ser vacio")
    private String title;

    @NotEmpty(message = "El director no puede ser vacio")
    private String director;

    @Min(value = 1, message = "rating no puede ser menor a 1")
    @Max(value = 5, message = "rating no puede ser mayor a 5")
    private Integer rating;
}
