# Spring Boot Tutorial 教程应用

这是一个完整的Spring Boot教程应用示例，包含以下功能：

## 技术栈
- Spring Boot 3.2.0
- Spring Security 6
- Spring Data JPA
- PostgreSQL
- OpenAPI 3 (Swagger)
- Lombok

## 项目信息
- **项目名**: spring-boot-tutorial
- **包名**: com.example.tutorial
- **主类**: SpringBootTutorialApplication

## 快速开始

### 环境要求
- Java 17+
- Maven 3.6+
- PostgreSQL 14+

### 数据库设置
```bash
# 创建数据库
createdb demo_db

# 或者使用Docker
docker run --name postgres-demo -e POSTGRES_DB=demo_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15