package com.ethan.emsp.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.po.LocationPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper extends BaseMapper<LocationPO> {
    // 如果需要自定义 SQL，可添加 @Select/@Update 等注解方法
}
