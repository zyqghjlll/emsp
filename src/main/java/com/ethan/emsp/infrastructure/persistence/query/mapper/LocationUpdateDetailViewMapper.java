package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.infrastructure.persistence.query.po.LocationUpdateDetailViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationUpdateDetailViewMapper extends BaseMapper<LocationUpdateDetailViewPO> {
    List<LocationUpdateDetailViewPO> selectByLastUpdated(@Param("queryDto") LocationQueryDto queryDto);
}
