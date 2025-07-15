package com.ethan.emsp.api.controller.vo;

public record ConnectorVo(
        String type,
        int voltague,
        int amperage,
        double power,
        String standard
) {
}
