package com.FMNNotificationServer.FMNNotificationServer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private String subject;
    private String attachment;
}
