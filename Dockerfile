# Stage 1: Build with Maven + JDK 17
FROM maven:3.8.6-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run with lightweight JDK 17
FROM openjdk:17-slim

WORKDIR /app

# Create log directory if needed
RUN mkdir -p /app/logs

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
