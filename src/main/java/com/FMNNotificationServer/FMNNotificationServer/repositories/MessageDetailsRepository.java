package com.FMNNotificationServer.FMNNotificationServer.repositories;

import com.FMNNotificationServer.FMNNotificationServer.models.MessageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageDetailsRepository extends JpaRepository<MessageDetails,String> {

    Optional<MessageDetails> findByMessageId(Long messageId);

}
