FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY . .
RUN gradle clean assemble -x test

FROM openjdk:17-jdk-alpine
WORKDIR /api-gateway
COPY --from=build /spring-boot-microservices/build/libs/api-gateway-*.jar api-gateway.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
