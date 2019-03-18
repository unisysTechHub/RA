package com.bvifsc.mbc.events;

/**
 * Created by Ramesh on 28-11-2018.
 */
public class OnExceptionEvent {
    String exceptionMessage;
    private final Exception exception;

    public OnExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return this.exception;

    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
