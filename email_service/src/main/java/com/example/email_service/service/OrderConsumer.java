package com.example.email_service.service;

import com.example.basedomains.dto.OrderEvent;
import com.example.email_service.entity.Email;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

  @Autowired
  private SendEmailService sendEmailService;

  @KafkaListener(topics = "${spring.kafka.topic.name}",
                  groupId = "${spring.kafka.consumer.group-id}")
  public void consume(OrderEvent orderEvent) throws MessagingException {
    LOGGER.info(String.format("Order event received in email-service => %s", orderEvent.toString()));

    Email email = new Email();
    email.setToEmail("xzzhou09@outlook.com");
    email.setSubject("Your order has been placed");
    email.setBody("Test Body.");

    sendEmailService.sendEmail(email);
    LOGGER.info(String.format("Email Sent Successfully..."));
  }
}
