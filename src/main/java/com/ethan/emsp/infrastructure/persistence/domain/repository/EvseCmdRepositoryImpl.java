package com.ethan.emsp.infrastructure.persistence.domain.repository;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ethan.emsp.domain.model.evse.Connector;
import com.ethan.emsp.domain.model.evse.Evse;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.evse.EvseCmdRepository;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.persistence.domain.mapper.ConnectorCmdMapper;
import com.ethan.emsp.infrastructure.persistence.domain.mapper.EvseCmdMapper;
import com.ethan.emsp.infrastructure.persistence.domain.po.ConnectorPO;
import com.ethan.emsp.infrastructure.persistence.domain.po.EvsePO;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@AllArgsConstructor
public class EvseCmdRepositoryImpl implements EvseCmdRepository {
    private final EvseCmdMapper evseCmdMapper;
    private final ConnectorCmdMapper connectorCmdMapper;
    private final IdGenerator idGenerator;

    @Override
    public Evse getById(EvseId evseId) {
        EvsePO evsePO = evseCmdMapper.selectById(evseId.getValue());
        if (evsePO == null) {
            return null;
        }

        return new Evse(
                EvseId.of(evsePO.getId()),
                LocationId.of(evsePO.getLocationId()),
                EvseStatus.valueOf(evsePO.getStatus()),
                getConnectors(evseId)
        );
    }

    @Override
    public void update(Evse evse) {
        EvsePO evsePO = new EvsePO();
        evsePO.setId(evse.getId().getValue());
        evsePO.setLocationId(evse.getLocationId().getValue());
        evsePO.setStatus(evse.getStatus().name());
        evseCmdMapper.updateById(evsePO);
        saveConnectors(evse.getId(), evse.getConnectors());
    }

    @Override
    public void save(Evse evse) {
        EvsePO evsePO = new EvsePO();
        evsePO.setId(evse.getId().getValue());
        evsePO.setLocationId(evse.getLocationId().getValue());
        evsePO.setStatus(evse.getStatus().name());
        evseCmdMapper.insert(evsePO);
        saveConnectors(evse.getId(), evse.getConnectors());
    }

    @Override
    public boolean exists(EvseId id) {
        EvsePO evsePO = evseCmdMapper.selectById(id.getValue());
        return ObjectUtil.isNotEmpty(evsePO);
    }

    private List<Connector> getConnectors(EvseId evseId) {
        List<ConnectorPO> connectorPOS = connectorCmdMapper.selectByEvseId(evseId.getValue());
        return connectorPOS.stream().map(connectorPO -> new Connector(
                connectorPO.getType(),
                connectorPO.getVoltage(),
                connectorPO.getAmperage(),
                connectorPO.getPower(),
                connectorPO.getStandard()
        )).collect(Collectors.toList());
    }

    private void saveConnectors(EvseId evseId, List<Connector> connectors) {
        connectorCmdMapper.deleteByEvseId(evseId.getValue());
        List<ConnectorPO> connectorPOS = new ArrayList<>();
        for (Connector connector : connectors) {
            ConnectorPO connectorPO = new ConnectorPO();
            connectorPO.setId(idGenerator.generate());
            connectorPO.setEvseId(evseId.getValue());
            connectorPO.setType(connector.type());
            connectorPO.setVoltage(connector.voltage());
            connectorPO.setAmperage(connector.amperage());
            connectorPO.setPower(connector.power());
            connectorPO.setStandard(connector.standard());
            connectorPOS.add(connectorPO);
        }
        if (CollUtil.isNotEmpty(connectorPOS)) {
            connectorCmdMapper.insertBatch(connectorPOS);
        }
    }
}
