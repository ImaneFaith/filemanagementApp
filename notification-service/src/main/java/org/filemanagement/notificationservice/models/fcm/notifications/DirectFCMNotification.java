package org.filemanagement.notificationservice.models.fcm.notifications;

import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DirectFCMNotification extends FCMNotification {

    private String token;
    public Message.Builder getMessageFromNotification(){

        return Message.builder().setToken(token);
    }
}
