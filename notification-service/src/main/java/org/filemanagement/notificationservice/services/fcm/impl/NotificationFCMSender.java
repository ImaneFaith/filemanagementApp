package org.filemanagement.notificationservice.services.fcm.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotification;
import org.filemanagement.notificationservice.services.notificationsabstract.NotificationSender;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationFCMSender implements NotificationSender<FCMNotification> {

    private final FirebaseMessaging fcm;
    @Override
    public void send(FCMNotification notification) {

        try{
            fcm.send(notification.getMessageFromNotification().build());

        }catch(FirebaseMessagingException ex){

            log.info("could not send notification {}", ex.getMessage());
        }


    }




}
