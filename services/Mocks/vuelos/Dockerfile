FROM openjdk:8-jdk-alpine
LABEL maintainer="andrea-gomezr@javeriana.edu.co"
VOLUME /tmp
EXPOSE 9090
RUN mkdir -p /vuelos/
RUN mkdir -p /vuelos/logs/
ADD target/vuelos-0.0.1-SNAPSHOT.jar /vuelos/vuelos.jar
ENTRYPOINT ["java","-Dserver.port=9090","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/vuelos/vuelos.jar"]