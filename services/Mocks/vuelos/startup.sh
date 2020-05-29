#!/bin/bash
./mvnw clean package -DskipTests
docker build ./ -t vuelos
docker-compose up --build -d