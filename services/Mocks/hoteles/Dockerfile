FROM openjdk:8-jdk-alpine
LABEL maintainer="andrea-gomezr@javeriana.edu.co"
VOLUME /tmp
EXPOSE 9091
RUN mkdir -p /hoteles/
RUN mkdir -p /hoteles/logs/
ADD target/hoteles-0.0.1-SNAPSHOT.jar /hoteles/hoteles.jar
ENTRYPOINT ["java","-Dserver.port=9090","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/hoteles/hoteles.jar"]