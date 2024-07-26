package com.orderservice.service;

import com.orderservice.dto.OrderDTO;
import com.orderservice.model.Order;
import com.orderservice.repository.OrderRepository;
import com.productservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::convertToDTO).orElse(null);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        // Check if all products exist
        for (String productId : orderDTO.productIds()) {
            ProductDTO product = restTemplate.getForObject("http://product-service/products/" + productId, ProductDTO.class);
            if (product == null) {
                throw new IllegalArgumentException("Product with id " + productId + " not found");
            }
        }

        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getProductIds());
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.id());
        order.setCustomerId(orderDTO.customerId());
        order.setProductIds(orderDTO.productIds());
        return order;
    }
}
