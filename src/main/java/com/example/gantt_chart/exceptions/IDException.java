package com.example.gantt_chart.exceptions;

import com.example.gantt_chart.model.activity.ID;

public class IDException extends Exception {
    public IDException() {
        super();
    }

    public IDException(String message) {
        super(ID.class.getName() + ": " + message);
    }

    public IDException(String message, Throwable cause) {
        super(ID.class.getName() + ": " + message, cause);
    }

    public IDException(Throwable cause) {
        super(cause);
    }
}
