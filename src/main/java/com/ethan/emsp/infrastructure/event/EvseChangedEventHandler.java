package com.ethan.emsp.infrastructure.event;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ethan.emsp.domain.event.EvseChangedEvent;
import com.ethan.emsp.infrastructure.event.assembler.EvseAssembler;
import com.ethan.emsp.infrastructure.event.assembler.LocationAssembler;
import com.ethan.emsp.infrastructure.event.repository.ConnectorViewRepository;
import com.ethan.emsp.infrastructure.event.repository.EvseViewRepository;
import com.ethan.emsp.infrastructure.event.repository.LocationViewRepository;
import com.ethan.emsp.infrastructure.persistence.event.mapper.ConnectorViewMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class EvseChangedEventHandler {

    private final LocationViewRepository locationViewRepository;
    private final EvseViewRepository evseViewRepository;
    private final ConnectorViewRepository connectorViewRepository;

    @Async
    @TransactionalEventListener
    public void handle(EvseChangedEvent event) {
        try {
            log.info("Received EvseChangedEvent: {}", event);
            EvseViewPO evseViewPO = EvseAssembler.toEvseView(event);
            evseViewRepository.saveOrUpdate(evseViewPO);

            connectorViewRepository.rebuildConnectorView(event.getEvseId(), event.getConnectors());
        } catch (Exception ex) {
            log.error("处理事件失败: {}, 错误: {}", event.getEventId(), ex.getMessage(), ex);

            // todo 记录到数据库表，扫表重试
        }
    }
}
