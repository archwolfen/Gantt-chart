package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SubActivities implements Convertible {
    private ArrayList<TerminalActivity> activities = new ArrayList<TerminalActivity>();

    public SubActivities() {
    }

    public void addActivity(TerminalActivity activity) {
        activities.add(activity);
    }

    public ArrayList<TerminalActivity> getActivities() {
        return activities;
    }

    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : activities) {
            jsonArray.add(activity.toJson());
        }
        return jsonArray;
    }
}
