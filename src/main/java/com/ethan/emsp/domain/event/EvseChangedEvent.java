package com.ethan.emsp.domain.event;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.domain.model.evse.Connector;
import lombok.Getter;

import java.util.List;

@Getter
public class EvseChangedEvent extends AppEvent {
    private final String evseId;
    private final String status;
    private final List<Connector> connectors;

    private EvseChangedEvent(String evseId, String status, List<Connector> connectors) {
        super("");
        this.evseId = evseId;
        this.status = status;
        this.connectors = connectors;
    }

    public static EvseChangedEvent of(String evseId, String status, List<Connector> connectors) {
        return new EvseChangedEvent(evseId, status, connectors);
    }
}
