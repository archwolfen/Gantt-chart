package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SummaryActivity extends TerminalActivity implements Convertible {
    private SubActivities subactivities = new SubActivities();

    public SummaryActivity() {

    }

    public SummaryActivity(Dates startFinal, Progress progress, IDList nextIds, ExecutorList executors, String title, ID id) {
        super(startFinal, progress, nextIds, executors, title, id);
    }

    public void addSubActivity(TerminalActivity activity) {
        subactivities.addActivity(activity);
    }

    public void addSubActivities(SubActivities activities) {
        subactivities.getActivities().addAll(activities.getActivities());
    }

    @Override
    public JsonElement toJson() {
        JsonElement jsonElement = super.toJson();
        JsonElement subJson = subactivities.toJson();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        jsonObject.add("sub-activities", subJson);
        return jsonObject;
    }
}
