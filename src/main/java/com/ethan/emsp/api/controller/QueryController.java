package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.EvsePageVo;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.application.query.LocationQueryApplication;
import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.domain.model.location.Location;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
@AllArgsConstructor
public class QueryController {

    private final LocationQueryApplication locationQueryApplication;

    @GetMapping("/queryByLastUpdated")
    public ResultMessage<PageResult<LocationPageVo>> queryByLastUpdated(@ModelAttribute LocationQueryDto queryDto) {
        PageResult<LocationPageVo> pageReuslt = locationQueryApplication.queryByLastUpdated(queryDto);
        return ResultMessage.success(pageReuslt);
    }
}
