package com.ethan.emsp.infrastructure.event;

import com.ethan.emsp.domain.event.LocationEvseChangedEvent;
import com.ethan.emsp.infrastructure.event.repository.EvseViewRepository;
import com.ethan.emsp.infrastructure.event.repository.LocationViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class LocationEvseChangedEventHandler {

    private final LocationViewRepository locationViewRepository;
    private final EvseViewRepository evseViewRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(LocationEvseChangedEvent event) {
        try {
            log.info("Received LocationEvseChangedEvent: {}", event);
            locationViewRepository.associatedUpdate(event.getLocationId(), event.getTimestamp());
            evseViewRepository.associatedUpdate(event.getEvseId(), event.getLocationId(), event.getTimestamp());
        } catch (Exception ex) {
            log.error("处理事件失败: {}, 错误: {}", event.getEventId(), ex.getMessage(), ex);

            // todo 记录到数据库表，扫表重试
        }
    }
}
