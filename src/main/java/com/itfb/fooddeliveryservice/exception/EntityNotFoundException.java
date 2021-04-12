package com.itfb.fooddeliveryservice.exception;

import com.itfb.fooddeliveryservice.model.Message;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(Message msg, Object... params) {
        super(msg, params);
    }
}
