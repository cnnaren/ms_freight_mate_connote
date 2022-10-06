package com.freight.mate.connote.controller;

import com.freight.mate.connote.api.ConnoteNumberRequest;
import com.freight.mate.connote.api.ConnoteNumberResponse;
import com.freight.mate.connote.service.FreightMateConnoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class FreightMateConnoteController {

    private final FreightMateConnoteService freightMateService;

    @PostMapping("/v1/consignment/generate")
    public ResponseEntity<ConnoteNumberResponse> generateConsignmentNumber(@RequestBody @Validated final ConnoteNumberRequest connoteNumberRequest,
                                                                           final HttpServletRequest httpServletRequest,
                                                                           @RequestHeader final HttpHeaders requestHeaders) {
        return new ResponseEntity<>(freightMateService.generateConsignmentNumber(connoteNumberRequest, requestHeaders), HttpStatus.OK);
    }
}
