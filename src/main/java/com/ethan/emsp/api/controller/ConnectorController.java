package com.ethan.emsp.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connector")
public class ConnectorController {
    // 添加 Connector 到 EVSE
    @RequestMapping("/add")
    public String add() {
        return "添加 Connector 到 EVSE";
    }
}
