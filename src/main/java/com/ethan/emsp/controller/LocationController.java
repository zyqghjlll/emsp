package com.ethan.emsp.controller;

import com.ethan.emsp.application.query.LocationQueryApplication;
import com.ethan.emsp.controller.dto.CreateLocationDto;
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

    @RequestMapping("/create")
    public ResultMessage<String> create(@RequestBody CreateLocationDto dto) {
        throw new RuntimeException("测试异常");
    }

    // 更新位置
    @RequestMapping("/update")
    public ResultMessage<String> update() {
        throw new RuntimeException("测试异常");
    }
}
