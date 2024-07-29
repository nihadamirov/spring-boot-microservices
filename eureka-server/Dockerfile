FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY . .
RUN gradle clean assemble -x test

FROM openjdk:17-jdk-alpine
WORKDIR /eureka-server
COPY --from=build /spring-boot-microservices/build/libs/eureka-server-*.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
