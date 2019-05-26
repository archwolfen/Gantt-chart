package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class Ids implements Convertible {
    private ArrayList<Integer> ids = new ArrayList<Integer>();

    public void addId(final int id) {
        ids.add(id);
    }

    public JsonElement toJson() {
        JsonArray result = new JsonArray();
        for(Integer id : ids)
        {
            result.add(id);
        }
        return result;
    }
}
