package com.ethan.emsp.api.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LocationPageVo implements Serializable {
    private String id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String openTime;
    private String closeTime;
    List<EvsePageVo> evses;
}
