package com.ethan.emsp.infrastructure.event.repository;

import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LocationViewRepository {
    void saveOrUpdate(LocationViewPO locationViewPO);

    void associatedUpdate(String locationId, LocalDateTime timestamp);
}
