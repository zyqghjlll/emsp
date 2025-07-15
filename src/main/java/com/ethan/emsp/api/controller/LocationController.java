package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.AddEvseToLocationDto;
import com.ethan.emsp.api.controller.dto.CreateLocationDto;
import com.ethan.emsp.api.controller.dto.UpdateLocationDto;
import com.ethan.emsp.application.cmd.LocationCmdApplication;
import com.ethan.emsp.application.cmd.LocationEvseCmdApplication;
import com.ethan.emsp.core.result.ResultMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {

    private final LocationCmdApplication locationCmdApplication;
    private final LocationEvseCmdApplication locationEvseCmdApplication;

    @PostMapping("/create")
    public ResultMessage<String> create(@RequestBody CreateLocationDto dto) {
        String id = locationCmdApplication.create(dto.toCommand());
        return ResultMessage.success(id);
    }

    @PostMapping("/update")
    public ResultMessage<Void> update(@RequestBody UpdateLocationDto dto) {
        locationCmdApplication.update(dto.toCommand());
        return ResultMessage.success();
    }

    @PostMapping("/addEvse")
    public ResultMessage<Boolean> addEvse(@RequestBody AddEvseToLocationDto dto) {
        locationEvseCmdApplication.addEvse(dto.toCommand());
        return ResultMessage.success(true);
    }
}
