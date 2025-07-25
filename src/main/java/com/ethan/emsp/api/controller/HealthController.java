package com.ethan.emsp.api.controller;

import com.ethan.emsp.core.result.ResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Value("${BUILD_VERSION:unknown}")
    private String buildVersion;

    @GetMapping("/liveness")
    public ResultMessage<String> liveness() {
        return ResultMessage.success("ok");
    }

    @GetMapping("/readiness")
    public ResultMessage<String> readiness() {
        return ResultMessage.success("ok");
    }

    @GetMapping("/version")
    public ResultMessage<String> version() {
        return ResultMessage.success(buildVersion);
    }
}
