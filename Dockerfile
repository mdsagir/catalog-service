# ---- Build Stage ----
FROM gradle:7.4-jdk17-alpine AS build

WORKDIR /app

# Copy the Gradle wrapper and source code
COPY gradlew .
COPY gradle /app/gradle
COPY src /app/src
COPY build.gradle /app
COPY settings.gradle /app

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Expose the application port (if applicable)
EXPOSE 8080

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
