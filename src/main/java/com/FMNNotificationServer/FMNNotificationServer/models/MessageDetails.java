package com.FMNNotificationServer.FMNNotificationServer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="messageDetails")
@Entity
@AllArgsConstructor
public class MessageDetails {

    //senderEmail, senderId, recipientEmail, subject, body, attachment, messageId,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    private String senderEmail;
    private long senderId;
    private long recipientId;
    private long eventId;
    private String recipientEmail;
    private String messageSubject;
    private String messageBody;
    private String attachment;
    private String sendTime;
    private String messageType;

    public MessageDetails(){

    }



}
