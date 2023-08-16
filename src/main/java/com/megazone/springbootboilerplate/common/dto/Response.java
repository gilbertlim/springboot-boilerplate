package com.megazone.springbootboilerplate.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public record Response<T>(
    String code,
    String message,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BindErrorResponse> errors
) {

    public Response(String code, String message, T data) {
        this(code, message, data, null);
    }

    public Response(String code, String message, List<BindErrorResponse> error) {
        this(code, message, null, error);
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        return withData(data, ResponseType.OK);
    }

    public static <T> ResponseEntity<Response<T>> created(T data, String locationPath) {
        Response<T> response = new Response<>(ResponseType.CREATE.getCode(), ResponseType.CREATE.getMessage(), data);
        return ResponseEntity.created(URI.create(locationPath)).body(response);
    }

    public static <T> ResponseEntity<Response<T>> withData(T data, ResponseType responseType) {
        return withData(data, responseType, responseType.getStatus().value());
    }

    public static <T> ResponseEntity<Response<T>> withData(T data, ResponseType responseType, int status) {
        return of(responseType.getCode(), responseType.getMessage(), status, data);
    }

    public static <T> ResponseEntity<Response<T>> of(String code, String message, int status) {
        return of(code, message, status, null);
    }

    public static <T> ResponseEntity<Response<T>> of(String code, String message, int status, T data) {
        Response<T> response = new Response<>(code, message, data);
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<Response<Void>> error(ResponseType responseType, Exception e) {
        return error(responseType, responseType.getStatus().value(), e);
    }

    public static ResponseEntity<Response<Void>> error(ResponseType responseType, int status, Exception e) {
        return error(responseType.getCode(), e.getMessage(), status, null);
    }

    public static ResponseEntity<Response<Void>> error(ResponseType responseType, List<BindErrorResponse> bindErrors) {
        return error(responseType.getCode(), responseType.getMessage(), responseType.getStatus().value(), bindErrors);
    }

    public static <T> ResponseEntity<Response<T>> error(String code, String message, int status, List<BindErrorResponse> bindErrors) {
        Response<T> response = new Response<>(code, message, bindErrors);
        return ResponseEntity.status(status).body(response);
    }
}
