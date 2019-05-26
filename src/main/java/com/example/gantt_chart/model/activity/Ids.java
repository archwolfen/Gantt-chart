package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class Ids implements Convertible {
    private ArrayList<Integer> ids = new ArrayList<Integer>();

    public void addId(final int id) {

    }

    public JsonElement toJson() {
        return null;
    }
}
