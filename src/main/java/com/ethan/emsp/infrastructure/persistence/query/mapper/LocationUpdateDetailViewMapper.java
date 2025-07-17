package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationUpdateDetailViewBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationUpdateDetailViewMapper extends BaseMapper<LocationUpdateDetailViewBO> {
    List<LocationUpdateDetailViewBO> selectByLastUpdated(@Param("queryDto") LocationQueryDto queryDto);
}
