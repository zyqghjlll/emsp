package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.core.ddd.AggregateRoot;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.core.result.exception.NotFoundException;

import java.util.*;

/**
 * 充电站点聚合根
 * 
 * 作为充电站点的聚合根，负责维护站点信息的一致性。
 * 包含站点的基本信息如名称、地址、坐标、营业时间等。
 * 
 * 设计原则：
 * 1. 聚合根是外部访问聚合的唯一入口
 * 2. 负责维护聚合内部的一致性
 * 3. 提供业务方法而不是简单的getter/setter
 * 
 * @author Ethan
 * @since 1.0.0
 */
public class Location implements AggregateRoot<LocationId> {
    private final LocationId id;
    private LocationAttributes attributes;

    /**
     * 构造函数
     * 
     * @param id 站点标识，不能为空
     * @param attributes 站点属性，不能为空
     * @throws IllegalArgumentException 如果参数为空
     */
    public Location(LocationId id, LocationAttributes attributes) {
        this.id = Objects.requireNonNull(id, "Location ID must not be null");
        this.attributes = Objects.requireNonNull(attributes, "Location attributes must not be null");
    }

    @Override
    public LocationId getId() {
        return this.id;
    }

    /**
     * 获取站点属性
     * 
     * @return 站点属性对象
     */
    public LocationAttributes getAttributes() {
        return this.attributes;
    }

    /**
     * 更新站点属性
     * 
     * 这是聚合根提供的业务方法，用于更新站点的属性信息。
     * 在实际应用中，可以在这里添加业务规则验证。
     * 
     * @param attributes 新的站点属性
     */
    public void updateAttributes(LocationAttributes attributes) {
        this.attributes = attributes;
    }
}
