package com.example.gantt_chart.model;

import com.example.gantt_chart.model.activity.Dates;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class ActivityList extends ArrayList<TerminalActivity> implements Convertible {
    public Dates calculateCriticalPath() {
        return null;
    }

    public JsonElement toJson() {
        return null;
    }
}
