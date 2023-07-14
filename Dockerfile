FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/app.jar /app/app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]