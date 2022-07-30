package com.poli.bookings.clientfeign;

import com.poli.bookings.helpers.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoviesClientImplHystrixFallback implements MoviesClient {

    @Override
    public Response getById(Long id) {
        return null;
    }
}
