package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates implements Convertible {
    private Date start;
    private Date end;

    public Dates(@NotNull Date start, @NotNull Date end) throws DatesException {
        checkDateForNull(start);
        checkDateForNull(end);
        this.start = start;
        this.end = end;
        checkDateCorrection();
    }

    public Dates(long timestampStart, long timestampEnd) throws DatesException {
        this(new Date(timestampStart), new Date(timestampEnd));
    }

    public Date getStart() {
        return start;
    }

    public void setStart(@NotNull Date start) throws DatesException {
        checkDateForNull(start);
        this.start = start;
        checkDateCorrection();
    }

    public Date getEnd() {
        return end;
    }

    public long getDurationInDays() {
        return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000) + 1;
    }

    public void setEnd(@NotNull Date end) throws DatesException {
        checkDateForNull(end);
        this.end = end;
        checkDateCorrection();
    }

    public JsonElement toJson() {
        JsonObject result = new JsonObject();
        result.addProperty("start-date", new SimpleDateFormat("dd-MM-yyyy").format(start));
        result.addProperty("end-date", new SimpleDateFormat("dd-MM-yyyy").format(end));
        result.addProperty("duration", getDurationInDays());
        return result;
    }

    private void checkDateCorrection() throws DatesException {
        if (start.compareTo(end) > 0) {
            throw new DatesException("The start date has to be earlier than the end date");
        }
    }

    private void checkDateForNull(Date date) throws DatesException {
        if (date == null) {
            throw new DatesException("Trying to set start|end date to null: start and end date is not @Nullable!");
        }
    }
}
