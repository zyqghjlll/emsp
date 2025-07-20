package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.application.query.LocationQueryApplication;
import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
@AllArgsConstructor
public class QueryController {

    private final LocationQueryApplication locationQueryApplication;

    @GetMapping("/getById")
    public ResultMessage<LocationVo> getById(String locationId) {
        return ResultMessage.success(locationQueryApplication.getById(locationId));
    }

    @GetMapping("/queryByLastUpdated")
    public ResultMessage<PageResult<LocationPageVo>> queryByLastUpdated(@ModelAttribute LocationQueryDto queryDto) {
        PageResult<LocationPageVo> pageReuslt = locationQueryApplication.queryByLastUpdated(queryDto);
        return ResultMessage.success(pageReuslt);
    }
}
