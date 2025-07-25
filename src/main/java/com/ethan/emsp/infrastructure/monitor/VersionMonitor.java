package com.ethan.emsp.infrastructure.monitor;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VersionMonitor {

    @Value("${version:unknown}")
    private String version;

    @PostConstruct
    public void logVersion() {
        log.info("🚀 EMSP started. Deployed version: {}", version);
    }

    public String getVersion() {
        return version;
    }
}
