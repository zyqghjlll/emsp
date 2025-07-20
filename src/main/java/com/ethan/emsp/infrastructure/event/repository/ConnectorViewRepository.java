package com.ethan.emsp.infrastructure.event.repository;

import com.ethan.emsp.domain.model.evse.Connector;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectorViewRepository {

    void rebuildConnectorView(String evseId, List<Connector> connectors);
}
