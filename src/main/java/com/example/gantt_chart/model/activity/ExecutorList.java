package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ExecutorList implements Convertible {
    private ArrayList<Executor> executors = new ArrayList<Executor>();

    public void addExecutor(Executor executor) {

    }

    public JsonObject toJson() {
        return null;
    }
}
