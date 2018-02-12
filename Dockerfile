FROM openjdk:8

COPY build/install/main/app.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
EXPOSE 8080
