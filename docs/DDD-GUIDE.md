# DDD架构学习指南

## 什么是DDD？

领域驱动设计（Domain-Driven Design，DDD）是一种软件开发方法论，强调以业务领域为核心来设计软件架构。

## 本项目的DDD实现亮点

### 1. 分层架构的完美实现

```
API Layer (接口层)
├── Controller - REST API控制器
├── DTO - 数据传输对象
└── VO - 视图对象

Application Layer (应用层)
├── Command - 命令处理
└── Query - 查询处理

Domain Layer (领域层)
├── Model - 领域模型
├── Service - 领域服务
├── Event - 领域事件
└── Repository - 仓储接口

Infrastructure Layer (基础设施层)
├── Persistence - 数据持久化
├── Event - 事件处理
└── Config - 配置管理
```

### 2. 聚合根设计示例

#### Location聚合根
```java
public class Location implements AggregateRoot<LocationId> {
    private final LocationId id;
    private LocationAttributes attributes;
    
    // 业务方法
    public void updateAttributes(LocationAttributes attributes) {
        this.attributes = attributes;
    }
}
```

#### Evse聚合根
```java
public class Evse implements AggregateRoot<EvseId> {
    private final EvseId id;
    private LocationId locationId;
    private EvseStatus status;
    private final List<Connector> connectors;
    
    // 业务方法
    public void changeStatus(EvseStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new ConflictException("Invalid status transition");
        }
        this.status = newStatus;
    }
}
```

### 3. 值对象设计

#### 标识值对象
```java
public record LocationId(String value) {
    public static LocationId of(String value) {
        return new LocationId(value);
    }
}
```

#### 业务值对象
```java
public record Coordinate(double latitude, double longitude) {
    public static Coordinate of(double latitude, double longitude) {
        return new Coordinate(latitude, longitude);
    }
}
```

### 4. 领域事件实现

#### 事件定义
```java
public record LocationChangedEvent(LocationId locationId, String eventType) implements DomainEvent {
    public static LocationChangedEvent of(Location location) {
        return new LocationChangedEvent(location.getId(), "LOCATION_CHANGED");
    }
}
```

#### 事件发布
```java
@Component
public class LocationCmdApplication {
    private final AppEventPublisher appEventPublisher;
    
    private void sendEvent(Location location) {
        appEventPublisher.publish(LocationChangedEvent.of(location));
    }
}
```

### 5. CQRS模式实现

#### 命令端
```java
@Transactional
public String createLocation(CreateLocationCmd command) {
    Location location = locationDomainService.create(command);
    locationRepository.save(location);
    sendEvent(location);
    return location.getId().getValue();
}
```

#### 查询端
```java
public PageResult<LocationPageVo> listLocations(LocationQueryDto queryDto) {
    return locationQueryRepository.listLocations(queryDto);
}
```

## DDD核心概念详解

### 1. 聚合根 (Aggregate Root)
- **定义**：聚合的入口点，负责维护聚合内部的一致性
- **特点**：外部只能通过聚合根访问聚合内的对象
- **示例**：Location、Evse

### 2. 实体 (Entity)
- **定义**：有唯一标识的对象
- **特点**：通过ID区分，状态可变
- **示例**：Connector

### 3. 值对象 (Value Object)
- **定义**：没有唯一标识的对象
- **特点**：不可变，通过属性值判断相等
- **示例**：LocationId、Coordinate、BusinessHours

### 4. 领域服务 (Domain Service)
- **定义**：处理跨聚合的业务逻辑
- **特点**：无状态，纯业务逻辑
- **示例**：LocationDomainService、EvseDomainService

### 5. 仓储 (Repository)
- **定义**：聚合的存储抽象
- **特点**：隐藏数据访问细节
- **示例**：LocationCmdRepository、EvseCmdRepository

## 学习路径建议

### 第一阶段：理解基础概念
1. 阅读《领域驱动设计》经典书籍
2. 理解分层架构的设计原则
3. 掌握聚合根、实体、值对象的概念

### 第二阶段：实践应用
1. 分析本项目的代码结构
2. 理解各个组件的职责
3. 尝试修改和扩展功能

### 第三阶段：深入理解
1. 学习CQRS模式
2. 理解事件驱动架构
3. 掌握DDD的最佳实践

## 常见问题解答

### Q: 为什么要使用DDD？
A: DDD能够帮助开发者更好地理解业务领域，设计出更符合业务需求的软件架构。

### Q: DDD适用于所有项目吗？
A: DDD更适合复杂业务领域的企业级项目，简单CRUD项目可能过度设计。

### Q: 如何识别聚合根？
A: 聚合根通常是业务中的核心概念，负责维护业务规则和一致性。

### Q: 值对象和实体的区别？
A: 实体有唯一标识，值对象通过属性值判断相等；实体可变，值对象不可变。

## 扩展学习资源

1. **书籍推荐**
   - 《领域驱动设计》- Eric Evans
   - 《实现领域驱动设计》- Vaughn Vernon
   - 《DDD实战》- 张逸

2. **在线资源**
   - DDD社区网站
   - 技术博客文章
   - 开源项目案例

3. **实践项目**
   - 本EMSP项目
   - 其他DDD开源项目
   - 个人练习项目

