package com.poli.movies.clientfeign;

import com.poli.movies.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="showtimes-service",fallback = ShowtimesClientImplHystrixFallBack.class)
public interface ShowtimesClient {

    @GetMapping("/cinema/api/v1/showtimes")
    Response getAll();

}
