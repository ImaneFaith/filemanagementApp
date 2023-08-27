package org.filemanagement.notificationservice.models.fcm.notifications;

import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.filemanagement.notificationservice.models.Notification;

@SuperBuilder
@NoArgsConstructor
public class FCMNotification extends Notification  {

    public  Message.Builder getMessageFromNotification(){
        return Message.builder();
    }

}
