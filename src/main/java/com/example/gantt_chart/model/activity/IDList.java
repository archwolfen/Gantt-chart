package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class IDList extends ArrayList<String> implements Convertible {

    // TODO: 28.05.2019 Make algorithm more corrective

    public void checkCorrectionID() throws IDException {
        for (String id : this) {
            if (ID.getID(id) == null)
                throw new IDException("Activity with this id does not exsit!");
        }
    }

    public JsonElement toJson() {
        JsonArray result = new JsonArray();
        for (String id : this) {
            result.add(id);
        }
        return result;
    }
}
