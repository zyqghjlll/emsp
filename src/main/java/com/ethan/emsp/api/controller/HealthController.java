package com.ethan.emsp.api.controller;

import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.infrastructure.monitor.VersionMonitor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@AllArgsConstructor
public class HealthController {

    private final VersionMonitor versionMonitor;

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
        return ResultMessage.success(versionMonitor.getVersion());
    }
}
