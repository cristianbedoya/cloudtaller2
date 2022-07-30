package com.poli.bookings.clientfeign;

import com.poli.bookings.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movies-service", fallback = MoviesClientImplHystrixFallback.class)
public interface MoviesClient {

    @GetMapping("/cinema/api/v1/movies/{id}")
    Response getById(@PathVariable("id") Long id);
}
