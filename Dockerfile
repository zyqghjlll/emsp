# 使用官方 JDK 镜像
FROM amazoncorretto:17-alpine

# 构建参数
ARG VERSION=dev
ENV VERSION=${VERSION}

# 构建时写入 version 到额外配置文件中
RUN echo "version=${VERSION}" > /app-version.properties

# 工作目录
WORKDIR /app

# 复制 jar 包
COPY target/emsp-0.0.1-SNAPSHOT.jar app.jar

# 启动时加载 application.properties + /app-version.properties
ENTRYPOINT ["java", "-Dspring.config.additional-location=classpath:/,file:/app-version.properties", "-jar", "app.jar"]
