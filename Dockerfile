FROM eclipse-temurin:26-jdk AS build
WORKDIR /build
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src/main src/main
RUN sed -i 's/\r$//' mvnw && sh mvnw -B -Dmaven.test.skip=true package

FROM eclipse-temurin:26-jdk
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
USER 10001
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
