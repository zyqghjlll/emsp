package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.exception.ConflictException;
import com.ethan.emsp.core.result.exception.NotFoundException;
import com.ethan.emsp.domain.model.evse.*;
import com.ethan.emsp.domain.model.evse.EvseCmdRepository;
import com.ethan.emsp.infrastructure.common.LocalEvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvseCmdApplicationTest {

    @Mock
    private EvseDomainService mockEvseDomainService;
    @Mock
    private EvseCmdRepository mockEvseCmdRepository;
    @Mock
    private AppEventPublisher mockAppEventPublisher;

    private EvseCmdApplication evseCmdApplicationUnderTest;

    private final EvseId evseId = EvseId.of("US*ABC*EVSE123456");

    @BeforeEach
    void setUp() {
        evseCmdApplicationUnderTest = new EvseCmdApplication(mockEvseDomainService, mockEvseCmdRepository,
                mockAppEventPublisher);
    }

    @Test
    void testCreate() {
        // Setup
        final CreateEvseCmd command = new CreateEvseCmd(CountryCode.CN, PartyID.ABC, new LocalEvseId("123456"));

        // Configure EvseDomainService.create(...).
        final Evse evse = new Evse(new EvseId("CN*ABC*123456"), LocationId.empty(), EvseStatus.INITIAL);
        when(mockEvseDomainService.create(command)).thenReturn(evse);

        when(mockEvseCmdRepository.exists(any(EvseId.class))).thenReturn(false);

        // Run the test
        final String result = evseCmdApplicationUnderTest.createEvse(command);

        // Verify the results
        assertThat(result).isEqualTo("CN*ABC*123456");
        verify(mockEvseCmdRepository).save(any(Evse.class));
        verify(mockAppEventPublisher).publish(any(AppEvent.class));
    }

    @Test
    void testCreate_EvseCmdRepositoryExistsReturnsTrue() {
        // Setup
        final CreateEvseCmd command = new CreateEvseCmd(CountryCode.CN, PartyID.ABC, new LocalEvseId("123456"));

        // Configure EvseDomainService.create(...).
        final Evse evse = new Evse(new EvseId("CN*ABC*123456"), LocationId.empty(), EvseStatus.INITIAL);
        when(mockEvseDomainService.create(command)).thenReturn(evse);

        when(mockEvseCmdRepository.exists(any(EvseId.class))).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> evseCmdApplicationUnderTest.createEvse(command)).isInstanceOf(ConflictException.class);
    }

    @Test
    void testChangeStatusWithSameStatus() {
        // Setup
        final ChangeStatusCmd command = new ChangeStatusCmd(evseId, EvseStatus.INITIAL);

        // Configure EvseCmdRepository.getById(...).
        final Evse evse = new Evse(evseId, LocationId.of("value"), EvseStatus.INITIAL);
        when(mockEvseCmdRepository.getById(any(EvseId.class))).thenReturn(evse);

        // Run the test
        evseCmdApplicationUnderTest.changeEvseStatus(command);

        // Verify the results
        verify(mockEvseCmdRepository, never()).save(any(Evse.class));
        verify(mockAppEventPublisher, never()).publish(any(AppEvent.class));
    }

    @Test
    void testChangeStatusWithCorrectDiffStatus() {
        // Setup
        final ChangeStatusCmd command = new ChangeStatusCmd(evseId, EvseStatus.AVAILABLE);

        // Configure EvseCmdRepository.getById(...).
        final Evse evse = new Evse(evseId, LocationId.empty(), EvseStatus.INITIAL);
        when(mockEvseCmdRepository.getById(any(EvseId.class))).thenReturn(evse);

        // Run the test
        evseCmdApplicationUnderTest.changeEvseStatus(command);

        // Verify the results
        verify(mockEvseCmdRepository).update(any(Evse.class));
        verify(mockAppEventPublisher).publish(any(AppEvent.class));
    }

    @Test
    void testChangeStatusWithInCorrectDiffStatus() {
        // Setup
        final ChangeStatusCmd command = new ChangeStatusCmd(evseId, EvseStatus.BLOCKED);

        // Configure EvseCmdRepository.getById(...).
        final Evse evse = new Evse(evseId, LocationId.empty(), EvseStatus.INITIAL);
        when(mockEvseCmdRepository.getById(any(EvseId.class))).thenReturn(evse);

        // Run the test
        assertThatThrownBy(() -> evseCmdApplicationUnderTest.changeEvseStatus(command)).isInstanceOf(ConflictException.class);

        // Verify the results
        verify(mockEvseCmdRepository, times(0)).save(any(Evse.class));
        verify(mockAppEventPublisher, times(0)).publish(any(AppEvent.class));
    }

    @Test
    void shouldValidateEvseStateMachine() {
        EvseStatus.selfValidate();
    }

    @Test
    void testChangeStatus_EvseCmdRepositoryGetByIdReturnsNull() {
        // Setup
        final ChangeStatusCmd command = new ChangeStatusCmd(new EvseId("US*ABC*EVSE123456"), EvseStatus.INITIAL);
        when(mockEvseCmdRepository.getById(any(EvseId.class))).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> evseCmdApplicationUnderTest.changeEvseStatus(command))
                .isInstanceOf(NotFoundException.class);
    }
}
