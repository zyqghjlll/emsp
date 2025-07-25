package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.location.BusinessHours;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.application.cmd.UpdateLocationCmd;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateLocationDto implements Serializable {
    private String name;
    private String address;
    private String openTime;
    private String closeTime;

    public UpdateLocationCmd toCommand(String locationId) {
        return new UpdateLocationCmd(
                LocationId.of(locationId),
                name,
                address,
                BusinessHours.of(openTime, closeTime)
        );
    }
}
