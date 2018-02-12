FROM openjdk:8

COPY build/install/main/restmock-1.0-capsule.jar /restmock/app.jar
CMD ["java", "-jar", "/restmock/app.jar"]
EXPOSE 8080
