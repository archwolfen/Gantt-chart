package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public abstract class Activity implements Convertible {
    private Dates startFinal;
    private Progress progress;
    private Ids nexIds;

    public JsonObject toJson() {
        return null;
    }
}