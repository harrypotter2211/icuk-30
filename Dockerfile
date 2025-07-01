# ---------- Build Stage ----------
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and resolve dependencies early (layer optimization)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Create logs directory for Logback
RUN mkdir -p logs

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
