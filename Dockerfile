# 使用 Amazon Corretto 17 作为基础镜像
FROM amazoncorretto:17-alpine

# 设置工作目录
WORKDIR /app

# 拷贝构建后的 jar 包到容器中
COPY target/emsp-0.0.1-SNAPSHOT.jar app.jar

# 开放 HTTP 端口
EXPOSE 80

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]
