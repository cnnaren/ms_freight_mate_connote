package com.freight.mate.connote.exception;


import com.freight.mate.connote.api.Status;
import lombok.Data;

@Data
public class MicroserviceException extends RuntimeException {

    private Status status;

    public MicroserviceException(String msg) { super(msg); }

    public MicroserviceException(String msg, Status status) {
        super(msg);
        this.status = status;
    }
}
