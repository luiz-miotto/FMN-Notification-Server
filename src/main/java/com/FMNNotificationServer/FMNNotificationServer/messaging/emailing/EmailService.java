package com.FMNNotificationServer.FMNNotificationServer.messaging.emailing;

import com.FMNNotificationServer.FMNNotificationServer.models.EmailDetails;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    String sendSimpleEmail(EmailDetails emailDetails);

}
