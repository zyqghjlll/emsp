package com.ethan.emsp.infrastructure.persistence.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.event.po.ConnectorViewPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConnectorViewMapper extends BaseMapper<ConnectorViewPO> {
}
