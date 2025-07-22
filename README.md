# 工作计划：

第一周目标：完成最小可运行架构（DDD骨架 + 心跳接口 + 本地运行 + Docker化 + GitHub提交）。

第二周：建模 Location / Evse / Connector，设计事件机制，完成部分核心 API。

第三周：接入数据校验、状态机、事件发布机制，编写单测，准备 Azure/AWS 部署。

第四周：部署上线，补充集成测试、CI 流水线、IaC（如有余力）。

# 详细设计

## 项目包结构

 ```
emsp
├── api                          # 提供外部 API 接口层（REST/MQ）
│   ├── controller               # HTTP 接口控制器
│   │   ├── dto                  # 接收参数对象（Data Transfer Object）
│   │   └── vo                   # 返回结果对象（View Object）
│   └── mq                       # 消息消费监听器（Kafka 等）
├── application                  # 应用层，处理业务用例和指令封装
│   ├── cmd                      # 写操作命令（Command）
│   └── query                    # 读操作查询（Query）
├── core                         # 核心通用模块
│   ├── ddd                      # DDD 通用接口与注解（Entity, AggregateRoot 等）
│   └── result
│       └── exception            # 通用异常结构定义
├── domain                       # 领域层，包含业务模型和领域事件
│   ├── event                    # 领域事件定义
│   └── model
│       ├── evse                 # 充电终端 EVSE 相关领域模型
│       └── location             # 地点 Location 相关领域模型
├── infrastructure              # 基础设施层，提供技术实现与适配支持
│   ├── assembler                # DTO/VO 与 DO 的装配器
│   ├── common                   # 通用工具、常量等
│   ├── config                   # 项目配置类（如 SpringConfig）
│   ├── event                    # 事件总线实现（如 Kafka 发布）
│   │   ├── assembler            # 事件消息的转换
│   │   └── repository           # 事件持久化适配
│   ├── persistence              # 数据持久化层（DB）
│   │   ├── domain               # 领域对象的存储
│   │   │   ├── mapper           # MyBatis Mapper 接口
│   │   │   ├── po               # 数据库映射实体（Persistent Object）
│   │   │   └── repository       # 持久化仓储实现
│   │   ├── event                # 事件对象的存储
│   │   │   ├── mapper
│   │   │   ├── po
│   │   │   └── repository
│   │   └── query                # 视图查询（读模型）
│   │       ├── common
│   │       ├── mapper
│   │       ├── repository
│   │       └── view
│   └── utils
│       └── Impl                 # 工具实现类
└── integration                 # 第三方系统集成适配层
    ├── api                      # 外部系统 API 调用
    ├── event                    # 外部事件监听与处理
    └── messaging                # 消息协议封装

 ```

## 推荐工具 & 依赖初始化

- Java 17 或以上
- Spring Boot 3.x
- Maven
- MyBatis-Plus（做数据落库）
- MapStruct（实体转换）
- Validation（如 Hibernate Validator）
- Lombok
- Docker
- GitHub Actions（CICD）

## 交付要求

Java + RESTful + DDD + EDD + RDBMS + CI/CD + 云部署

| 编号 | 要求                             |
|----|--------------------------------|
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

- [数据库设计](docs/DB-DESIGN.md)

### 状态转移规则：

INITIAL → AVAILABLE

AVAILABLE ↔ BLOCKED

AVAILABLE ↔ INOPERATIVE

ANY → REMOVED（不可逆）

Evse ID 格式：

```
<CountryCode>*<PartyID>*<LocalEvseID>（如：US*ABC*Evse123456）
```

Connector：Evse 下的具体充电接口，包含标准、电压、功率等参数。

## 需要实现的接口（RESTful API）

创建、更新 Location

添加 Evse 到指定 Location（校验 Evse ID 格式）

Evse 状态变更（需遵循状态机）

添加 Connector 到 Evse

根据 last_updated 分页查询 Location 和其 Evse
