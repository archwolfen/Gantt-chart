package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

public abstract class Activity implements Convertible {
    private Dates startFinal;
    private Progress progress;
    private Ids nextIds;
    private ExecutorList executors;

    public Activity(Dates startFinal, Progress progress, Ids nexIds) {
        this.startFinal = startFinal;
        this.progress = progress;
        this.nextIds = nexIds;
    }

    public Activity()
    {

    }

    public Dates getStartFinal() {
        return startFinal;
    }

    public void setStartFinal(Dates startFinal) {
        this.startFinal = startFinal;
    }

    public ExecutorList getExecutors() {
        return executors;
    }

    public void setExecutors(ExecutorList executors) {
        this.executors = executors;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Ids getNextIds() {
        return nextIds;
    }

    public void setNextIds(Ids nextIds) {
        this.nextIds = nextIds;
    }

    public JsonElement toJson() {
        return null;
    }
}