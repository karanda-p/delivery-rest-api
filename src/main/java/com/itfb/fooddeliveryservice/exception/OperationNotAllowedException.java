package com.itfb.fooddeliveryservice.exception;

import com.itfb.fooddeliveryservice.model.Message;

public class OperationNotAllowedException extends BaseException {

    public OperationNotAllowedException() {
        super();
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }

    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }

    public OperationNotAllowedException(Message msg, Object... params) {
        super(msg, params);
    }
}
