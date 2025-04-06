#---------------------------------stage1-----------------------------------------
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests=true
 
#---------------------------------stage2-----------------------------------------
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/target/bank.jar
EXPOSE 8080
CMD ["java","-jar","/app/target/bank.jar"]
