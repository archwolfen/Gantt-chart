package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonObject;

public class Progress implements Convertible {
    private int percents;

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    public Progress(int percents) {
        this.percents = percents;
    }

    public JsonObject toJson() {
        return null;
    }
}
