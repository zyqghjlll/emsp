package com.ethan.emsp.api.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EvsePageVo implements Serializable {
    private String id;
    private String status;
    List<ConnectorVo> connectors;

    public EvsePageVo(String id, String status, List<ConnectorVo> connectors) {
        this.id = id;
        this.status = status;
        this.connectors = connectors;
    }
}
