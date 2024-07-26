# Stage 1: Build
FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY . .
RUN gradle clean assemble -x test
# RUN gradle clean build -x test

# Stage 2: Run
FROM openjdk:17-jdk-alpine
WORKDIR /order-service
COPY --from=build /spring-boot-microservices/build/libs/order-service-*.jar order-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "order-service.jar"]
