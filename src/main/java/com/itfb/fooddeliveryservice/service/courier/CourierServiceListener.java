package com.itfb.fooddeliveryservice.service.courier;

import com.itfb.fooddeliveryservice.model.domain.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class CourierServiceListener {

    @RabbitListener(queues = "${message.courier-queue}")
    public void deliver(Task task){

    }
}
