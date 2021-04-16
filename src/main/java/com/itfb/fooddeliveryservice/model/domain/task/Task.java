package com.itfb.fooddeliveryservice.model.domain.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    private Long id;
    private Long orderId;
    private String info;
    private TaskStatus status;
    private String address;
}
