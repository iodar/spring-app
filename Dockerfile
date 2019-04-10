FROM openjdk:8u201-jdk-alpine

COPY target/spring-app-0.0.1-SNAPSHOT.jar /spring-app-0.0.1-SNAPSHOT.jar

ENV SPRING_DATASOURCE_URL jdbc:postgresql://localhost:5432/postgres
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres

ENTRYPOINT ["java", "-jar", "/spring-app-0.0.1-SNAPSHOT.jar"]

