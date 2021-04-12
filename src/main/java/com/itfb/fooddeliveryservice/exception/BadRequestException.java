package com.itfb.fooddeliveryservice.exception;

import com.itfb.fooddeliveryservice.model.Message;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(Message msg, Object... params) {
        super(msg, params);
    }
}
