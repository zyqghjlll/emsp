package com.ethan.emsp.infrastructure.persistence.repository;

import com.ethan.emsp.domain.location.Location;
import com.ethan.emsp.domain.location.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LocationRepositoryImpl implements LocationRepository {
    @Override
    public void save(Location location) {
        log.info("💾 Save location: {}", location.getId());
    }
}
