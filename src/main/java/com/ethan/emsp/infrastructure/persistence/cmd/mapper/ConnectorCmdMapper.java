package com.ethan.emsp.infrastructure.persistence.cmd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.cmd.po.ConnectorPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConnectorCmdMapper extends BaseMapper<ConnectorPO> {
    List<ConnectorPO> selectByEvseId(String evseId);

    void deleteByEvseId(String string);

    void insertBatch(@Param("connectorList") List<ConnectorPO> connectorList);
}
