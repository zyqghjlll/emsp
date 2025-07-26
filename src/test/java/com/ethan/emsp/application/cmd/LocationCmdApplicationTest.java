package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.core.ddd.AppEventPublisher;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.domain.model.location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationCmdApplicationTest {

    @Mock
    private LocationDomainService mockLocationDomainService;
    @Mock
    private LocationCmdRepository mockLocationRepository;
    @Mock
    private AppEventPublisher mockAppEventPublisher;

    private LocationCmdApplication locationCmdApplicationUnderTest;

    @BeforeEach
    void setUp() {
        locationCmdApplicationUnderTest = new LocationCmdApplication(mockLocationDomainService, mockLocationRepository,
                mockAppEventPublisher);
    }

    @Test
    void testCreate() {
        // Setup
        final CreateLocationCmd command = new CreateLocationCmd("name", "address", new Coordinate(0.0, 0.0),
                new BusinessHours(LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0)));

        // Configure LocationDomainService.create(...).
        final Location location = new Location(LocationId.of("location_id"), LocationAttributes.builder()
                .name("name")
                .address("address")
                .coordinate(new Coordinate(0.0, 0.0))
                .businessHours(new BusinessHours(LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0)))
                .build());
        when(mockLocationDomainService.create(command)).thenReturn(location);

        // Run the test
        final String result = locationCmdApplicationUnderTest.createLocation(command);

        // Verify the results
        assertThat(result).isEqualTo("location_id");
        verify(mockLocationRepository).save(any(Location.class));
        verify(mockAppEventPublisher).publish(any(AppEvent.class));
    }

    @Test
    void testUpdate() {
        // Setup
        final UpdateLocationCmd command = new UpdateLocationCmd(LocationId.of("value"), "name", "address",
                new BusinessHours(LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0)));

        // Configure LocationCmdRepository.getById(...).
        final Location location = new Location(LocationId.of("value"), LocationAttributes.builder()
                .name("name")
                .address("address")
                .coordinate(new Coordinate(0.0, 0.0))
                .businessHours(new BusinessHours(LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0)))
                .build());
        when(mockLocationRepository.getById(any(LocationId.class))).thenReturn(location);

        // Run the test
        locationCmdApplicationUnderTest.updateLocation(command);

        // Verify the results
        verify(mockLocationRepository).update(any(Location.class));
        verify(mockAppEventPublisher).publish(any(AppEvent.class));
    }

    @Test
    void testUpdate_LocationCmdRepositoryGetByIdReturnsNull() {
        // Setup
        final UpdateLocationCmd command = new UpdateLocationCmd(LocationId.of("value"), "name", "address",
                new BusinessHours(LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0)));
        when(mockLocationRepository.getById(any(LocationId.class))).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> locationCmdApplicationUnderTest.updateLocation(command)).isInstanceOf(BusinessException.class);
    }
}
