package com.example.gantt_chart.exceptions;

import com.example.gantt_chart.model.activity.Dates;

public class DatesException extends Exception {
    public DatesException() {
        super();
    }

    public DatesException(String message) {
        super(Dates.class.getName() + ": " + message);
    }

    public DatesException(String message, Throwable cause) {
        super(Dates.class.getName() + ": " + message, cause);
    }

    public DatesException(Throwable cause) {
        super(cause);
    }
}
