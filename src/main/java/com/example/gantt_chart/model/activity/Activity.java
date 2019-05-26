package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonObject;

public abstract class Activity implements Convertible {
    private Dates startFinal;
    private Progress progress;
    private Ids nexIds;

    public Activity(Dates startFinal, Progress progress, Ids nexIds) {
        this.startFinal = startFinal;
        this.progress = progress;
        this.nexIds = nexIds;
    }

    public Dates getStartFinal() {
        return startFinal;
    }

    public void setStartFinal(Dates startFinal) {
        this.startFinal = startFinal;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Ids getNexIds() {
        return nexIds;
    }

    public void setNexIds(Ids nexIds) {
        this.nexIds = nexIds;
    }

    public JsonObject toJson() {
        return null;
    }
}