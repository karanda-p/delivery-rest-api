package com.itfb.fooddeliveryservice.camunda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CamundaShowMessage implements JavaDelegate {

    public String show(String message) {
        log.info(message);
        return message;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Camunda working process: " + this.getClass());
        log.info("Message: " + (String) delegateExecution
                .getVariable("startMessage"));
    }
}
