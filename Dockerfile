# Use official Maven image to build the app
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the project
COPY src ./src
RUN mvn clean package -DskipTests

# ------------------------------------------------------------------------------
# Create a minimal runtime image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV APP_HOME=/app \
    LOG_DIR=/app/logs

WORKDIR $APP_HOME

# Create logs directory with proper permissions
RUN mkdir -p /app/logs
WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Use a non-root user for better security
USER spring

# Expose app port (change if your app uses a different port)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
