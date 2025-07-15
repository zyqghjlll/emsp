package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.AddConnectorDto;
import com.ethan.emsp.api.controller.dto.ChangeStatusDto;
import com.ethan.emsp.api.controller.dto.CreateEvseDto;
import com.ethan.emsp.application.cmd.EvseCmdApplication;
import com.ethan.emsp.core.result.ResultMessage;
import com.ethan.emsp.domain.model.evse.Evse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evse")
@AllArgsConstructor
public class EvseController {

    private final EvseCmdApplication evseCmdApplication;

    /**
     * create
     * 添加 Evse 到指定 Location（校验 Evse ID 格式）
     * @param dto 入参
     * @return ResultMessage<String>
     */
    @PostMapping("/create")
    public ResultMessage<String> create(@RequestBody @Valid CreateEvseDto dto) {
        String evseId = evseCmdApplication.create(dto.toCommand());
        return ResultMessage.success(evseId);
    }

    // Evse 状态变更（需遵循状态机）
    @PostMapping("/changeStatus")
    public ResultMessage<Boolean> changeStatus(@RequestBody @Valid ChangeStatusDto dto) {
        boolean result = evseCmdApplication.changeStatus(dto.toCommand());
        return ResultMessage.success(result);
    }

    @PostMapping("/getById")
    public ResultMessage<Boolean> addConnector(@RequestBody @Valid AddConnectorDto dto) {
        boolean result = evseCmdApplication.addConnector(dto.toCommand());
        return ResultMessage.success(result);
    }
}
