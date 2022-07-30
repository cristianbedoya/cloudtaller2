package com.poli.users.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotEmpty(message = "El name no puede ser vacio")
    private String name;

    @NotEmpty(message = "El lastname no puede ser vacio")
    private String lastName;

}
