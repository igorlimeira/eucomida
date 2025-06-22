#!/usr/bin/env bash
# Build docker images for the Auth and Purchase Order APIs

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
ROOT_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
cd "$ROOT_DIR"

# Build Auth API image
docker build -t eucomida-auth -f auth/Dockerfile .

# Build Purchase Order API image
docker build -t eucomida-purchase-orders -f purchase-orders/Dockerfile .

echo "Docker images built successfully:"

# Running docker-compose to start the services
docker-compose -f ./assets/docker-compose.yml up -d
