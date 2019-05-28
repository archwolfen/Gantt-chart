package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ExecutorList extends ArrayList<Executor> implements Convertible {
    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (Executor e : this) {
            jsonArray.add(e.toJson());
        }
        return jsonArray;
    }
}
