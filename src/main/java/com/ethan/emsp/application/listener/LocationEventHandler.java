package com.ethan.emsp.application.listener;

import com.ethan.emsp.domain.model.location.LocationCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class LocationEventHandler {
    @Async
    @TransactionalEventListener
    public void handle(LocationCreatedEvent event) {
        // 暂时记录日志。可做其他工作：审计、发送消息通知、索引同步等
        try {
            log.info("📣 Location created: {}", event.getLocationId());

            // 模拟调用外部服务
            Thread.sleep(1000);

        } catch (Exception ex) {
            log.error("❌ 处理事件失败: {}, 错误: {}", event.getLocationId(), ex.getMessage(), ex);

            // 记录到数据库表，扫表重试
        }
    }
}
