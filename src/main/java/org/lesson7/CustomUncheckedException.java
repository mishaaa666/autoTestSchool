package org.lesson7;

class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
