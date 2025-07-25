package com.ethan.emsp.infrastructure.persistence.query.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectorQueryBO {
    private String evseId;
    private String connectorType;
    private Integer connectorVoltage;
    private Integer connectorAmperage;
    private Double connectorPower;
    private String connectorStandard;
    private LocalDateTime lastUpdated;
}
