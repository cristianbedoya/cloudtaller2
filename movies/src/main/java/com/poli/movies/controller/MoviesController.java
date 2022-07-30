package com.poli.movies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.movies.helpers.ErrorMessage;
import com.poli.movies.helpers.Response;
import com.poli.movies.helpers.ResponseBuild;
import com.poli.movies.service.MoviesService;
import com.poli.movies.service.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;
    private final ResponseBuild builder;

    @PostMapping()
    public Response save(@Valid @RequestBody MovieDTO movieDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        movieDTO = moviesService.save(movieDTO);
        return builder.success(movieDTO);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {

        boolean resultDelete = moviesService.Delete(id);

        if(resultDelete) {
            return builder.success(id);
        } else {
            return builder.failed("No se puede eliminar una película si tiene programaciones o reservas asociadas");
        }
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") Long id){
        MovieDTO movieDTO = moviesService.findById(id);
        if(movieDTO==null){
            return builder.success();
        }
        return builder.success(movieDTO);
    }

    @GetMapping()
    public Response getAll(){
        List<MovieDTO> moviesDTO = moviesService.getAll();
        if(moviesDTO==null){
            return builder.success();
        }
        return builder.success(moviesDTO);
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
