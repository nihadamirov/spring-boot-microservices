FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY settings.gradle gradlew gradle /spring-boot-microservices/
COPY gradle-wrapper.jar /spring-boot-microservices/gradle/wrapper/
COPY build.gradle /spring-boot-microservices/
RUN gradle wrapper

COPY . /spring-boot-microservices/
RUN gradle clean assemble -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /spring-boot-microservices/build/libs/*.jar /app/

EXPOSE 8761 8088 8081 8082 8083 8090

ENTRYPOINT ["java", "-jar", "/app/*.jar"]


