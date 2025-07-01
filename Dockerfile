# Stage 1: Build with Maven + JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Runtime with lightweight JDK
FROM openjdk:17-slim

WORKDIR /app

# Create log directory to avoid logback errors
RUN mkdir -p /app/logs

# Copy JAR from builder
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
