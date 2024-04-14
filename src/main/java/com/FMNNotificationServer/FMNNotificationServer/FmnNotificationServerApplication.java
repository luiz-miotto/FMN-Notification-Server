package com.FMNNotificationServer.FMNNotificationServer;

import com.FMNNotificationServer.FMNNotificationServer.messaging.emailing.EmailService;
import com.FMNNotificationServer.FMNNotificationServer.messaging.emailing.EmailServiceImpl;
import com.FMNNotificationServer.FMNNotificationServer.models.EmailDetails;
import com.FMNNotificationServer.FMNNotificationServer.models.MessageDetails;
import com.FMNNotificationServer.FMNNotificationServer.workers.EmailingWorker;
import com.FMNNotificationServer.FMNNotificationServer.workers.ReminderTimeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

@SpringBootApplication
@Controller
//@ComponentScan({"com.FMNNotificationServer.FMNNotificationServer.messaging.emailing"})
public class FmnNotificationServerApplication implements CommandLineRunner {



	@Autowired
	 EmailService emailService;

	@Autowired
	EmailingWorker emailingWorker;

	@Autowired
	ReminderTimeWorker reminderTimeWorker;

	@ResponseBody
	public static void main(String[] args) {
		SpringApplication.run(FmnNotificationServerApplication.class, args);

		MessageDetails messageDetails = new MessageDetails();
		messageDetails.setRecipientEmail("luiz.l.miotto94@gmail.com");
		messageDetails.setMessageSubject("Buongiorno");
		messageDetails.setMessageBody("Here is  a test of the emailing component");
		messageDetails.setAttachment("");
		//EmailService emailService1 = new EmailServiceImpl();
		//EmailingWorker emailingWorker = new EmailingWorker(emailService1);
		//emailingWorker.setEmailDetails(messageDetails);
		//emailingWorker.setDetails(messageDetails);
		//emailingWorker.sendSimpleEmail(emailingWorker.getEmailDetails());

	}

	@Override
	public void run(String... args) throws Exception {
		MessageDetails messageDetails = new MessageDetails();
		messageDetails.setRecipientEmail("luiz.l.miotto94@gmail.com");
		messageDetails.setMessageSubject("Buongiorno");
		messageDetails.setMessageBody("Here is  a test of the emailing component");
		messageDetails.setAttachment("");


		emailingWorker.setDetails(messageDetails);
		//emailingWorker.sendSimpleEmail(emailingWorker.getEmailDetails());
		ScheduledExecutorService scheduledActivity = Executors.newScheduledThreadPool(1);
		scheduledActivity.scheduleAtFixedRate(() -> {
			reminderTimeWorker.getUpcomingReminders();
		}, 0, 10, TimeUnit.MINUTES);



		LocalDateTime date1 = LocalDateTime.parse("2024-04-14T15:54:15");
		LocalDateTime date2 = LocalDateTime.parse("2024-04-14T17:51");
		System.out.println("The difference between these two times is: " + date1.until(date2, ChronoUnit.MINUTES));

	}
}
