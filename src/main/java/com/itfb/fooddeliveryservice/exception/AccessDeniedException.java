package com.itfb.fooddeliveryservice.exception;

import com.itfb.fooddeliveryservice.model.Message;

public class AccessDeniedException extends BaseException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(Message msg, Object... params) {
        super(msg, params);
    }
}
