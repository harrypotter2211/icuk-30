# Stage 1: Build
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Create log directory to avoid permission issues
RUN mkdir -p /app/logs

# Copy JAR from build
COPY --from=build /app/target/*.jar app.jar

# Ensure the JAR has permissions to write logs
RUN chmod -R 777 /app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
