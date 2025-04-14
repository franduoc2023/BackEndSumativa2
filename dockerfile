FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/bdget-0.0.1-SNAPSHOT.jar app.jar
COPY wallet /app/wallet
ENV TNS_ADMIN=/app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
