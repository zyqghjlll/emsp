package com.ethan.emsp.infrastructure.persistence.event.repository;

import com.ethan.emsp.infrastructure.event.repository.LocationViewRepository;
import com.ethan.emsp.infrastructure.persistence.event.mapper.LocationViewMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class LocationViewRepositoryImpl implements LocationViewRepository {

    private final LocationViewMapper locationViewMapper;
    private final IdGenerator idGenerator;

    @Override
    public void saveOrUpdate(LocationViewPO locationViewPO) {
        LocationViewPO existLocationViewPO = locationViewMapper.selectByLocationId(locationViewPO.getLocationId());
        if (existLocationViewPO == null) {
            locationViewPO.setId(idGenerator.generate());
            locationViewPO.setCreatedAt(locationViewPO.getUpdatedAt());
            locationViewMapper.insert(locationViewPO);
        } else {
            locationViewPO.setId(existLocationViewPO.getId());
            locationViewMapper.updateById(locationViewPO);
        }
    }

    @Override
    public void associatedUpdate(String locationId, LocalDateTime timestamp) {
        locationViewMapper.associatedUpdate(locationId, timestamp);
    }
}
