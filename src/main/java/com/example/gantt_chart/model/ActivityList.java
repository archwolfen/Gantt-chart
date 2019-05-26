package com.example.gantt_chart.model;

import com.example.gantt_chart.model.activity.Activity;
import com.example.gantt_chart.model.activity.Dates;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class ActivityList implements Convertible {

    private ArrayList<Activity> allActivities = new ArrayList<Activity>();

    public ActivityList() {
    }

    public void addActivity(Activity activity) {

    }

    public Dates calculateCriticalPath() {
        return null;
    }

    public JsonElement toJson() {
        return null;
    }
}
