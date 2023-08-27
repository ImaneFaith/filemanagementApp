package org.filemanagement.notificationservice.services.notificationsabstract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.filemanagement.notificationservice.events.Event;
import org.filemanagement.notificationservice.models.Notification;


public interface  NotificationSenderBuilder<T> {

  public T build(String e) throws JsonProcessingException, FirebaseMessagingException;


}
