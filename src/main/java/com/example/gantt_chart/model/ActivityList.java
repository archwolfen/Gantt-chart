package com.example.gantt_chart.model;

import com.example.gantt_chart.model.activity.SubActivities;
import com.google.gson.JsonElement;

public class ActivityList extends SubActivities {

    @Override
    public JsonElement toJson() {
        //         TODO Implement CriticalPath calculating
        return super.toJson().getAsJsonArray();
    }
}
