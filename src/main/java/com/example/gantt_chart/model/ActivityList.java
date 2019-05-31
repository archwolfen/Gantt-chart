package com.example.gantt_chart.model;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.activity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class ActivityList extends SubActivities {

    @Override
    public JsonElement toJson() {
        JsonObject jsonObject = super.toJson().getAsJsonObject();
//         TODO Implement CriticalPath calculating
        return jsonObject;
    }
}
