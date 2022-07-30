package com.poli.movies.clientfeign;


import com.poli.movies.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="bookings-service",fallback = BookingsClientImplHystrixFallBack.class)
public interface BookingsClient {

    @GetMapping("/cinema/api/v1/bookings")
    Response getAll();

}
