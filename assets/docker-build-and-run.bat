@echo off
REM Build Docker images for the Auth and Purchase Order APIs

SET SCRIPT_DIR=%~dp0
cd /d %SCRIPT_DIR%\..

REM Check if the network exists
FOR /F "tokens=*" %%i IN ('docker network ls --filter "name=network1" --format "{{.Name}}"') DO (
    IF /I "%%i"=="network1" (
        echo Docker network "network1" already exists.
        GOTO BuildImages
    )
)

REM Create the Docker network
echo Creating Docker network "network1"...
docker network create network1

:BuildImages
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