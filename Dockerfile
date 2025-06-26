FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/EcoPick-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "EcoPick-0.0.1-SNAPSHOT.jar"]
