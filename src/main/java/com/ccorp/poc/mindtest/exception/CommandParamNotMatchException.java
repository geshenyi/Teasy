package com.ccorp.poc.mindtest.exception;

/**
 * Created by ssge on 2015/11/10.
 */
public class CommandParamNotMatchException extends RuntimeException {

    public CommandParamNotMatchException() {
    }

    public CommandParamNotMatchException(String message) {
        super(message);
    }

    public CommandParamNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
