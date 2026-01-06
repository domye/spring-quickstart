# Spring Quickstart

## 项目简介

Spring Quickstart 是一个个人使用的 Spring Boot 快速开发脚手架，集成了常用的开发工具和框架，旨在提供一个开箱即用的项目基础架构，帮助快速构建各类应用。这个脚手架专门针对个人开发习惯进行了优化，包含了日常开发中最常用的功能和配置。

## 技术栈

- **框架**: Spring Boot 2.7.6
- **数据库**: MySQL
- **ORM**: MyBatis-Plus 3.5.12
- **API文档**: Knife4j 4.4.0 (Swagger增强)
- **工具库**: Hutool 5.8.38
- **构建工具**: Maven
- **开发工具**: Lombok

## 功能特性

- 统一响应结果封装
- 全局异常处理
- 参数校验
- API文档自动生成
- 逻辑删除支持
- 分页查询支持
- 健康检查接口

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.5+
- MySQL 5.7+

### 使用步骤

1. 克隆或复制项目作为新项目的基础
```bash
git clone https://github.com/your-username/spring-quickstart.git your-new-project
cd your-new-project
```

2. 修改项目信息
- 修改 `pom.xml` 中的项目名称、groupId等信息
- 修改 `src/main/resources/application.yml` 中的应用名称和数据库配置

3. 配置数据库
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database
    username: your_username
    password: your_password
```

4. 创建数据库
```sql
CREATE DATABASE IF NOT EXISTS `your_database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

5. 运行项目
```bash
mvn spring-boot:run
```

6. 访问应用
- 应用地址: http://localhost:8080
- API文档: http://localhost:8080/doc.html
- 健康检查: http://localhost:8080/health

### 项目定制

这个脚手架已经包含了一些基础配置和工具类，您可以根据具体需求进行扩展：
- 在 `controller` 包下添加新的控制器
- 在 `service` 包下实现业务逻辑
- 在 `mapper` 包下添加数据访问接口
- 在 `model` 包下定义实体类和DTO
- 在 `common` 包下添加通用工具类

## 项目结构

```
src/main/java/com/domye/demo
├── common          // 通用工具类和响应封装
├── config          // 配置类
├── controller      // 控制器层
├── exception       // 异常处理
├── handler         // 处理器
├── mapper          // 数据访问层
├── model           // 实体类和DTO
├── service         // 业务逻辑层
└── SpringQuickstartApplication.java // 应用启动类
```

## API文档

项目使用 Knife4j 生成 API 文档，启动项目后访问 http://localhost:8080/doc.html 查看完整的API文档。

### 示例API

#### 健康检查
- **请求**: `GET /health`
- **响应**:
```json
{
    "code": 0,
    "data": "ok",
    "message": "服务运行正常"
}
```

## 开发指南

### 代码规范

- 使用 Lombok 简化代码，减少样板代码
- 统一异常处理，所有异常通过全局异常处理器处理
- 统一响应格式，使用 `BaseResponse` 和 `ResultUtils` 封装返回结果
- API 使用 Swagger 注解，自动生成接口文档
- 使用 `ValidateUtils` 进行参数校验

### 数据库

- 使用 MyBatis-Plus 简化数据库操作，提供强大的 CRUD 功能
- 支持逻辑删除，通过 `isDelete` 字段标记数据状态
- 支持分页查询，使用 `PageRequest` 封装分页参数
- 数据库配置位于 `application.yml` 文件中

### 个人开发优化

这个脚手架已经针对个人开发习惯进行了以下优化：
- 预置了常用的工具类和配置
- 简化了项目初始化流程
- 提供了统一的代码风格和结构
- 集成了常用的依赖和插件
- 配置了开发环境的日志输出

### 扩展建议

在基于此脚手架开发新项目时，可以考虑以下扩展：
- 添加项目特定的业务模块
- 根据需要引入新的依赖
- 扩展通用工具类
- 添加更多中间件支持（如Redis、RabbitMQ等）

## 许可证

本项目采用 MIT 许可证，详情请参阅 [LICENSE](LICENSE) 文件。