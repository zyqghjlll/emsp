package com.ethan.emsp.api.controller;

import com.ethan.emsp.application.command.LocationCmdApplication;
import com.ethan.emsp.application.query.LocationQueryApplication;
import com.ethan.emsp.api.controller.dto.CreateLocationDto;
import com.ethan.emsp.core.result.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationQueryApplication locationApplication;
    @Autowired
    private LocationCmdApplication locationCommandApplication;

    @RequestMapping("/create")
    public ResultMessage<String> create(@RequestBody CreateLocationDto dto) {
        String id = locationCommandApplication.create(dto.toCommand());
        return ResultMessage.success(id);
    }

    // 更新位置
    @RequestMapping("/update")
    public ResultMessage<String> update() {
        throw new RuntimeException("测试异常");
    }
}
