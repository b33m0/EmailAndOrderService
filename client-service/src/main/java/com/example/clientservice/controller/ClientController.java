package com.example.clientservice.controller;

import com.example.clientservice.entity.Order;
import com.example.clientservice.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/allOrders")
  public List<Order> getAllOrders() {
    return clientService.getAllOrders();
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }
}
