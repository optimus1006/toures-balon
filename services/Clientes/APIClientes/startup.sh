#!/bin/bash
./mvnw clean package -DskipTests
docker build ./ -t apiclientsapp
docker-compose up --build -d