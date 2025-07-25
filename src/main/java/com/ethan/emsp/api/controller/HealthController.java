package com.ethan.emsp.api.controller;

import com.ethan.emsp.core.result.ResultMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/liveness")
    public ResultMessage<String> liveness() {
        return ResultMessage.success("ok");
    }

    @GetMapping("/readiness")
    public ResultMessage<String> readiness() {
        return ResultMessage.success("ok");
    }
}
