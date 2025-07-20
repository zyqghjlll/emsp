package com.ethan.emsp.infrastructure.persistence.event.repository;

import com.ethan.emsp.infrastructure.event.repository.EvseViewRepository;
import com.ethan.emsp.infrastructure.persistence.event.mapper.EvseViewMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class EvseViewRepositoryImpl implements EvseViewRepository {

    private final EvseViewMapper evseViewMapper;
    private final IdGenerator idGenerator;

    @Override
    public void saveOrUpdate(EvseViewPO evseViewPO) {
        EvseViewPO existEvseViewPO = evseViewMapper.selectByEvseId(evseViewPO.getEvseId());
        if (existEvseViewPO == null) {
            evseViewPO.setId(idGenerator.generate());
            evseViewPO.setCreatedAt(evseViewPO.getUpdatedAt());
            evseViewMapper.insert(evseViewPO);
        } else {
            evseViewPO.setId(existEvseViewPO.getId());
            evseViewMapper.updateById(evseViewPO);
        }
    }

    @Override
    public void associatedUpdate(String evseId, String locationId, LocalDateTime timestamp) {
        evseViewMapper.associatedUpdate(evseId, locationId, timestamp);
    }
}
