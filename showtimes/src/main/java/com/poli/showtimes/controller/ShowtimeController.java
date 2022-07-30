package com.poli.showtimes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.showtimes.helpers.ErrorMessage;
import com.poli.showtimes.helpers.Response;
import com.poli.showtimes.helpers.ResponseBuild;
import com.poli.showtimes.service.ShowtimeService;
import com.poli.showtimes.service.dto.ShowtimeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final ResponseBuild builder;


    @GetMapping()
    public Response getAll(){
        List<ShowtimeDTO> showtimeDTOS = showtimeService.getAll();
        if(showtimeDTOS==null){
            return builder.success();
        }
        return builder.success(showtimeDTOS);
    }

    @PostMapping()
    public Response save(@Valid @RequestBody ShowtimeDTO showtimeDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        showtimeDTO = showtimeService.save(showtimeDTO);
        return builder.success(showtimeDTO);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") Long id){

        ShowtimeDTO showtimeDTO = showtimeService.findById(id);

        if(showtimeDTO==null){
            return builder.success();
        }
        return builder.success(showtimeDTO);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Long id,@RequestBody ShowtimeDTO showtimeDTO){

        showtimeDTO = showtimeService.update(id,showtimeDTO);

        if(showtimeDTO==null){
            return builder.failed(showtimeDTO);
        }
        return builder.success(showtimeDTO);
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
