package com.example.gantt_chart.model.activity;

import com.google.gson.JsonElement;

public class TerminalActivity extends Activity {

    public TerminalActivity() {

    }

    public TerminalActivity(Dates startFinal, Progress progress, Ids nextIds) {
        super(startFinal, progress, nextIds);
    }

    @Override
    public JsonElement toJson() {
        return super.toJson();
    }
}
