package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class IDList extends ArrayList<String> implements Convertible {

    // TODO: 28.05.2019 Make algorithm more corrective

    public boolean checkCorrectionID() {
        for (String id : this) {
            if (ID.getID(id) == null)
                return false;
        }

        return true;
    }

    public JsonElement toJson() {
        JsonArray result = new JsonArray();
        for (String id : this) {
            result.add(id);
        }
        return result;
    }
}
