package com.FMNNotificationServer.FMNNotificationServer.workers;

import com.FMNNotificationServer.FMNNotificationServer.messaging.emailing.EmailService;
import com.FMNNotificationServer.FMNNotificationServer.models.EmailDetails;
import com.FMNNotificationServer.FMNNotificationServer.models.MessageDetails;
import com.FMNNotificationServer.FMNNotificationServer.repositories.MessageDetailsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@Controller
public class EmailingWorker {

    @Autowired
     EmailService emailService;
    private EmailDetails emailDetails;

    @Autowired
    MessageDetailsRepository messageDetailsRepository;
    public EmailingWorker(){
        MessageDetails messageDetails = new MessageDetails();
        messageDetails.setRecipientEmail("luiz.l.miotto94@gmail.com");
        messageDetails.setMessageSubject("Buongiorno");
        messageDetails.setMessageBody("Here is  a test of the emailing component");
        messageDetails.setAttachment("");
        setDetails(messageDetails);
    }



    public EmailingWorker(MessageDetails messageDetails){
        this.emailDetails = new EmailDetails();
        this.emailDetails.setSubject(messageDetails.getMessageSubject());
        this.emailDetails.setMessageBody(messageDetails.getMessageBody());
        this.emailDetails.setRecipient(messageDetails.getRecipientEmail());
       // this.emailDetails.setAttachment(messageDetails.getAttachment());
    }

    public void setEmailDetails(MessageDetails messageDetails){
        this.emailDetails = new EmailDetails();
        this.emailDetails.setSubject(messageDetails.getMessageSubject());
        this.emailDetails.setMessageBody(messageDetails.getMessageBody());
        this.emailDetails.setRecipient(messageDetails.getRecipientEmail());
    }

    public void setDetails(MessageDetails messageDetails){
        this.emailDetails = new EmailDetails();
        this.emailDetails.setSubject(messageDetails.getMessageSubject());
        this.emailDetails.setMessageBody(messageDetails.getMessageBody());
        this.emailDetails.setRecipient(messageDetails.getRecipientEmail());
    }


    @PostMapping("/sendMail")
    public String sendSimpleEmail(@RequestBody EmailDetails emailDetails){
        this.emailDetails = emailDetails;
        String status = emailService.sendSimpleEmail(emailDetails);
        System.out.println("Emailing status: " + status);
        return status;
    }

    public String sendSimpleEmail(){
        this.emailDetails = emailDetails;
        String status = emailService.sendSimpleEmail(emailDetails);
        System.out.println("Emailing status: " + status);
        return status;
    }

    public List<MessageDetails> getAllEmails(){
        return (List<MessageDetails>) messageDetailsRepository.findAll();

    }


}
