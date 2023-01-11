package com.example.order_service.controller;

import com.example.basedomains.dto.OrderEvent;
import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderProducer;
import com.example.order_service.service.OrderService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;
  private final OrderProducer orderProducer;

  public OrderController(OrderService orderService,
      OrderProducer orderProducer) {
    this.orderService = orderService;
    this.orderProducer = orderProducer;
  }

  @PostMapping("/newOrder")
  public String newOrder(@RequestBody Order order) {

    order.setId(UUID.randomUUID().toString());

    OrderEvent orderEvent = new OrderEvent();
    orderEvent.setStatus("PENDING");
    orderEvent.setMessage("order is pending");

    orderProducer.sendMessage(orderEvent);
    orderService.saveOrder(order);

    return "New Order Placed";
  }

  @GetMapping("/allOrders")
  public List<Order> allOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/{order-id}")
  public Order getOrderById(@PathVariable("order-id") String id) {
    return orderService.getOrderById(id);
  }

}
