# EMSP项目快速开始指南

## 项目简介

EMSP (Electric Mobility Service Provider) 是一个基于DDD架构的电动汽车充电服务管理系统演示项目。本项目展示了如何在企业级Java项目中应用领域驱动设计模式。

## 环境要求

- **Java**: 17 或更高版本
- **Maven**: 3.6 或更高版本
- **数据库**: PostgreSQL 12 或更高版本
- **IDE**: IntelliJ IDEA 或 Eclipse

## 快速启动

### 1. 克隆项目
```bash
git clone <repository-url>
cd emsp
```

### 2. 配置数据库
```sql
-- 创建数据库
CREATE DATABASE emsp;

-- 执行初始化脚本
\i script/init.sql
```

### 3. 配置应用
编辑 `src/main/resources/application-dev.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/emsp
    username: your_username
    password: your_password
```

### 4. 启动应用
```bash
# 使用Maven启动
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 或者打包后启动
mvn clean package
java -jar target/emsp-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### 5. 验证启动
访问健康检查接口：
```bash
curl http://localhost:8080/health/liveness
```

## API接口测试

### 1. 创建充电站点
```bash
curl -X POST http://localhost:8080/locations \
  -H "Content-Type: application/json" \
  -d '{
    "name": "测试充电站",
    "address": "北京市朝阳区xxx街道",
    "latitude": 39.9042,
    "longitude": 116.4074,
    "openTime": "08:00",
    "closeTime": "22:00"
  }'
```

### 2. 创建充电设备
```bash
curl -X POST http://localhost:8080/evses \
  -H "Content-Type: application/json" \
  -d '{
    "countryCode": "CN",
    "partyId": "ABC",
    "localEvseId": "123456"
  }'
```

### 3. 查询充电站点
```bash
curl http://localhost:8080/locations
```

### 4. 变更设备状态
```bash
curl -X PATCH http://localhost:8080/evses/CN*ABC*123456/status \
  -H "Content-Type: application/json" \
  -d '{
    "newStatus": "AVAILABLE"
  }'
```

## 项目结构说明

```
src/main/java/com/ethan/emsp/
├── api/                    # 接口层
│   └── controller/        # REST API控制器
├── application/           # 应用层
│   ├── cmd/              # 命令处理
│   └── query/            # 查询处理
├── domain/               # 领域层
│   ├── model/            # 领域模型
│   └── event/            # 领域事件
└── infrastructure/       # 基础设施层
    ├── persistence/      # 数据持久化
    └── event/           # 事件处理
```

## 核心概念演示

### 1. DDD分层架构
- **API层**: 处理HTTP请求，参数验证
- **应用层**: 编排业务用例，事务管理
- **领域层**: 核心业务逻辑，领域模型
- **基础设施层**: 技术实现，数据访问

### 2. 聚合根设计
- **Location**: 充电站点聚合根
- **Evse**: 充电设备聚合根

### 3. 领域事件
- 状态变更时自动发布事件
- 支持异步事件处理

### 4. CQRS模式
- 读写分离设计
- 命令端处理写操作
- 查询端处理读操作

## 开发指南

### 1. 添加新的聚合根
1. 在 `domain/model` 中定义聚合根类
2. 在 `domain` 中定义仓储接口
3. 在 `infrastructure/persistence` 中实现仓储
4. 在 `application/cmd` 中添加应用服务
5. 在 `api/controller` 中添加控制器

### 2. 添加新的领域事件
1. 在 `domain/event` 中定义事件类
2. 在聚合根中发布事件
3. 在 `infrastructure/event` 中实现事件处理器

### 3. 添加新的API接口
1. 在 `api/controller/dto` 中定义DTO
2. 在 `api/controller/vo` 中定义VO
3. 在控制器中添加接口方法

## 测试

### 1. 单元测试
```bash
mvn test
```

### 2. 集成测试
```bash
mvn test -Dtest=*IntegrationTest
```

### 3. 测试覆盖率
```bash
mvn jacoco:report
```

## 部署

### 1. Docker部署
```bash
# 构建镜像
docker build -t emsp:latest .

# 运行容器
docker run -p 8080:8080 emsp:latest
```

### 2. 生产环境配置
- 修改 `application-prod.yml`
- 配置数据库连接池
- 配置日志级别
- 配置监控指标

## 常见问题

### Q: 启动时数据库连接失败？
A: 检查数据库配置，确保PostgreSQL服务正在运行。

### Q: 如何修改端口？
A: 在 `application.yml` 中设置 `server.port` 属性。

### Q: 如何查看详细日志？
A: 修改 `logback-spring.xml` 中的日志级别。

### Q: 如何添加新的业务功能？
A: 参考现有的聚合根实现，按照DDD分层架构添加代码。

## 学习资源

1. **项目文档**
   - [架构设计文档](ARCHITECTURE.md)
   - [DDD学习指南](DDD-GUIDE.md)
   - [数据库设计](DB-DESIGN.md)

2. **外部资源**
   - [Spring Boot官方文档](https://spring.io/projects/spring-boot)
   - [DDD社区](https://domainlanguage.com/)
   - [MyBatis-Plus文档](https://baomidou.com/)

## 技术支持

如有问题，请通过以下方式联系：
- 提交Issue到项目仓库
- 发送邮件到技术支持邮箱
- 查看项目Wiki页面

