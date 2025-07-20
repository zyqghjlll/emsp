package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EvseQueryMapper {
    List<EvseViewPO> selectByLocationIds(@Param("locationIds") List<String> locationIds);
}
