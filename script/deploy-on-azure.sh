#!/bin/bash

set -e  # 遇到错误就退出

# === Step 1: 切换到脚本所在目录（即 script/）===
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# === Step 2: 切换到项目根目录（即 script/ 的上一级）===
cd ..
PROJECT_ROOT=$(pwd)
echo "📂 当前项目根目录: $PROJECT_ROOT"

# === Step 3: 配置参数 ===
RESOURCE_GROUP="emsp-rg"
APP_NAME="emsp-app"
PLAN_NAME="ASP-emsprg-b874"
REGION="Southeast Asia"
RUNTIME="JAVA|17-java17"
JAR_FILE_NAME="emsp-0.0.1-SNAPSHOT.jar"
JAR_FILE="$PROJECT_ROOT/target/emsp-0.0.1-SNAPSHOT.jar"

# === Step 4: 自动切换到 JDK 17（Mac 用户专用）===
if command -v /usr/libexec/java_home &> /dev/null; then
  export JAVA_HOME=$(/usr/libexec/java_home -v17)
  export PATH="$JAVA_HOME/bin:$PATH"
  echo "☕ 已切换 JAVA_HOME 到: $JAVA_HOME"
fi

# === Step 5: 打包项目 ===
echo "🔧 开始 Maven 打包..."
mvn clean package -DskipTests

# === Step 6: 检查 JAR 是否存在 ===
if [ ! -f "$JAR_FILE" ]; then
  echo "❌ JAR 文件不存在: $JAR_FILE"
  exit 1
else
  echo "✅ 找到 JAR 文件: $JAR_FILE"
fi

chmod +r "$JAR_FILE"

# === Step 7: Azure 资源检查与部署 ===

az login

# 1. 检查资源组
if ! az group show --name "$RESOURCE_GROUP" &> /dev/null; then
  echo "📦 创建资源组..."
  az group create --name "$RESOURCE_GROUP" --location "$REGION"
else
  echo "✅ 资源组已存在"
fi

# 2. 检查 Plan
if ! az appservice plan show --name "$PLAN_NAME" --resource-group "$RESOURCE_GROUP" &> /dev/null; then
  echo "📦 创建 App Service Plan..."
  az appservice plan create \
    --name "$PLAN_NAME" \
    --resource-group "$RESOURCE_GROUP" \
    --location "$REGION" \
    --sku B1 \
    --is-linux
else
  echo "✅ Plan 已存在"
fi

# 3. 检查 Web App
if ! az webapp show --name "$APP_NAME" --resource-group "$RESOURCE_GROUP" &> /dev/null; then
  echo "🚀 创建 Web App..."
  az webapp create \
    --resource-group "$RESOURCE_GROUP" \
    --plan "$PLAN_NAME" \
    --name "$APP_NAME" \
    --runtime "$RUNTIME"
else
  echo "✅ Web App 已存在"
fi

# 4. 设置启动命令
az webapp config set \
  --resource-group "$RESOURCE_GROUP" \
  --name "$APP_NAME" \
  --startup-file "java -jar /home/site/wwwroot/$JAR_FILE_NAME"

# 5. 部署 JAR 包
export AZURE_CLI_DISABLE_CONNECTION_VERIFICATION=1
az webapp deploy \
  --resource-group "$RESOURCE_GROUP" \
  --name "$APP_NAME" \
  --src-path "target/emsp-0.0.1-SNAPSHOT.jar" \
  --type jar

# === 完成提示 ===
echo "✅ 部署完成！你可以访问：https://${APP_NAME}.azurewebsites.net/health/ready"
echo "✅ 部署完成！可以运行以下命令实时查看日志："
echo "az webapp log tail --name $APP_NAME --resource-group $RESOURCE_GROUP"

