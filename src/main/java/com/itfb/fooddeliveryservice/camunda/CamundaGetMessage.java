package com.itfb.fooddeliveryservice.camunda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CamundaGetMessage implements JavaDelegate {

    public String execute(String message){
        log.info("Camunda get message turn");
        message = "This message goes throw camunda: " + message;
        return message;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Camunda working process: " + this.getClass());
        String message = (String) delegateExecution.getVariable("startMessage");
        log.info("Generated message: " + message);
        delegateExecution.setVariable("startMessage", "Current message: " + message);
    }
}
