FROM openjdk:8u201-jdk-alpine

COPY target/spring-app.jar /spring-app.jar

ENV SPRING_DATASOURCE_URL jdbc:postgresql://localhost:5432/postgres
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres

ENTRYPOINT ["java", "-jar", "/spring-app.jar"]

