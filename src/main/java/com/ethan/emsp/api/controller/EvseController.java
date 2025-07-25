package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.AddConnectorDto;
import com.ethan.emsp.api.controller.dto.ChangeStatusDto;
import com.ethan.emsp.api.controller.dto.CreateEvseDto;
import com.ethan.emsp.application.cmd.EvseCmdApplication;
import com.ethan.emsp.core.result.ResultMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evses")
@AllArgsConstructor
public class EvseController {

    private final EvseCmdApplication evseCmdApplication;

    @PostMapping
    public ResultMessage<String> createEvse(@RequestBody @Valid CreateEvseDto dto) {
        String evseId = evseCmdApplication.createEvse(dto.toCommand());
        return ResultMessage.success(evseId);
    }

    @PatchMapping("/{evseId}/status")
    public ResultMessage<Void> changeEvseStatus(@PathVariable String evseId, @RequestBody @Valid ChangeStatusDto dto) {
        evseCmdApplication.changeEvseStatus(dto.toCommand(evseId));
        return ResultMessage.success();
    }

    @PostMapping("/{evseId}/connectors")
    public ResultMessage<Boolean> addConnectorToEvse(@PathVariable String evseId, @RequestBody @Valid AddConnectorDto dto) {
        boolean result = evseCmdApplication.addConnectorToEvse(dto.toCommand(evseId));
        return ResultMessage.success(result);
    }
}
