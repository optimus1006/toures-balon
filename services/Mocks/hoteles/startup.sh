#!/bin/bash
./mvnw clean package -DskipTests
docker build ./ -t hoteles
docker-compose up --build -d