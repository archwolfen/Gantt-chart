package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class Progress implements Convertible {
    private int percents;

    public Progress(int percents) {
        this.percents = percents;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    public JsonElement toJson() {
        return new JsonPrimitive(percents);
    }
}
