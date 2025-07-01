# Use Maven image for building
FROM maven:3.8.6-openjdk-17 AS build

# Set work directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use lightweight image for running the app
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in runtime container
WORKDIR /app

# Create logs directory to prevent Logback error
RUN mkdir -p logs

# Copy the JAR from the build image
COPY --from=build /app/target/*.jar app.jar

# Expose port (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
