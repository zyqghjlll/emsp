package com.ethan.emsp.infrastructure.persistence.cmd.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("db_evse")
@Data
public class EvsePO {
    @TableId
    private String id;
    private String locationId;
    private String status;
}
