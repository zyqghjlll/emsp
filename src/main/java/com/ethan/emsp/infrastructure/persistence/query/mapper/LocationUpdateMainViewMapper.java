package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.infrastructure.persistence.query.po.LocationUpdateDetailViewPO;
import com.ethan.emsp.infrastructure.persistence.query.po.LocationUpdateMainViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationUpdateMainViewMapper extends BaseMapper<LocationUpdateMainViewPO> {
    List<LocationUpdateDetailViewPO> selectByLastUpdated(@Param("queryDto") LocationQueryDto queryDto);
}
