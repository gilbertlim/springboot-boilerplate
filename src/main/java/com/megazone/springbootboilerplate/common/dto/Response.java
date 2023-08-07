package com.megazone.springbootboilerplate.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.net.URI;
import org.springframework.http.ResponseEntity;

public record Response<T>(
    String code,
    String message,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data
) {
    public static <T> ResponseEntity<Response<T>> created(ResponseType responseType, T data, String locationPath) {
        Response<T> response = new Response<>(responseType.getCode(), responseType.getMessage(), data);
        return ResponseEntity.created(URI.create(locationPath)).body(response);
    }

    public static <T> ResponseEntity<Response<T>> withData(ResponseType responseType, T data) {
        return withData(responseType, responseType.getStatus(), data);
    }

    public static <T> ResponseEntity<Response<T>> withData(ResponseType responseType, int status, T data) {
        return of(responseType.getCode(), responseType.getMessage(), status, data);
    }

    public static ResponseEntity<Response<Void>> error(ResponseType responseType, Exception e) {
        return error(responseType, responseType.getStatus(), e);
    }

    public static ResponseEntity<Response<Void>> error(ResponseType responseType, int status, Exception e) {
        return of(responseType.getCode(), e.getMessage(), status, null);
    }

    public static ResponseEntity<Response<Void>> of(ResponseType type) {
        return of(type, null);
    }

    public static <T> ResponseEntity<Response<T>> of(ResponseType type, T data) {
        Response<T> response = new Response<>(type.getCode(), type.getMessage(), data);
        return ResponseEntity.status(type.getStatus()).body(response);
    }

    public static <T> ResponseEntity<Response<T>> of(String code, String message, int status, T data) {
        Response<T> response = new Response<>(code, message, data);
        return ResponseEntity.status(status).body(response);
    }
}
