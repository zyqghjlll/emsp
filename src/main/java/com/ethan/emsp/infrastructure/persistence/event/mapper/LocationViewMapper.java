package com.ethan.emsp.infrastructure.persistence.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface LocationViewMapper extends BaseMapper<LocationViewPO> {
    LocationViewPO selectByLocationId(@Param("locationId") String locationId);

    void associatedUpdate(@Param("locationId") String locationId, @Param("timestamp") LocalDateTime timestamp);
}
