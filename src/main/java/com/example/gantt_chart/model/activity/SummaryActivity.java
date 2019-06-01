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
        subactivities.add(activity);
    }

    public void addSubActivities(SubActivities activities) {
        subactivities = activities;
    }

    public boolean checkDateBounds() {
        for (TerminalActivity activity : subactivities) {
            if (activity.getStartFinal().getStart().compareTo(getStartFinal().getStart()) < 0)
                return false;

            if (activity.getStartFinal().getEnd().compareTo(getStartFinal().getEnd()) > 0)
                return false;
        }

        return true;
    }

    public SubActivities getSubactivities() {
        return subactivities;
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
