package com.ethan.emsp.infrastructure.persistence.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("db_evse")
@Data
public class EvsePO {
    @TableId
    private String id;
    private String locationId;
    private String status;
}
