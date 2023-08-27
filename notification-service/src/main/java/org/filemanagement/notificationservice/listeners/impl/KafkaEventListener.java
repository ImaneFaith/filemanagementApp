package org.filemanagement.notificationservice.listeners.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.notificationservice.exceptions.InvalidMessageException;
import org.filemanagement.notificationservice.exceptions.TokenNotFoundException;
import org.filemanagement.notificationservice.listeners.EventListener;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotification;
import org.filemanagement.notificationservice.services.notificationsabstract.NotificationSenderBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaEventListener implements EventListener {

    private final NotificationSenderBuilder<FCMNotification> notificationSenderBuilder;

    @Override
    @KafkaListener(topics = {"file-notif"})
    public void listen(String event){

        log.info("Event '{}' received.", event);
        try{
            notificationSenderBuilder.build(event);
        }catch(JsonProcessingException ex){
            throw new InvalidMessageException("Invalid Event Exception");
        }catch(FirebaseMessagingException ex){
            log.info("{} Code : {}", ex.getMessage(),ex.getMessagingErrorCode());
        }catch (TokenNotFoundException ex){
            log.info("Token Not Found Exception");
        }




    }
}
