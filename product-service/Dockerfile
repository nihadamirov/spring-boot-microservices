# Stage 1: Build
FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY . .
RUN gradle clean assemble -x test
#RUN gradle clean build -x test

# Stage 2: Run
FROM openjdk:17-jdk-alpine
WORKDIR /product-service
COPY --from=build /spring-boot-microservices/build/libs/product-service-*.jar product-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "product-service.jar"]
