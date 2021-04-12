package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.component.MessageComponent;
import com.itfb.fooddeliveryservice.exception.*;
import com.itfb.fooddeliveryservice.model.Error;
import com.itfb.fooddeliveryservice.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageComponent messageComponent;

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
        return buildErrorResult(status, exception.getMsg(), exception.getParams());
    }

    private ResponseEntity<Object> buildErrorResult(HttpStatus httpStatus, Message message, Object... params) {
        String text;
        if (params.length == 0){
            text = messageComponent.getMessage(message.getText());
        } else {
            text = messageComponent.getMessageWithParams(message.getText(), params);
        }
        return new ResponseEntity<>(new Error(message.getCode(), text, params), httpStatus);
    }
}
