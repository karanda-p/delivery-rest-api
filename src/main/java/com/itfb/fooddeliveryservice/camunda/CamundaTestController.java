package com.itfb.fooddeliveryservice.camunda;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/camunda")
@RequiredArgsConstructor
public class CamundaTestController {

    private final RuntimeService runtimeService;
    private final CamundaShowMessage camundaShowMessage;

    @PostMapping
    public String test(@RequestBody String message) {

        Map<String, Object> map = new HashMap<>();
        map.put("startMessage", message);
        runtimeService.startProcessInstanceByKey("testProcess", map);

        return "done";
    }
}
