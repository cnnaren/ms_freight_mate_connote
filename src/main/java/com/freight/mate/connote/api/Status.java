package com.freight.mate.connote.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Status {

    BAD_REQUEST("400","Bad Request", "Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("500","Internal Server Error", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FORBIDDEN("403","Forbidden", "Forbidden", HttpStatus.FORBIDDEN),
    GATEWAY_TIMEOUT("504","Gateway Timeout", "Gateway Timeout", HttpStatus.GATEWAY_TIMEOUT),
    NOT_FOUND("404","Not Found", "Not Found", HttpStatus.NOT_FOUND);


    private String errorId;
    private String errorMessage;
    private String issue;
    private HttpStatus httpStatus;

}
