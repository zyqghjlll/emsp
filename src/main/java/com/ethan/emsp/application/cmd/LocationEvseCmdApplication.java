package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.exception.NotFoundException;
import com.ethan.emsp.domain.model.evse.AddEvseToLocationCmd;
import com.ethan.emsp.domain.model.evse.Evse;
import com.ethan.emsp.domain.model.evse.EvseAddedEvent;
import com.ethan.emsp.domain.model.location.Equipment;
import com.ethan.emsp.domain.model.location.EvseCmdRepository;
import com.ethan.emsp.domain.model.location.Location;
import com.ethan.emsp.domain.model.location.LocationCmdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class LocationEvseCmdApplication {
    private final LocationCmdRepository locationRepository;
    private final EvseCmdRepository evseCmdRepository;
    private final AppEventPublisher appEventPublisher;

    @Transactional
    public void addEvse(AddEvseToLocationCmd command) {
        Location location = locationRepository.getById(command.locationId());
        if (location == null) {
            throw new NotFoundException("Location not found: " + command.locationId());
        }

        Evse evse = evseCmdRepository.getById(command.evseId());
        if (evse == null) {
            throw new NotFoundException("EVSE not found: " + command.locationId());
        }

        location.addEquipment(new Equipment(evse.getId().toString(), evse.getStatus().toString(), LocalDateTime.now()));
        evse.assignToLocation(location.getId());
        evseCmdRepository.update(evse);

        // 发布领域事件
        appEventPublisher.publish(new EvseAddedEvent(location.getId().getValue(), evse.getId().toString()));
    }
}
