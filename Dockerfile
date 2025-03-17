FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder #new base image added
workdir /app
copy . .
run mvn clean install -DskipTests=true
from openjdk:17-slim
workdir /app
COPY --from=builder /app/target/*.jar /app/app.jar
expose 8080
cmd ["java","-jar","/app/app.jar"]

