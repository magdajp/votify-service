FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/votify-service-1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
