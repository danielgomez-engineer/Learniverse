# Etapa 1: build con Gradle Wrapper y Java 17
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

# Etapa 2: imagen ligera solo con el JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
