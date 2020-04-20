#!/bin/bash
docker-compose stop
docker container rm postgres_container
docker container rm apiclientsapp
docker rmi apiclientsapp