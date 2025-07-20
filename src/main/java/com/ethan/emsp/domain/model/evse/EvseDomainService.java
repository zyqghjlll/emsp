package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.application.cmd.CreateEvseCmd;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EvseDomainService {
    private final EvseCmdRepository evseCmdRepository;

    public Evse create(CreateEvseCmd command) {
        EvseId evseId = EvseId.of(command.countryCode(), command.partyId(), command.localEvseId());
        return Evse.of(evseId);
    }
}
