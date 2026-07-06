#!/bin/zsh

docker network create booking-network
./mvnw clean package
docker compose up -d
