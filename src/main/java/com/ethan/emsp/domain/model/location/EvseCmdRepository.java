package com.ethan.emsp.domain.model.location;


import com.ethan.emsp.domain.model.evse.Evse;
import com.ethan.emsp.domain.model.evse.EvseId;

public interface EvseCmdRepository {
    Evse getById(EvseId evseId);

    void update(Evse evse);

    void save(Evse evse);

    boolean exists(EvseId id);
}
