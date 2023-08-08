package com.megazone.springbootboilerplate.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseType {
    CREATE("A-1", HttpStatus.CREATED, "생성됨"),
    OK("A-2", HttpStatus.OK, "정보 조회"),
    UPDATE("A-3", HttpStatus.OK, "정보 업데이트"),
    BAD_REQUEST("E-0", HttpStatus.BAD_REQUEST, "요청이 올바르지 않음"),
    NOT_FOUND("E-1", HttpStatus.NOT_FOUND, "정보를 찾을 수 없음"),
    DUPLICATE("E-2", HttpStatus.CONFLICT, "중복된 데이터");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ResponseType(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
