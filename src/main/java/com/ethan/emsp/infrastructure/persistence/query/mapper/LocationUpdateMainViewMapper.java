package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationUpdateDetailViewBO;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationUpdateMainViewBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationUpdateMainViewMapper extends BaseMapper<LocationUpdateMainViewBO> {
    List<LocationUpdateDetailViewBO> selectByLastUpdated(@Param("queryDto") LocationQueryDto queryDto);
}
