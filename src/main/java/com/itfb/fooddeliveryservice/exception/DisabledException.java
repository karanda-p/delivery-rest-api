package com.itfb.fooddeliveryservice.exception;

import com.itfb.fooddeliveryservice.model.Message;

public class DisabledException extends BaseException {

    public DisabledException() {
        super();
    }

    public DisabledException(String message) {
        super(message);
    }

    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledException(Throwable cause) {
        super(cause);
    }

    public DisabledException(Message msg, Object... params) {
        super(msg, params);
    }
}
