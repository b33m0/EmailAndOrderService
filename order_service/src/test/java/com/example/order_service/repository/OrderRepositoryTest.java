package com.example.order_service.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.order_service.entity.Order;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class OrderRepositoryTest {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  TestEntityManager testEntityManager;

  @BeforeEach
  void setup() {
    Order order = new Order("123", "abc", 1, 200);
    testEntityManager.persist(order);
  }

  @Test
  public void testFindById() {
    Order order = orderRepository.findById("123").get();
    assertEquals("abc", order.getName());
  }
}