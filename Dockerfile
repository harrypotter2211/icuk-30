# Stage 1: Build the application using Maven and JDK 17
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Run Maven build including tests
RUN mvn clean package

# Stage 2: Run the app in a lightweight JDK container
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Create a logs directory for logback
RUN mkdir -p logs

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the app port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
