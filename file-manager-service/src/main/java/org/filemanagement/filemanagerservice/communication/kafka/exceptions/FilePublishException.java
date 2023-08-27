package org.filemanagement.filemanagerservice.communication.kafka.exceptions;

public class FilePublishException extends RuntimeException{
    public FilePublishException() {
    }

    public FilePublishException(String message) {
        super(message);
    }

    public FilePublishException(String message, Throwable cause) {
        super(message, cause);
    }
}
