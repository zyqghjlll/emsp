package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.location.CreateLocationCmd;

public class CreateLocationDto {
    private String name;
    private String address;
    private String coordinates;
    private Long businessHours;

    public CreateLocationCmd toCommand() {
        return new CreateLocationCmd(name, address, coordinates, businessHours);
    }
}
