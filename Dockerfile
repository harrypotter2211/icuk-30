# -----------------------------
# Stage 1: Build with Maven + JDK 17
# -----------------------------
FROM maven:3.8.6-openjdk-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy the entire project
COPY pom.xml .
COPY src ./src

# Download dependencies (improves cache usage)
RUN mvn dependency:go-offline

# Build the project with tests
RUN mvn clean package

# -----------------------------
# Stage 2: Lightweight JDK runtime
# -----------------------------
FROM openjdk:17-jdk-slim

# Create non-root user (optional but recommended)
RUN useradd -ms /bin/bash appuser

# Set working directory and permissions
WORKDIR /app
RUN mkdir -p /app/logs && chown -R appuser:appuser /app

# Copy built artifact from the build image
COPY --from=build /app/target/*.jar app.jar

# Change to non-root user
USER appuser

# Expose application port (adjust as needed)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
