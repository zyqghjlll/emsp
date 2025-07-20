package com.ethan.emsp.infrastructure.persistence.event.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("db_connector_view")
public class ConnectorViewPO {
    @TableId
    private String id;
    private String evseId;
    private String connectorType;
    private Integer connectorVoltage;
    private Integer connectorAmperage;
    private Double connectorPower;
    private String connectorStandard;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
