package com.poli.users.clientfeign;

import com.poli.users.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bookings-service",fallback = BookingsClientImplHystrixFallBack.class)
public interface BookingsClient {

    @GetMapping("/cinema/api/v1/bookings/user/{id}")
    Response getByUser(@PathVariable("id") Long id);

}