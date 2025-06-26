package com.ethan.emsp.application.command;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.domain.location.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationCmdApplication {
    private final LocationDomainService locationDomainService;
    private final LocationRepository locationRepository;
    private final AppEventPublisher appEventPublisher;

    public String create(CreateLocationCmd command) {
        Location location = locationDomainService.create(command);
        locationRepository.save(location);
        appEventPublisher.publish(new LocationCreatedEvent(location.getId(), location.getAttributes().getName()));
        return location.getId();
    }
}
