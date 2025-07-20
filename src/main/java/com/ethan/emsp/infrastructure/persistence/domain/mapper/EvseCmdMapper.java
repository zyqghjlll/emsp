package com.ethan.emsp.infrastructure.persistence.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.emsp.infrastructure.persistence.domain.po.EvsePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvseCmdMapper extends BaseMapper<EvsePO> {
    // 如果需要自定义 SQL，可添加 @Select/@Update 等注解方法
}
