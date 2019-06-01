package com.example.gantt_chart.exceptions;

public class ProgressException extends Exception {
    public ProgressException() {
        super();
    }

    public ProgressException(String message) {
        super(message);
    }

    public ProgressException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgressException(Throwable cause) {
        super(cause);
    }
}
