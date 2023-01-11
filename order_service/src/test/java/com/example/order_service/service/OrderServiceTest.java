package com.example.order_service.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @MockBean
  private OrderRepository orderRepository;

  @BeforeEach
  void setup() {
    Optional<Order> order = Optional.of(new Order("123", "abc", 1, 200));
    Mockito.when(orderRepository.findById("123")).thenReturn(order);
  }

  @Test
  void getOrderById() {
    String orderName = "abc";
    Order order = orderService.getOrderById("123");
    assertEquals(orderName, order.getName());

  }
}