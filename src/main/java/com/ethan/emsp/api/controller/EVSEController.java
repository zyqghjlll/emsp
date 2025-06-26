package com.ethan.emsp.api.controller;

import com.ethan.emsp.api.controller.dto.AddEVSEDto;
import com.ethan.emsp.core.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evse")
public class EVSEController {

    /**
     * 添加 EVSE 到指定 Location（校验 EVSE ID 格式）
     * @param dto 入参
     * @return ResultMessage<String>
     */
    @PostMapping("/add")
    public ResultMessage<String> add(@RequestBody AddEVSEDto dto) {
        return ResultMessage.success("添加成功");
    }

    // EVSE 状态变更（需遵循状态机）
    @PostMapping("/changeStatus")
    public ResultMessage<String> changeStatus() {
        return ResultMessage.success("更新成功");
    }
}
