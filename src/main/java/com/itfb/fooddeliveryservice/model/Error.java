package com.itfb.fooddeliveryservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itfb.fooddeliveryservice.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Message message;
    private String exception;
    private Object[] params;

}
