package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.domain.event.LocationChangedEvent;
import com.ethan.emsp.domain.model.location.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Component
@AllArgsConstructor
public class LocationCmdApplication {
    private final LocationDomainService locationDomainService;
    private final LocationCmdRepository locationRepository;
    private final AppEventPublisher appEventPublisher;

    @Transactional
    public String createLocation(CreateLocationCmd command) {
        Location location = locationDomainService.create(command);
        locationRepository.save(location);

        sendEvent(location);

        return location.getId().getValue();
    }

    @Transactional
    public void updateLocation(UpdateLocationCmd command) {
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

        sendEvent(location);
    }

    private void sendEvent(Location location) {
        Assert.notNull(location, "location must not be null");
        Assert.notNull(location.getId(), "location.id must not be null");

        appEventPublisher.publish(LocationChangedEvent.of(location));
        log.info("Publishing LocationChangedEvent: {}", location.getId());
    }
}
