package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

public class Dates implements Convertible {
    private Date start;
    private Date end;

    public Dates(Date start, Date end) {
        this.start = start;
        this.end = end;
        checkDateCorrection();
    }

    public Dates(long timestampStart, long timestampEnd) {
        this(new Date(timestampStart), new Date(timestampEnd));
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
        checkDateCorrection();
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
        checkDateCorrection();
    }

    public JsonElement toJson() {
        JsonObject result = new JsonObject();
        result.addProperty("start-date", start.toString());
        result.addProperty("end-date", end.toString());
        //duration + 1 because of including end date
        result.addProperty("duration", (int)(end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000) + 1);
        return result;
    }

    private void checkDateCorrection() {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("The start date has to be earlier than the end date");
        }
    }
}
