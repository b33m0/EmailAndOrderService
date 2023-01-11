package com.example.email_service.service;

import com.example.email_service.entity.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class SendEmailService {

  private Logger logger = LoggerFactory.getLogger(SendEmailService.class);

  private JavaMailSender javaMailSender;
  private Configuration templates;

  public SendEmailService(JavaMailSender javaMailSender, Configuration templates) {
    this.javaMailSender = javaMailSender;
    this.templates = templates;
  }

  public void sendEmail(Email email) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

    String toEmail = email.getToEmail();
    String subject = "Your order status has changed";
    String body = setEmail(email);

    helper.setSubject(subject);
    helper.setTo(toEmail);
    helper.setText(body, true);
    javaMailSender.send(mimeMessage);
    logger.info("Request to send email: {}", toEmail);
  }

  private String setEmail(Email email) {
//    String toEmail = email.getToEmail();
//    String subject = "Your order status has changed";
    String body = "";
    try {
      Template t = templates.getTemplate("order-update.ftl");
      Map<String, String> map = new HashMap<>();
      map.put("TO_EMAIL_ADDRESS", email.getToEmail());
      body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
    } catch (Exception ex) {
      logger.info("Request to send email error: {}", ex.getMessage());
    }

    return body;
  }
}
