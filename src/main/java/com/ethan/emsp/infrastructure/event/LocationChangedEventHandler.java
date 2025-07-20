package com.ethan.emsp.infrastructure.event;

import com.ethan.emsp.domain.event.LocationChangedEvent;
import com.ethan.emsp.infrastructure.event.repository.LocationViewRepository;
import com.ethan.emsp.infrastructure.event.assembler.LocationAssembler;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class LocationChangedEventHandler {

    private final LocationViewRepository locationViewRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(LocationChangedEvent event) {
        try {
            log.info("Received LocationChangedEvent: {}", event);
            LocationViewPO locationViewPO = LocationAssembler.toLocationView(event);
            locationViewRepository.saveOrUpdate(locationViewPO);
        } catch (Exception ex) {
            log.error("处理事件失败: {}, 错误: {}", event.getEventId(), ex.getMessage(), ex);

            // todo 记录到数据库表，扫表重试
        }
    }
}
