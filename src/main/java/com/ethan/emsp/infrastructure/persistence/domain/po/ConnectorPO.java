package com.ethan.emsp.infrastructure.persistence.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("db_connector")
@Data
public class ConnectorPO {
    @TableId
    private String id;
    private String evseId;
    private String type;
    private int voltage;
    private int amperage;
    private double power;
    private String standard;
}
