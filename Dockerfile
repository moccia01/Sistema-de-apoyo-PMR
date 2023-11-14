# syntax = docker/dockerfile:1.2
#
# Build stage
#
FROM maven:3.8.1-openjdk-17 AS build
COPY . .
RUN mvn clean package assembly:single -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/javalin-deploy-1.0-SNAPSHOT-jar-with-dependencies.jar tpa-pmr-g14.jar
# ENV PORT=8080
EXPOSE 8080
CMD ["java","-classpath","tpa-pmr-g14.jar","domain.server.App"]