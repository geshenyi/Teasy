package com.ccorp.poc.mindtest.exception;

/**
 * Created by ssge on 2015/11/10.
 */
public class VerificationFailureException extends RuntimeException {

    public VerificationFailureException() {
    }

    public VerificationFailureException(String message) {
        super(message);
    }

    public VerificationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
