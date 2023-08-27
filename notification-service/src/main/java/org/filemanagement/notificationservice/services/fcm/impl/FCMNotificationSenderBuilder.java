package org.filemanagement.notificationservice.services.fcm.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.notificationservice.entities.TokenEntity;
import org.filemanagement.notificationservice.events.Event;
import org.filemanagement.notificationservice.exceptions.InvalidMessageException;
import org.filemanagement.notificationservice.exceptions.TokenNotFoundException;
import org.filemanagement.notificationservice.mappers.MapperObject;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotification;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotificationType;
import org.filemanagement.notificationservice.services.RedisTokenService;
import org.filemanagement.notificationservice.services.notificationsabstract.NotificationSender;
import org.filemanagement.notificationservice.services.notificationsabstract.NotificationSenderBuilder;

import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class FCMNotificationSenderBuilder implements NotificationSenderBuilder<FCMNotification> {

    private final NotificationSender<FCMNotification> notificationSender;
    private final MapperObject mapper;
    private final RedisTokenService redisTokenService;

    @Override
    public FCMNotification build(String payload) {

        FCMNotificationFactory factory = new FCMNotificationFactory();
        FCMNotification notification = null;

        try {
            Event event = mapper.mapToEvent(payload);
            if (hasUsername(event)) {

                TokenEntity entity = redisTokenService.findById(event.getUsername()).orElseThrow(() ->
                        new TokenNotFoundException("Token doesn't exist"));
                notification = factory.createNotificationBuilder(FCMNotificationType.PUSH_DIRECT.toString(), entity.getToken(), event.getData());


            } else {

                notification = factory.createNotificationBuilder(FCMNotificationType.PUSH_DIRECT.toString(), "topic-notif", event.getData());

            }

            notificationSender.send(notification);


        } catch (FirebaseMessagingException ex) {
            throw new RuntimeException(ex);
        } catch (JsonProcessingException ex) {
            log.info("{} : ", ex.getMessage());
            throw new RuntimeException(ex);
        }

        return notification;


    }

    private String obtainUsername(Event event) {
        return event.getUsername();
    }

    private boolean hasUsername(Event event) {
        return obtainUsername(event) != null;
    }
}
