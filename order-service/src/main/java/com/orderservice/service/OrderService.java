package com.orderservice.service;

import com.orderservice.dto.OrderDTO;
import com.orderservice.model.Order;
import com.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
