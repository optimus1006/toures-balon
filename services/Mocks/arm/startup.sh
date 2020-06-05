#!/bin/bash
#sudo java -Dquarkus.http.port=8181 -jar arm-1.0.0-SNAPSHOT-runner.jar 2>> arm.log
#sudo java -Dquarkus.http.port=8181 -jar arm-1.0.0-SNAPSHOT-runner.jar
#sudo nohup java -jar -Dquarkus.http.port=8181 -jar arm-1.0.0-SNAPSHOT-runner.jar 2>> arm.log &
sudo java -jar -Dquarkus.http.port=8193 arm-1.0.0-SNAPSHOT-runner.jar > arm.log 2>&1
sudo nohup java -jar -Dquarkus.http.port=8193 arm-1.0.0-SNAPSHOT-runner.jar > arm.log 2>&1 &