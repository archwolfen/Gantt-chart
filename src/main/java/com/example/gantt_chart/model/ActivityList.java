package com.example.gantt_chart.model;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.activity.Dates;
import com.example.gantt_chart.model.activity.ID;
import com.example.gantt_chart.model.activity.IDList;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class ActivityList extends ArrayList<TerminalActivity> implements Convertible {

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : this) {
            jsonArray.add(activity.toJson());
        }
        jsonObject.add("activities", jsonArray);

        CriticalPath path = new CriticalPath(this);
        jsonObject.add("critical", path.toJson());
        return jsonObject;
    }
}
