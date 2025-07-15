package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.domain.event.LocationUpdatedDomainEvent;
import com.ethan.emsp.domain.model.location.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class LocationCmdApplication {
    private final LocationDomainService locationDomainService;
    private final LocationCmdRepository locationRepository;
    private final AppEventPublisher appEventPublisher;

    @Transactional
    public String create(CreateLocationCmd command) {
        Location location = locationDomainService.create(command);
        locationRepository.save(location);
        appEventPublisher.publish(new LocationCreatedEvent(location.getId().toString(), location.getAttributes().getName()));
        return location.getId().toString();
    }

    public void update(UpdateLocationCmd command) {
        Location location = locationRepository.getById(command.id());
        if (location == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "Location not found: " + command.name());
        }
        LocationAttributes attributes = location.getAttributes();
        attributes.setName(command.name());
        attributes.setAddress(command.address());
        attributes.setBusinessHours(command.businessHours());
        location.updateAttributes(attributes);
        locationRepository.update(location);
        appEventPublisher.publish(new LocationUpdatedDomainEvent(location.getId().toString(), location.getAttributes().getName()));
    }
}
