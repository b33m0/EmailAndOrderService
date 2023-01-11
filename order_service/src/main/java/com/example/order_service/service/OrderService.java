package com.example.order_service.service;

import com.example.order_service.entity.Order;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  private Logger logger = LoggerFactory.getLogger(OrderService.class);

  public Order saveOrder(Order order) {

    logger.info("Request to create new order: {}", order.toString());
    return orderRepository.save(order);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Order getOrderById(String id) {

    Optional<Order> orderobj = orderRepository.findById(id);
    if(orderobj.isEmpty()) {
      throw new OrderNotFoundException("Order with id : " + id + " dosen't exist in DB");
    }

    return orderobj.get();
  }
}
