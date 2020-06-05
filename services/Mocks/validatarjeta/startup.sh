#!/bin/bash
#sudo java -Dquarkus.http.port=8192 -jar validatarjeta-1.0.0-SNAPSHOT-runner.jar
sudo nohup java -jar -Dquarkus.http.port=8192 validatarjeta-1.0.0-SNAPSHOT-runner.jar > validatarjeta.log 2>&1 &