package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(adjustTaskMessage(mail.getMessage()));
        return mailMessage;
    }

    private String adjustTaskMessage(String message) {

        int taskCount = extractTaskCountFromMessage(message);
        String taskLabel = (taskCount == 1) ? "task" : "tasks";
        return message.replace("tasks", taskLabel);
    }

    private int extractTaskCountFromMessage(String message) {
        try {
            String[] parts = message.split(" ");
            return Integer.parseInt(parts[parts.length - 2]);
        } catch (Exception e) {
            log.error("Failed to extract task count from message.", e);
            return 0;
        }
    }
}