package org.filemanagement.notificationservice.services.notificationsabstract;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.filemanagement.notificationservice.models.Notification;

public interface NotificationSender<T> {

    public void send(T t) throws FirebaseMessagingException;
}
