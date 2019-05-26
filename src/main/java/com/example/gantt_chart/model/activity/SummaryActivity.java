package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

public class SummaryActivity extends TerminalActivity implements Convertible {
    private SubActivities subactivities = new SubActivities();

    public SummaryActivity() {

    }

    public SummaryActivity(Dates startFinal, Progress progress, Ids nextIds, ExecutorList executors, SubActivities subactivities) {
        super(startFinal, progress, nextIds, executors);
        this.subactivities = subactivities;
    }

    public void addSubActivitiy(TerminalActivity activity) {
        subactivities.addActivity(activity);
    }

    public void addSubActivities(SubActivities activitys) {
        subactivities.getActivities().addAll(activitys.getActivities());
    }

    @Override
    public JsonElement toJson() {
        return super.toJson();
    }
}
