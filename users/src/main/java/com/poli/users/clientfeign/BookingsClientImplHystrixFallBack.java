package com.poli.users.clientfeign;

import com.poli.users.helpers.Response;
import com.poli.users.helpers.ResponseBuild;
import com.poli.users.service.dto.BookingsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BookingsClientImplHystrixFallBack implements BookingsClient {

    private final ResponseBuild builder;

    @Override
    public Response getByUser(@PathVariable("id") Long id) {
        return builder.success(new BookingsDTO());
    }
}