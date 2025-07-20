package com.ethan.emsp.infrastructure.persistence.event.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("db_evse_view")
public class EvseViewPO {
    @TableId
    private String id;
    private String locationId;
    private String evseId;
    private String evseStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
