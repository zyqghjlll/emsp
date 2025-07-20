package com.ethan.emsp.infrastructure.event.assembler;

import com.ethan.emsp.domain.event.EvseChangedEvent;
import com.ethan.emsp.domain.event.LocationChangedEvent;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;

public class EvseAssembler {
    public static EvseViewPO toEvseView(EvseChangedEvent event) {
        EvseViewPO evseViewPO = new EvseViewPO();
        evseViewPO.setEvseId(event.getEvseId());
        evseViewPO.setEvseStatus(event.getStatus());
        evseViewPO.setUpdatedAt(event.getTimestamp());
        return evseViewPO;
    }
}
