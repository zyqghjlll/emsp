package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.location.BusinessHours;
import com.ethan.emsp.domain.model.location.Coordinate;
import com.ethan.emsp.domain.model.location.CreateLocationCmd;

public class CreateLocationDto {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String openTime;
    private String closeTime;

    public CreateLocationCmd toCommand() {
        return new CreateLocationCmd(
                name,
                address,
                new Coordinate(latitude, longitude),
                BusinessHours.of(openTime, closeTime)
        );
    }
}
