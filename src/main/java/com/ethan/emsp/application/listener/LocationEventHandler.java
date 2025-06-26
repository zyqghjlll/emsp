package com.ethan.emsp.application.listener;

import com.ethan.emsp.domain.location.LocationCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocationEventHandler {
    @Async
    @EventListener
    public void handle(LocationCreatedEvent event) {
        // 暂时记录日志。可做其他工作：审计、发送消息通知、索引同步等
        log.info("📣 Location created: {}", event.getLocationId());
    }
}
