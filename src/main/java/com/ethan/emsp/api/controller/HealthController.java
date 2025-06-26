package com.ethan.emsp.api.controller;

import com.ethan.emsp.core.result.ResultMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/beat")
    public ResultMessage<String> beat() {
        return ResultMessage.success("ok");
    }

    @GetMapping("/ready")
    public ResultMessage<String> ready() {
        return ResultMessage.success("ok");
    }
}
