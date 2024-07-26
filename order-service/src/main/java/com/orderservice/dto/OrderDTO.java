package com.orderservice.dto;

import java.util.List;

public record OrderDTO(
        String id,
        String customerId,
        List<String> productIds) {

}
