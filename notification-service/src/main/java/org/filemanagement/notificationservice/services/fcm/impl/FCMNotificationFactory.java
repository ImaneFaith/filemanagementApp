package org.filemanagement.notificationservice.services.fcm.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotificationType;
import org.filemanagement.notificationservice.models.fcm.notifications.DirectFCMNotification;
import org.filemanagement.notificationservice.models.fcm.notifications.FCMNotification;
import org.filemanagement.notificationservice.models.fcm.notifications.TopicFCMNotification;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FCMNotificationFactory {

    public FCMNotification createNotificationBuilder(String type, String payload, Object data){
        FCMNotification notification ;

        switch (FCMNotificationType.valueOf(type)){

            case PUSH_DIRECT -> notification = DirectFCMNotification.builder().token(payload).notifDate(LocalDate.now()).data(data).build();
            case PUSH_TOPIC -> notification = TopicFCMNotification.builder().topic(payload).notifDate(LocalDate.now()).data(data).build();
            default -> notification = new FCMNotification() ;
        }

        return notification;
    }

}
