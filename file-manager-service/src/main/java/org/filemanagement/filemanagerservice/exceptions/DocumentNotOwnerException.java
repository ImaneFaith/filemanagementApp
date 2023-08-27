package org.filemanagement.filemanagerservice.exceptions;

public class DocumentNotOwnerException extends RuntimeException{

    public DocumentNotOwnerException() {
    }

    public DocumentNotOwnerException(String message) {
        super(message);
    }

    public DocumentNotOwnerException(String message, Throwable cause) {
        super(message, cause);
    }
}
