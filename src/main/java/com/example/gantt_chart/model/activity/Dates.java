package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates implements Convertible {
    private Date start;
    private Date end;

    public Dates(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public JsonElement toJson() {
        JsonObject result = new JsonObject();
        result.addProperty("start-date", new SimpleDateFormat("dd-MM-yyyy").format(start));
        result.addProperty("end-date", new SimpleDateFormat("dd-MM-yyyy").format(end));
        result.addProperty("duration", (int) (end.getTime() - start.getTime()) / 24 * 60 * 60 * 1000);
        return result;
    }
}
