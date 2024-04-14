package com.FMNNotificationServer.FMNNotificationServer.messaging.emailing;

import com.FMNNotificationServer.FMNNotificationServer.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String sender;

    @Value("${spring.mail.password")
    private String emailingServicePassword;

    public EmailServiceImpl(){

    }

    public String sendSimpleEmail(EmailDetails details){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMessageBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Email successfully sent...";
        } catch (Exception e){
            return "Error while sending email. Exeception: " + e.getMessage();
        }
    }

    public String sendSimpleEmailTwo(EmailDetails details){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMessageBody());
            mailMessage.setSubject(details.getSubject());
            System.out.println("message details here: " + mailMessage.getText());
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost("smtp.gmail.com");
            javaMailSender.setPort(587);
            javaMailSender.setUsername(sender);
            javaMailSender.setPassword(emailingServicePassword);
            javaMailSender.send(mailMessage);
            return "Email successfully sent...";
        } catch (Exception e){
            return "Error while sending email. Exeception: " + e.getMessage();
        }
    }
}
