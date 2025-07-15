
# 工作计划：

第一周目标：完成最小可运行架构（DDD骨架 + 心跳接口 + 本地运行 + Docker化 + GitHub提交）。

第二周：建模 Location / Evse / Connector，设计事件机制，完成部分核心 API。

第三周：接入数据校验、状态机、事件发布机制，编写单测，准备 Azure/AWS 部署。

第四周：部署上线，补充集成测试、CI 流水线、IaC（如有余力）。

# 详细设计
## 项目包结构
 ```
 com.yourcompany.evcharging
├── application            # 应用服务层（用例编排）
│   ├── service
│   └── dto
│
├── domain                 # 领域层
│   ├── model
│   │   ├── entity         # 实体（Location、Evse、Connector）
│   │   ├── vo             # 值对象（EvseId、坐标等）
│   │   ├── aggregate      # 聚合根（如 LocationAggregate）
│   │   └── event          # 领域事件
│   ├── repository         # 仓储接口
│   └── service            # 领域服务
│
├── gateway                # 网关适配层（“菱形架构”核心）
│   ├── northbound         # 北向：对上暴露 REST API
│   │   ├── controller
│   │   └── facade
│   └── southbound         # 南向：对接外部系统
│       ├── client         # 如调用 CPO、支付系统
│       └── adapter        # MQ、API 转换适配
│
├── infrastructure         # 基础设施层
│   ├── config             # SpringBoot 配置、统一响应等
│   ├── event              # 事件总线 / 发布订阅机制
│   └── persistence        # 数据持久化
│       ├── mapper
│       ├── entity         # DB层 Entity（与domain分离）
│       ├── converter      # DO ↔ domain 的转换器
│       └── repository     # Repository 实现
│
├── interfaces             # 接口层（对外暴露 DTO）
│   └── dto
│
├── shared                 # 通用模块（工具类、枚举等）
│   ├── util
│   ├── enums
│   └── constants
│
├── bootstrap              # 启动类、初始化器
│   └── Application.java

 ```

## 推荐工具 & 依赖初始化
- Java 17 或以上 
- Spring Boot 3.x 
- Maven 
- MyBatis-Plus or JPA（做数据落库） 
- MapStruct（实体转换） 
- Validation（如 Hibernate Validator） 
- Lombok 
- Docker + Docker Compose 
- GitHub Actions（CI） 
- Terraform（IaC，可后续加入）

## 交付要求
Java + RESTful + DDD + EDD + RDBMS + CI/CD + 云部署

| 编号 | 要求                             |
| -- | ------------------------------ |
| 1  | 使用 **DDD（领域驱动设计）**             |
| 2  | 使用 **事件驱动设计（EDD）**             |
| 3  | 编写完整 API + 单元测试，代码上传 GitHub    |
| 4  | 配置 **CI 管道（推荐 GitHub Action）** |
| 5  | **部署至 Azure 与 AWS（free tier）** |
| 6  | 数据验证 & 错误响应设计（如 400、409）       |
| 7  | 提供 `Dockerfile`                |
| 8  | 使用 RDBMS 并设计数据结构               |
| 9  | **语言要求：Java**                  |

## 关键实体
Location：充电站点，包括名称、地址、坐标、营业时间等。

Evse：一个充电设备单元，属于某个 Location，带有状态。

状态：AVAILABLE, BLOCKED, INOPERATIVE, REMOVED

### 状态转移规则：

INITIAL → AVAILABLE

AVAILABLE ↔ BLOCKED

AVAILABLE ↔ INOPERATIVE

ANY → REMOVED（不可逆）

Evse ID 格式：<CountryCode>*<PartyID>*<LocalEvseID>（如：US*ABC*Evse123456）

Connector：Evse 下的具体充电接口，包含标准、电压、功率等参数。

## 需要实现的接口（RESTful API）
创建、更新 Location

添加 Evse 到指定 Location（校验 Evse ID 格式）

Evse 状态变更（需遵循状态机）

添加 Connector 到 Evse

根据 last_updated 分页查询 Location 和其 Evse
