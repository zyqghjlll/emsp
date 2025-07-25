package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.AddEvseToLocationDto;
import com.ethan.emsp.api.controller.dto.CreateLocationDto;
import com.ethan.emsp.api.controller.dto.UpdateLocationDto;
import com.ethan.emsp.application.cmd.LocationCmdApplication;
import com.ethan.emsp.application.cmd.LocationEvseCmdApplication;
import com.ethan.emsp.core.result.ResultMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {

    private final LocationCmdApplication locationCmdApplication;
    private final LocationEvseCmdApplication locationEvseCmdApplication;

    @PostMapping
    public ResultMessage<String> createLocation(@RequestBody CreateLocationDto dto) {
        String id = locationCmdApplication.createLocation(dto.toCommand());
        return ResultMessage.success(id);
    }

    @PatchMapping("/{locationId}")
    public ResultMessage<Void> updateLocation(@PathVariable String locationId, @RequestBody UpdateLocationDto dto) {
        locationCmdApplication.updateLocation(dto.toCommand(locationId));
        return ResultMessage.success();
    }

    @PostMapping("/{locationId}/evses")
    public ResultMessage<Boolean> addEvseToLocation(@PathVariable String locationId, @RequestBody AddEvseToLocationDto dto) {
        locationEvseCmdApplication.addEvseToLocation(dto.toCommand(locationId));
        return ResultMessage.success(true);
    }
}
