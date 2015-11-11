package com.ccorp.poc.mindtest.exception;

/**
 * Created by ssge on 2015/11/10.
 */
public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException() {
    }

    public CommandNotFoundException(String message) {
        super(message);
    }

    public CommandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
