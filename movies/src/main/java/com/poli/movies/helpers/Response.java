package com.poli.movies.helpers;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private Integer code;
    private Object data;
}