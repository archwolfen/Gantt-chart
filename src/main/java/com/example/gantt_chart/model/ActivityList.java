package com.example.gantt_chart.model;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.activity.SubActivities;
import com.example.gantt_chart.model.activity.SummaryActivity;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.google.gson.JsonElement;

public class ActivityList extends SubActivities {

    @Override
    public void sort() {
        this.forEach(activity -> {
            if (activity instanceof SummaryActivity) {
                ((SummaryActivity) activity).getSubactivities().sort();
            }
        });

        super.sort();
    }

    public void checkDateBounds() throws DatesException {
        for (TerminalActivity activity : this) {
            if (activity instanceof SummaryActivity) {
                ((SummaryActivity) activity).checkDateBounds();
            }
        }
    }

    @Override
    public JsonElement toJson() {
        //         TODO Implement CriticalPath calculating
        return super.toJson().getAsJsonArray();
    }
}
