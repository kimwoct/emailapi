package service;

import config.EmailConfig;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static config.EmailConfig.emailBccList;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String from, String subject, String text, String emailBccList) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("noreply.lingto@gmail.com");
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        message.setBcc(emailBccList);
        mailSender.send(message);
    }
}
