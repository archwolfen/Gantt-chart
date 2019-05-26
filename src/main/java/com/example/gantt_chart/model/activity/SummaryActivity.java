package com.example.gantt_chart.model.activity;

import com.google.gson.JsonElement;

public class SummaryActivity extends Activity {
    private SubActivities subactivities = new SubActivities();

    public SummaryActivity() {

    }

    public SummaryActivity(Dates startFinal, Progress progress, Ids nextIds) {
        super(startFinal, progress, nextIds);
    }

    public SummaryActivity(Dates startFinal, Progress progress, Ids nextIds, SubActivities subActivities) {
        super(startFinal, progress, nextIds);
    }

    public void addActivity(SubActivities activitys) {

    }

    @Override
    public JsonElement toJson() {
        return super.toJson();
    }
}
