package com.ethan.emsp.infrastructure.persistence.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("db_location")
@Data
public class LocationPO {
    @TableId
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String openTime;
    private String closeTime;
    private LocalDateTime createdAt;
}
