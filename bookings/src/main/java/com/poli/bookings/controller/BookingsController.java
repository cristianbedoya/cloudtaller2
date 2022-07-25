package com.poli.bookings.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.bookings.helpers.ErrorMessage;
import com.poli.bookings.helpers.Response;
import com.poli.bookings.helpers.ResponseBuild;
import com.poli.bookings.service.BookingsService;
import com.poli.bookings.service.dto.BookingsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingsController {


    private final BookingsService bookingsService;
    private final ResponseBuild builder;

    @PostMapping()
    public Response save(@Valid @RequestBody BookingsDTO bookingsDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        bookingsDTO = bookingsService.save(bookingsDTO);
        return builder.success(bookingsDTO);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {

        bookingsService.Delete(id);

        return builder.success(id);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") Long id){
        BookingsDTO bookingsDTO = bookingsService.findById(id);
        if(bookingsDTO==null){
            return builder.success();
        }
        return builder.success(bookingsDTO);
    }

    @GetMapping("/user/{id}")
    public Response getByUser(@PathVariable("id") Long id){
        BookingsDTO bookingsDTO = bookingsService.findById(id);
        if(bookingsDTO==null){
            return builder.success();
        }
        return builder.success(bookingsDTO);
    }

    @GetMapping()
    public Response getAll(){
        List<BookingsDTO> moviesDTO = bookingsService.getAll();
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
