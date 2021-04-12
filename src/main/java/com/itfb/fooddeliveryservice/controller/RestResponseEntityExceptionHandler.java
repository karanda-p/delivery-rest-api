package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.exception.*;
import com.itfb.fooddeliveryservice.model.Error;
import com.itfb.fooddeliveryservice.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
            BadRequestException.class,
            AccessDeniedException.class,
            OperationNotAllowedException.class,
            DisabledException.class
    })
    protected ResponseEntity<Object> handleBaseException(BaseException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EntityNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof DisabledException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        } else if (exception instanceof OperationNotAllowedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
        }
        return buildErrorResult(exception.getMsg(), status, exception, exception.getParams());
    }

    private ResponseEntity<Object> buildErrorResult(Message message, HttpStatus httpStatus, BaseException ex, Object... params) {
        return new ResponseEntity<>(new Error(message, ex.getMsg().getText(), params), httpStatus);
    }
}
