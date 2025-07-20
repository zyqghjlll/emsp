package com.ethan.emsp.infrastructure.persistence.event.repository;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ethan.emsp.domain.model.evse.Connector;
import com.ethan.emsp.infrastructure.event.repository.ConnectorViewRepository;
import com.ethan.emsp.infrastructure.persistence.event.mapper.ConnectorViewMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.ConnectorViewPO;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ConnectorViewRepositoryImpl implements ConnectorViewRepository {

    private final ConnectorViewMapper connectorViewMapper;
    private final IdGenerator idGenerator;

    @Override
    @Transactional
    public void rebuildConnectorView(String evseId, List<Connector> connectors) {
        LambdaUpdateWrapper<ConnectorViewPO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ConnectorViewPO::getEvseId, evseId);
        connectorViewMapper.delete(wrapper);

        for (Connector connector : connectors) {
            ConnectorViewPO connectorViewPO = new ConnectorViewPO();
            connectorViewPO.setId(idGenerator.generate());
            connectorViewPO.setEvseId(evseId);
            connectorViewPO.setConnectorType(connector.type());
            connectorViewPO.setConnectorVoltage(connector.voltage());
            connectorViewPO.setConnectorAmperage(connector.amperage());
            connectorViewPO.setConnectorPower(connector.power());
            connectorViewPO.setConnectorStandard(connector.standard());
            connectorViewMapper.insert(connectorViewPO);
        }
    }
}
