package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.core.ddd.AggregateRoot;
import com.ethan.emsp.core.result.exception.ConflictException;
import com.ethan.emsp.domain.model.location.LocationId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Evse implements AggregateRoot<EvseId> {

    private final EvseId id;
    private LocationId locationId;
    private EvseStatus status;
    private final List<Connector> connectors = new ArrayList<>();
    private final static List<Connector> EMPTY_CONNECTORS = new ArrayList<>();

    public Evse(EvseId evseId, LocationId locationId, EvseStatus status, List<Connector> connectors) {
        this.id = evseId;
        this.locationId = locationId;
        this.status = status;
        this.connectors.addAll(connectors);
    }

    public Evse(EvseId evseId, EvseStatus status, List<Connector> connectors) {
        this(evseId, LocationId.empty(), status, connectors);
    }

    public Evse(EvseId evseId, LocationId locationId, EvseStatus status) {
        this(evseId, locationId, status, EMPTY_CONNECTORS);
    }

    public Evse(EvseId evseId, EvseStatus status) {
        this(evseId, status, EMPTY_CONNECTORS);
    }

    public static Evse of(EvseId evseId) {
        return new Evse(evseId, EvseStatus.INITIAL, EMPTY_CONNECTORS);
    }

    public void addConnector(Connector connector) {
        this.connectors.add(connector);
    }

    public void removeConnector(Connector connector) {
        this.connectors.remove(connector);
    }

    public void changeStatus(EvseStatus newStatus) {
        if (this.status.equals(newStatus)) {
            return;
        }
        if (!this.status.canTransitionTo(newStatus)) {
            throw new ConflictException("Invalid status transition from " + this.status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    @Override
    public EvseId getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Evse{" +
                "id=" + id +
                ", locationId=" + locationId +
                ", status=" + status +
                ", connectors=" + connectors +
                '}';
    }

    public List<Connector> getConnectors() {
        return Collections.unmodifiableList(connectors);
    }

    public void assignToLocation(LocationId newLocationId) {
        // 可以在这里添加业务规则
        this.locationId = newLocationId;
    }
}
