package com.ethan.emsp.api.controller;

import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.domain.location.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {
    // 根据 last_updated 分页查询 Location 和其 EVSE
    @GetMapping("/location/evse")
    public ResultMessage<List<Location>> queryLocationEvse(@RequestParam("last_updated") String lastUpdated,
                                                           @RequestParam("page_size") int pageSize) {
        return ResultMessage.success(null);
    }
}
