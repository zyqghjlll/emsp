package com.ethan.emsp.infrastructure.persistence.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface EvseViewMapper extends BaseMapper<EvseViewPO> {
    EvseViewPO selectByEvseId(String evseId);

    void associatedUpdate(String evseId, String locationId, LocalDateTime timestamp);
}
