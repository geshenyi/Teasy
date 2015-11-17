package com.ccorp.poc.mindtest.exception;

/**
 * Created by ssge on 2015/11/10.
 */
public class ExecutionErrorException extends RuntimeException {

    public ExecutionErrorException() {
    }

    public ExecutionErrorException(String message) {
        super(message);
    }

    public ExecutionErrorException(Throwable cause) {
        super(cause);
    }

    public ExecutionErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
