package org.filemanagement.filemanagerservice.exceptions;

public class DocumentNotFoundException extends RuntimeException{

    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
