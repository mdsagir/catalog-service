# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy the build.gradle and settings.gradle files first to leverage caching
COPY build.gradle settings.gradle ./

# Download the dependencies
RUN ./gradlew --no-daemon dependencies

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew --no-daemon build

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-alpine AS runtime

WORKDIR /app

# Copy the JAR file built from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entrypoint for the container
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
