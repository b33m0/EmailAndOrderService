package com.example.clientservice.feign;

import com.example.clientservice.entity.Order;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ORDER-SERVICE")
public interface OrderClient {

  @GetMapping(path = "/order/allOrders")
  public List<Order> getAllOrders();

}

