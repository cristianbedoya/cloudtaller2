package com.poli.users.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.users.helpers.ErrorMessage;
import com.poli.users.helpers.Response;
import com.poli.users.helpers.ResponseBuild;
import com.poli.users.service.UserService;
import com.poli.users.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final ResponseBuild builder;

    @PostMapping()
    public Response save(@Valid @RequestBody UserDTO user, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        user = userService.save(user);
        return builder.success(user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {

        boolean resultDelete = userService.Delete(id);
        if(resultDelete){
            builder.success(id);
        }
        return builder.failed("No se puede eliminar un usuario si tiene reservas asociadas ");
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") Long id){
        UserDTO userDTO = userService.findById(id);
        if(userDTO==null){
            return builder.failed("No se encontraron registros");
        }
        return builder.success(userDTO);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
