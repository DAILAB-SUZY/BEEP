FROM gradle:8.7-jdk17 AS backend-builder
WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:17
WORKDIR /app
COPY --from=backend-builder /app/build/libs/*.jar app.jar
