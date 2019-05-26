package com.example.gantt_chart.model.activity;

import com.google.gson.JsonObject;

public class TerminalActivity extends Activity {
    public TerminalActivity(Dates startFinal, Progress progress, Ids nexIds) {
        super(startFinal, progress, nexIds);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }
}
