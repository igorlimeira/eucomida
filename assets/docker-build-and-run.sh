#!/usr/bin/env bash
# Build docker images for the Auth and Purchase Order APIs

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
ROOT_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
cd "$ROOT_DIR"

# Create Docker network if it doesn't exist
if ! docker network ls --format '{{.Name}}' | grep -q '^network1$'; then
  echo "Creating Docker network 'network1'..."
  docker network create network1
else
  echo "Docker network 'network1' already exists."
fi

# Build Auth API image
echo "Building eucomida-auth Docker image..."
docker build -t eucomida-auth -f auth/Dockerfile .

# Build Purchase Order API image
echo "Building eucomida-purchase-orders Docker image..."
docker build -t eucomida-purchase-orders -f purchase-orders/Dockerfile .

echo "Docker images built successfully."

# Running docker-compose to start the services
docker-compose -f ./assets/docker-compose.yml up -d
