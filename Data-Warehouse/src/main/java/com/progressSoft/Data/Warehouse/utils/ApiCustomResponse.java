package com.progressSoft.Data.Warehouse.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

@ToString
@NoArgsConstructor
@Data
public class ApiCustomResponse<Object> {
    private String message;
    private Object data;
    private HttpStatus statusCode;

    public ApiCustomResponse(String message, Object data, HttpStatus statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }
}
