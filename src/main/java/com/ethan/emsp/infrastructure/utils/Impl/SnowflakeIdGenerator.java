package com.ethan.emsp.infrastructure.utils.Impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator implements IdGenerator {
    private final Snowflake snowflake;

    public SnowflakeIdGenerator() {
        this.snowflake = IdUtil.getSnowflake(1, 1); // workerId, datacenterId
    }

    @Override
    public String generate() {
        return String.valueOf(snowflake.nextId()); // 转成 String 返回
    }
}
