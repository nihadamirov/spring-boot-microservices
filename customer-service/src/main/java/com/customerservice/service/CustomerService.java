package com.customerservice.service;

import com.customerservice.dto.CustomerDTO;
import com.customerservice.model.Customer;
import com.customerservice.repository.CustomerRepository;
import com.orderservice.dto.OrderDTO;
import com.productservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToDTO).orElse(null);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.name());
            customer.setEmail(customerDTO.email());
            Customer updatedCustomer = customerRepository.save(customer);
            return convertToDTO(updatedCustomer);
        }
        return null;
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public OrderDTO placeOrder(String customerId, List<String> productIds) {
        OrderDTO orderDTO = new OrderDTO(null, customerId, productIds);
        return restTemplate.postForObject("http://order-service/orders", orderDTO, OrderDTO.class);
    }


    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail());
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.id());
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        return customer;
    }
}
