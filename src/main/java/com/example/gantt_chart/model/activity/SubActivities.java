package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SubActivities implements Convertible {
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {

    }

    public JsonElement toJson() {
        return null;
    }
}
