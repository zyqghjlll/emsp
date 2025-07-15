package com.ethan.emsp.api.controller.dto;

import cn.hutool.core.util.ObjectUtil;
import com.ethan.emsp.domain.model.location.BusinessHours;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.domain.model.location.UpdateLocationCmd;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateLocationDto implements Serializable {
    private String id;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;

    public UpdateLocationCmd toCommand() {
        return new UpdateLocationCmd(
                LocationId.of(id),
                name,
                address,
                BusinessHours.of(openTime, closeTime)
        );
    }
}
