# Etapa 1: compilación del artefacto
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src ./src

RUN mvn -B clean package -DskipTests

# Etapa 2: ejecución
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY --from=build /app/target/ProyectoAlquiler-0.0.1-SNAPSHOT.jar app.jar

ENV SERVER_PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
