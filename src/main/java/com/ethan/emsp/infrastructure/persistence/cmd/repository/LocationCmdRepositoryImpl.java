package com.ethan.emsp.infrastructure.persistence.cmd.repository;

import com.ethan.emsp.domain.model.location.*;
import com.ethan.emsp.infrastructure.persistence.cmd.mapper.LocationCmdMapper;
import com.ethan.emsp.infrastructure.persistence.cmd.po.LocationPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LocationCmdRepositoryImpl implements LocationCmdRepository {

    private final LocationCmdMapper locationCmdMapper;

    public LocationCmdRepositoryImpl(LocationCmdMapper locationCmdMapper) {
        this.locationCmdMapper = locationCmdMapper;
    }

    @Override
    public void save(Location location) {
        LocationPO locationPO = new LocationPO();
        locationPO.setId(location.getId().getValue());
        locationPO.setName(location.getAttributes().getName());
        locationPO.setAddress(location.getAttributes().getAddress());
        locationPO.setLatitude(location.getAttributes().getCoordinate().latitude());
        locationPO.setLongitude(location.getAttributes().getCoordinate().longitude());
        locationPO.setOpenTime(location.getAttributes().getBusinessHours().openTime().toString());
        locationPO.setCloseTime(location.getAttributes().getBusinessHours().closeTime().toString());
        locationPO.setCreatedAt(location.getAttributes().getCreatedAt());
        locationCmdMapper.insert(locationPO);
    }

    @Override
    public Location getById(LocationId id) {
        LocationPO locationPO = locationCmdMapper.selectById(id.getValue());
        if (locationPO == null) {
            return null;
        }
        LocationAttributes attributes = LocationAttributes.builder()
                .name(locationPO.getName())
                .address(locationPO.getAddress())
                .coordinate(new Coordinate(locationPO.getLatitude(), locationPO.getLongitude()))
                .businessHours(BusinessHours.of(locationPO.getOpenTime(), locationPO.getCloseTime()))
                .createdAt(locationPO.getCreatedAt())
                .build();
        return new Location(LocationId.of(locationPO.getId()), attributes);
    }

    @Override
    public void update(Location location) {
        LocationPO locationPO = new LocationPO();
        locationPO.setId(location.getId().getValue());
        locationPO.setName(location.getAttributes().getName());
        locationPO.setAddress(location.getAttributes().getAddress());
        locationPO.setOpenTime(location.getAttributes().getBusinessHours().openTime().toString());
        locationPO.setCloseTime(location.getAttributes().getBusinessHours().closeTime().toString());
        locationCmdMapper.updateById(locationPO);
    }

    @Override
    public boolean existsByNameAndAddress(String name, String address) {
        return false;
    }
}
