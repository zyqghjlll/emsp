package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.application.query.LocationQueryApplication;
import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationQueryController {

    private final LocationQueryApplication locationQueryApplication;

    @GetMapping("/{locationId}")
    public ResultMessage<LocationVo> getLocationById(@PathVariable String locationId) {
        return ResultMessage.success(locationQueryApplication.getLocationById(locationId));
    }

    @GetMapping
    public ResultMessage<PageResult<LocationPageVo>> listLocations(@ModelAttribute LocationQueryDto queryDto) {
        PageResult<LocationPageVo> pageReuslt = locationQueryApplication.listLocations(queryDto);
        return ResultMessage.success(pageReuslt);
    }
}
