#!/bin/bash
set -e

# ========== 配置区 ==========
RESOURCE_GROUP="emsp-rg"
APP_NAME="emsp-app"
PLAN_NAME="ASP-emsprg-bb31"
REGION="Southeast Asia"
SKU="F1"
IMAGE_REPOSITORY="ghcr.io"
IMAGE_NAME="$IMAGE_REPOSITORY/zyqghjlll/emsp:latest"
GHCR_USER="zyqghjlll"
GHCR_PAT="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" # 可传入环境变量方式 export GHCR_PAT="..."

# ========== 登录 Azure（缓存登录可跳过） ==========
echo "🔐 登录 Azure..."
az login

# ========== 创建资源组（幂等） ==========
echo "📦 创建资源组..."
az group create \
  --name "$RESOURCE_GROUP" \
  --location "$REGION"

# ========== 创建 App Service Plan（Linux） ==========
echo "📦 创建 App Service Plan..."
az appservice plan create \
  --name "$PLAN_NAME" \
  --resource-group "$RESOURCE_GROUP" \
  --sku "$SKU" \
  --is-linux \
  --location "$REGION"

# ========== 创建 Web App（容器模式） ==========
echo "🚀 创建 Web App（Docker 镜像部署）..."
az webapp create \
  --name "$APP_NAME" \
  --plan "$PLAN_NAME" \
  --resource-group "$RESOURCE_GROUP" \
  --container-image-name "$IMAGE_NAME"

# ========== 设置 Spring Boot Profile ==========
echo "🌱 注入环境变量 SPRING_PROFILES_ACTIVE=azure..."
az webapp config appsettings set \
  --name "$APP_NAME" \
  --resource-group "$RESOURCE_GROUP" \
  --settings SPRING_PROFILES_ACTIVE=azure

# ========== 配置容器镜像认证（用于私有 GHCR 镜像） ==========
echo "🔧 配置私有镜像仓库访问..."
az webapp config container set \
  --name "$APP_NAME" \
  --resource-group "$RESOURCE_GROUP" \
  --container-image-name "$IMAGE_NAME" \
  --container-registry-url "https://$IMAGE_REPOSITORY" \
  --container-registry-user "$GHCR_USER" \
  --container-registry-password "$GHCR_PAT"

# ========== 开启容器日志，便于调试 ==========
echo "📄 启用日志..."
az webapp log config \
  --name "$APP_NAME" \
  --resource-group "$RESOURCE_GROUP" \
  --docker-container-logging filesystem

# ========== 完成 ==========
echo ""
echo "✅ 部署完成！访问地址： https://${APP_NAME}.azurewebsites.net"
echo "📡 查看实时日志： az webapp log tail --name $APP_NAME --resource-group $RESOURCE_GROUP"
