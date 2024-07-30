FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY . .
RUN gradle clean assemble -x test

FROM openjdk:17-jdk-alpine
WORKDIR /customer-service
COPY --from=build /spring-boot-microservices/build/libs/customer-service-*.jar customer-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
