package com.FMNNotificationServer.FMNNotificationServer.messaging.emailing;

import com.FMNNotificationServer.FMNNotificationServer.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailing")
public class EmailController {

    @Autowired
    private EmailService emailService;

    //@RequestBody allows Spring to auto deserialize the json in the request body
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDetails emailDetails){
        String status = emailService.sendSimpleEmail(emailDetails);
        System.out.println("Email status: " + status);
        return status;
    }
}
