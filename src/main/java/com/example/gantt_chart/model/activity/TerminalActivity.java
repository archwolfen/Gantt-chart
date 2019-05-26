package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TerminalActivity implements Convertible {
    private String title;
    private String id;
    private Dates startFinal;
    private ExecutorList executors;
    private Progress progress;
    private Ids nextIds;

    public TerminalActivity() {
    }

    public TerminalActivity(Dates startFinal, Progress progress, Ids nextIds, ExecutorList executors, String title, String id) {
        this.startFinal = startFinal;
        this.progress = progress;
        this.nextIds = nextIds;
        this.executors = executors;
        this.title = title;
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonElement toJson() {
        JsonObject activity = new JsonObject();
        activity.addProperty("title", title);
        activity.addProperty("id", id);
        activity.add("date", startFinal.toJson());
        activity.add("executors", executors.toJson());
        activity.add("progress", progress.toJson());
        activity.add("next-ids", nextIds.toJson());
        return activity;
    }
}
