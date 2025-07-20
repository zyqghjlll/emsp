package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.ethan.emsp.infrastructure.persistence.event.po.ConnectorViewPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConnectorQueryMapper {
    List<ConnectorViewPO> selectByEvseIds(@Param("evseIds") List<String> evseIds);
}
