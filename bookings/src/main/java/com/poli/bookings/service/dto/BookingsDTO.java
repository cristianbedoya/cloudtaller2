package com.poli.bookings.service.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BookingsDTO {

    private Long id;

    @NotNull(message = "El campo userid no puede estar nulo")
    private Long userid;

    @NotNull(message = "El campo showtimeid no puede estar nulo")
    private Long showtimeid;

    private List<MoviesDTO> movies;
}
