package com.ethan.emsp.infrastructure.persistence.query.view;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("db_location_update_detail_view")
@Data
public class LocationUpdateMainViewBO {
    @TableId
    private Long id;

    private String locationId;
    private String locationName;
    private String locationAddress;
    private String latitude;
    private String longitude;
    private String openTime;
    private String closeTime;

    private String evseId;
    private String evseStatus;

    private String connectorType;
    private Integer connectorVoltage;
    private Integer connectorAmperage;
    private Double connectorPower;
    private String connectorStandard;

    private String mainId;
}
