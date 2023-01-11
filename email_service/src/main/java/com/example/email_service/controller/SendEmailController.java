package com.example.email_service.controller;

import com.example.email_service.entity.Email;
import com.example.email_service.service.SendEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendEmail")
public class SendEmailController {
  @Autowired
  private SendEmailService sendEmailService;

  @PostMapping("/newEmail")
  public String sendEmail(@RequestBody Email email) throws MessagingException {
    sendEmailService.sendEmail(email);
    return "Email Sent Successfully...";
  }
}
