# ----------- Build Stage -----------
FROM maven:3.8.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only project files (avoids rebuilding on every code change)
COPY pom.xml .
COPY src ./src
COPY tests ./tests

# Run Maven build with tests
RUN mvn clean package

# ----------- Runtime Stage -----------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Create logs directory to prevent logback error
RUN mkdir -p /app/logs

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (adjust if needed)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
