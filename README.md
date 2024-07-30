
# Spring Boot Microservices Project

## Overview

This project is a collection of microservices built using Spring Boot. It demonstrates various aspects of microservices architecture, including service discovery, load balancing, and API gateway integration.

## Project Structure

- **Eureka Server**: Provides service discovery and registration.
- **API Gateway (Zuul)**: Manages routing and load balancing for incoming requests to various microservices.
- **Order Service**: Manages order-related operations.
- **Product Service**: Handles product-related operations.
- **Customer Service**: Manages customer-related operations.
- **MongoDB**: Database used by services to store data.
- **Mongo Express**: Provides a web interface to interact with MongoDB.

## Technologies Used

- **Spring Boot**: Framework for building microservices.
- **Eureka**: Service discovery and registration.
- **Zuul**: API Gateway for routing and load balancing.
- **Ribbon**: Client-side load balancing.
- **MongoDB**: NoSQL database.
- **Docker**: Containerization of services.

## Setup and Running

1. **Clone the Repository**

   ```bash
   git clone https://github.com/nihadamirov/spring-boot-microservices.git
   cd spring-boot-microservices
   ```

2. **Build and Run the Services**

   Ensure Docker is installed and running. Then build and start the services using Docker Compose:

   ```bash
   docker-compose up --build
   ```

3. **Access the Services**

    - **Eureka Server**: [http://localhost:8761](http://localhost:8761)
    - **API Gateway**: [http://localhost:8088](http://localhost:8088)
    - **Order Service**: [http://localhost:8083/orders](http://localhost:8083/orders)
    - **Product Service**: [http://localhost:8082/products](http://localhost:8082/products)
    - **Customer Service**: [http://localhost:8081/customers](http://localhost:8081/customers)
    - **Mongo Express**: [http://localhost:8090](http://localhost:8090)

## Configuration

- **Eureka Server**: Configured to listen on port 8761.
- **API Gateway**: Configured to route requests to `order-service`, `product-service`, and `customer-service`.
- **Ribbon**: Used within the API Gateway for client-side load balancing.

## Testing

You can test the services by sending HTTP requests to the API Gateway. For example:

- **List All Orders**: `GET http://localhost:8088/orders`
- **Get a Product by ID**: `GET http://localhost:8088/products/{id}`
- **Get All Products**: `GET http://localhost:8088/products`
- **Create a Customer**: `POST http://localhost:8088/customers` with a JSON body

## Contribution

Feel free to fork the repository and submit pull requests. Contributions are welcome!



