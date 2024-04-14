package com.FMNNotificationServer.FMNNotificationServer.workers;

import com.FMNNotificationServer.FMNNotificationServer.models.MessageDetails;
import com.FMNNotificationServer.FMNNotificationServer.repositories.MessageDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class ReminderTimeWorker {

    @Autowired
    private EmailingWorker emailingWorker;

    @Autowired
    private MessageDetailsRepository messageDetailsRepository;
    public ReminderTimeWorker(){

    }

    public List<MessageDetails> getMessageTimes(){
        List<MessageDetails> messageDetailsList = emailingWorker.getAllEmails();

        for(MessageDetails messageDetails: messageDetailsList){
            System.out.println(messageDetails.getSendTime());
        }


        return messageDetailsList;
    }

    public void getUpcomingReminders(){
        List<MessageDetails> messageDetailsList = emailingWorker.getAllEmails();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        for(MessageDetails messageDetails: messageDetailsList){
            LocalDateTime fifteenMinsBefore = LocalDateTime.parse(messageDetails.getSendTime()).plusMinutes(15);
            if(fifteenMinsBefore.until(now, ChronoUnit.MINUTES) <= 15 && fifteenMinsBefore.isAfter(now)){
                emailingWorker.setDetails(messageDetails);
                emailingWorker.sendSimpleEmail();
                System.out.println(messageDetails.getSendTime());
            }
        }
    }

    // UPDATE: this method is no longer required since server stores time as UTC 24 hour format
    //if time is pm, then add 12 hours and remove pm, else remove am and "0" in front of hour
    // Nota bene: There is a bug in this code because it doesn't handle 12am and 12pm properly, i.e. what idiot decided that it should be "10am, 11am, 12pm"...
    public String parseAMPM(MessageDetails messageDetails){
       String[] timeDateAndAMPM = messageDetails.getSendTime().split(" ");
       String time = timeDateAndAMPM[0];
       if(timeDateAndAMPM[1].equals("PM")){
           String[] timeAndDateParts = timeDateAndAMPM[0].split("T");
           String[] hoursAndMinutesParts = timeAndDateParts[1].split(":");
           String justHour = hoursAndMinutesParts[0];
           Integer adjustedHour = Integer.valueOf(justHour) + 12;
           time = timeAndDateParts[0] + "T" + adjustedHour.toString() + ":" + hoursAndMinutesParts[1];
           System.out.println("finalTime is " + time);
       } else{
           String[] timeAndDateParts = timeDateAndAMPM[0].split("T");
           String[] hoursAndMinutesParts = timeAndDateParts[1].split(":");
           String justHour = "0" + hoursAndMinutesParts[0];
           time = timeAndDateParts[0] + "T" + justHour + ":" + hoursAndMinutesParts[1];
       }
       return time;
    }

}
