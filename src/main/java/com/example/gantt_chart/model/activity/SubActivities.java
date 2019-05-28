package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SubActivities extends ArrayList<TerminalActivity> implements Convertible {
    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : this) {
            jsonArray.add(activity.toJson());
        }
        return jsonArray;
    }
}
