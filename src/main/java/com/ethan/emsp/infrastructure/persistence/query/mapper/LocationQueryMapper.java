package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationQueryMapper {
    List<LocationViewPO> selectByLastUpdated(@Param("queryDto") LocationQueryDto queryDto);
}
