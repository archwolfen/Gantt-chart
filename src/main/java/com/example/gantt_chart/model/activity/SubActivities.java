package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SubActivities implements Convertible {
    private ArrayList<TerminalActivity> activities = new ArrayList<TerminalActivity>();

    public void addActivity(TerminalActivity activity) {
        activities.add(activity);
    }

    public ArrayList<TerminalActivity> getActivities() {
        return activities;
    }

    public JsonElement toJson() {
        return null;
    }
}
