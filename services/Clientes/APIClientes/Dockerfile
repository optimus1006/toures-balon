FROM openjdk:8-jdk-alpine
LABEL maintainer="julio_pinzon@javeriana.edu.co"
VOLUME /tmp
EXPOSE 8082
RUN mkdir -p /apiclientsapp/
RUN mkdir -p /apiclientsapp/logs/
ADD target/touresbalon-api-clientes-1.0.0.jar /apiclientsapp/apiclientsapp.jar
ENTRYPOINT ["java","-Dserver.port=8082","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/apiclientsapp/apiclientsapp.jar"]
