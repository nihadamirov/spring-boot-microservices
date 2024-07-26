# Stage 1: Build
FROM gradle:8.8-jdk17 AS build
WORKDIR /spring-boot-microservices
COPY settings.gradle gradlew gradle /spring-boot-microservices/
COPY gradle-wrapper.jar /spring-boot-microservices/gradle/wrapper/
COPY build.gradle /spring-boot-microservices/
RUN gradle wrapper

COPY . /spring-boot-microservices/
RUN gradle clean assemble -x test
# RUN gradle clean build -x test

# Stage 2: Run
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the built jar files from the build stage
COPY --from=build /spring-boot-microservices/build/libs/*.jar /app/

# Expose the ports for the services
EXPOSE 8761 8088 8081 8082 8083

# Command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app/*.jar"]







## Rəsmi Docker imicindən istifadə edərək, Docker Compose quraşdırın
#FROM docker:20.10.7-dind
#
## Docker Compose quraşdırın
#RUN apk add --no-cache py3-pip \
#    && pip install docker-compose==2.9.0
#
## İş qovluğunu root layihənin kök qovluğu olaraq təyin edin
#WORKDIR /app
#
## Docker Compose faylını konteynerə köçürün
#COPY docker-compose.yml /app/
#
## Mikroservis qovluqlarını konteynerə köçürün
#COPY api-gateway /app/api-gateway
#COPY eureka-server /app/eureka-server
#COPY order-service /app/order-service
#COPY product-service /app/product-service
#COPY customer-service /app/customer-service
#
## Açıq portları müəyyən edin (isteğe bağlı, adətən Docker Compose tərəfindən idarə olunur)
#EXPOSE 8080 8081 8082 8083 8761
#
## Docker Compose-i başladın
#CMD ["docker-compose", "up"]
