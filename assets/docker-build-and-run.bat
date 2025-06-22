@echo off
REM Build Docker images for the Auth and Purchase Order APIs

SET SCRIPT_DIR=%~dp0
cd /d %SCRIPT_DIR%\..

REM Build Auth API image
echo Building eucomida-auth Docker image...
docker build -t eucomida-auth -f auth\Dockerfile .

REM Build Purchase Order API image
echo Building eucomida-purchase-orders Docker image...
docker build -t eucomida-purchase-orders -f purchase-orders\Dockerfile .

echo.
echo Docker images built successfully.
echo.

REM Start services using docker-compose
docker-compose -f assets\docker-compose.yml up -d
