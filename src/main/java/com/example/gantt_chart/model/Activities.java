package com.example.gantt_chart.model;

import com.example.gantt_chart.model.activity.Activity;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Activities implements Convertible {
    private ArrayList<Activity> allActivities;
    public JsonObject toJson() {
        return null;
    }
}
