package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.exception.ConflictException;
import com.ethan.emsp.core.result.exception.NotFoundException;
import com.ethan.emsp.domain.event.EvseChangedEvent;
import com.ethan.emsp.domain.model.evse.*;
import com.ethan.emsp.domain.model.location.EvseCmdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class EvseCmdApplication {
    private final EvseDomainService evseDomainService;
    private final EvseCmdRepository evseCmdRepository;
    private final AppEventPublisher appEventPublisher;

    @Transactional
    public String create(CreateEvseCmd command) {
        Evse evse = evseDomainService.create(command);
        if (evseCmdRepository.exists(evse.getId())) {
            throw new ConflictException("EVSE already exists: " + evse.getId());
        }
        evseCmdRepository.save(evse);

        sendEvent(evse);

        return evse.getId().toString();
    }

    @Transactional
    public void changeStatus(ChangeStatusCmd command) {
        Evse evse = evseCmdRepository.getById(command.evseId());
        if (evse == null) {
            throw new NotFoundException("EVSE not found: " + command.evseId());
        }
        if (evse.getStatus().equals(command.newStatus())) {
            return;
        }
        evse.changeStatus(command.newStatus());
        evseCmdRepository.update(evse);

        sendEvent(evse);
    }

    @Transactional
    public boolean addConnector(AddConnectorCmd command) {
        Evse evse = evseCmdRepository.getById(command.evseId());
        if (evse == null) {
            throw new NotFoundException("EVSE not found: " + command.evseId());
        }
        evse.addConnector(command.connector());
        evseCmdRepository.update(evse);

        sendEvent(evse);

        return true;
    }

    private void sendEvent(Evse evse) {
        EvseChangedEvent event = EvseChangedEvent.of(evse.getId().getValue(), evse.getStatus().toString(), evse.getConnectors());
        appEventPublisher.publish(event);
    }
}
