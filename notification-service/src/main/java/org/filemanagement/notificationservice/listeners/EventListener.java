package org.filemanagement.notificationservice.listeners;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface EventListener {
    
    public void listen(String event) throws JsonProcessingException, FirebaseMessagingException;
}
