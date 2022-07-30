package com.poli.movies.clientfeign;

import com.poli.movies.helpers.Response;
import com.poli.movies.helpers.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BookingsClientImplHystrixFallBack implements BookingsClient {

    private final ResponseBuild builder;

    @Override
    public Response getAll() {
        return builder.success(new ArrayList<>());
    }
}
