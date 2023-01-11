package com.example.clientservice.service;

import com.example.clientservice.entity.Order;
import com.example.clientservice.feign.OrderClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  private OrderClient orderClient;

  public List<Order> getAllOrders() {
    return orderClient.getAllOrders();
  }

}
