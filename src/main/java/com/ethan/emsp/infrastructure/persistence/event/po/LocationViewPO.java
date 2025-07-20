package com.ethan.emsp.infrastructure.persistence.event.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("db_location_view")
public class LocationViewPO {
    @TableId
    private String id;
    private String locationId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String openTime;
    private String closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
