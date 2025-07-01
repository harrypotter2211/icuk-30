# Use official Maven image with Java 17
FROM maven:3.8.6-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Run Maven build with tests
RUN mvn clean package

# -----------------------------
# Create a lightweight runtime image
# -----------------------------
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Create logs directory to prevent permission issues
RUN mkdir logs

# Copy the built jar from the builder image
COPY --from=build /app/target/*.jar app.jar

# Expose application port (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
