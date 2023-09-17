package com.megazone.springbootboilerplate.common.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megazone.springbootboilerplate.common.dto.CommonResponse;

@RequestMapping("${server.error.path:${error.path:/error}}")
@RestController
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    @RequestMapping
    public ResponseEntity<CommonResponse<Void>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        return CommonResponse.of("E-10", String.format("%s %s", body.get("path"), body.get("error")), status.value());
    }
}
