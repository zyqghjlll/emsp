package com.ethan.emsp.infrastructure.event.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EvseViewRepository {

    void saveOrUpdate(EvseViewPO evseViewPO);

    void associatedUpdate(String evseId, String locationId, LocalDateTime timestamp);
}
