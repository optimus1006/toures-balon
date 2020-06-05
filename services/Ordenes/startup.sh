#!/bin/bash
#sudo java -Dquarkus.http.port=8181 -jar read-ordenes-1.0.0-SNAPSHOT-runner.jar 2>> read-ordenes.log
#sudo java -Dquarkus.http.port=8181 -jar read-ordenes-1.0.0-SNAPSHOT-runner.jar
#sudo nohup java -jar -Dquarkus.http.port=8181 -jar read-ordenes-1.0.0-SNAPSHOT-runner.jar 2>> read-ordenes.log &
sudo nohup java -jar -Dquarkus.http.port=8181 admin-ordenes-1.0.0-SNAPSHOT-runner.jar > admin-ordenes.log 2>&1 & echo $! > ./pid.file &
sudo nohup java -jar -Dquarkus.http.port=8193 read-ordenes-1.0.0-SNAPSHOT-runner.jar > read-ordenes.log 2>&1 & echo $! > ./pid2.file &

echo $! > ./pid.file &kill $(cat ./pid.file)