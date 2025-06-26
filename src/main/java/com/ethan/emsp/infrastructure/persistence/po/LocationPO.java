package com.ethan.emsp.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("db_location")
@Data
public class LocationPO {
    @TableId
    private String id;
    private String name;
    private String description;
    private String address;
    private String contact;

}
