# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

WORKDIR /opt/app

ARG JAR_FILE=target/spring-boot-weather-forecast.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

