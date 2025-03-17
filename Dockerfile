FROM eclipse-temurin:23-jdk-alpine AS builder
LABEL authors="jones"

WORKDIR /build

COPY mvnw pom.xml ./

COPY .mvn .mvn

COPY src src

RUN chmod +x mvnw && ./mvnw clean package -DskipTests


FROM eclipse-temurin:23-jre-alpine
WORKDIR /app

COPY --from=builder /build/target/app-management-service-0.0.1-SNAPSHOT.jar app-management-service.jar


ENTRYPOINT ["java", "-jar", "/app-management-service.jar"]