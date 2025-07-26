package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.exception.ConflictException;
import com.ethan.emsp.core.result.exception.InvalidArgumentException;
import com.ethan.emsp.core.result.exception.NotFoundException;
import com.ethan.emsp.domain.model.evse.Evse;
import com.ethan.emsp.domain.model.evse.EvseCmdRepository;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.location.Location;
import com.ethan.emsp.domain.model.location.LocationAttributes;
import com.ethan.emsp.domain.model.location.LocationCmdRepository;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.LocalEvseId;
import com.ethan.emsp.infrastructure.common.PartyID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationEvseCmdApplicationTest {

    @Mock
    private LocationCmdRepository locationCmdRepository;
    @Mock
    private EvseCmdRepository evseCmdRepository;
    @Mock
    private AppEventPublisher appEventPublisher;

    @InjectMocks
    private LocationEvseCmdApplication locationEvseCmdApplicationTest;

    private final EvseId evseId = EvseId.of(CountryCode.CN, PartyID.ABB, new LocalEvseId("123"));

    @Test
    void addEvseToLocationWithNullArgumentsShouldThrowInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> new AddEvseToLocationCmd(LocationId.of(null), EvseId.of(null)));
    }

    @Test
    void addEvseToLocationWithWrongLocationIdShouldThrowNotFoundException() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("wrong_location_id"), evseId);
        assertThrows(NotFoundException.class, () -> locationEvseCmdApplicationTest.addEvseToLocation(cmd));
    }

    @Test
    void addEvseToLocationWithWrongEvseIdShouldThrowNotFoundException() {
        LocationId locationId = LocationId.of("loc1");
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(locationId, evseId);

        // 能查到Location
        Location location = new Location(locationId, LocationAttributes.builder().build());
        when(locationCmdRepository.getById(cmd.locationId())).thenReturn(location);

        // 查不到Evse
        when(evseCmdRepository.getById(any())).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> locationEvseCmdApplicationTest.addEvseToLocation(cmd));
    }

    @Test
    void addEvseToLocation_LocationNotFound_ShouldThrowNotFoundException() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("loc1"), EvseId.of("CN*ABB*123"));
        when(locationCmdRepository.getById(any())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> locationEvseCmdApplicationTest.addEvseToLocation(cmd));
    }

    @Test
    void addEvseToLocation_EvseNotFound_ShouldThrowNotFoundException() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("loc1"), EvseId.of("CN*ABB*123"));
        when(locationCmdRepository.getById(any())).thenReturn(new Location(LocationId.of("loc1"), LocationAttributes.builder().build()));
        when(evseCmdRepository.getById(any())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> locationEvseCmdApplicationTest.addEvseToLocation(cmd));
    }

    @Test
    void addEvseToLocation_EvseUsedByOtherLocation_ShouldThrowConflictException() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("loc1"), EvseId.of("CN*ABB*123"));
        Location location = new Location(LocationId.of("loc1"), LocationAttributes.builder().build());
        Evse evse = new Evse(EvseId.of("CN*ABB*123"), LocationId.of("loc2"), null);
        when(locationCmdRepository.getById(any())).thenReturn(location);
        when(evseCmdRepository.getById(any())).thenReturn(evse);
        assertThrows(ConflictException.class, () -> locationEvseCmdApplicationTest.addEvseToLocation(cmd));
    }

    @Test
    void addEvseToLocation_EvseAlreadyInLocation_ShouldReturnIdempotent() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("loc1"), EvseId.of("CN*ABB*123"));
        Location location = new Location(LocationId.of("loc1"), LocationAttributes.builder().build());
        Evse evse = new Evse(EvseId.of("CN*ABB*123"), LocationId.empty(), null);
        when(locationCmdRepository.getById(any())).thenReturn(location);
        when(evseCmdRepository.getById(any())).thenReturn(evse);
        // 不抛异常即为通过
        locationEvseCmdApplicationTest.addEvseToLocation(cmd);
    }

    @Test
    void addEvseToLocation_Success_ShouldUpdateAndPublishEvent() {
        AddEvseToLocationCmd cmd = new AddEvseToLocationCmd(LocationId.of("loc1"), EvseId.of("CN*ABB*123"));
        Location location = new Location(LocationId.of("loc1"), LocationAttributes.builder().build());
        Evse evse = new Evse(EvseId.of("CN*ABB*123"), LocationId.empty(), null);
        when(locationCmdRepository.getById(any())).thenReturn(location);
        when(evseCmdRepository.getById(any())).thenReturn(evse);
        locationEvseCmdApplicationTest.addEvseToLocation(cmd);
        verify(evseCmdRepository).update(evse);
        verify(appEventPublisher).publish(any());
    }
}