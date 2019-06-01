package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DependencyException;
import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TerminalActivity implements Convertible {
    private String title;
    private ID id;
    private Dates startFinal;
    private ExecutorList executors;
    private Progress progress;
    private IDList dependencies;

    public TerminalActivity() {
    }

    public TerminalActivity(Dates startFinal, Progress progress, IDList nextIds, ExecutorList executors, String title, ID id) {
        this.startFinal = startFinal;
        this.progress = progress;
        this.dependencies = nextIds;
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

    public IDList getDependencies() {
        return dependencies;
    }

    public void setDependencies(IDList nextIds) {
        this.dependencies = nextIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setId(String id, TerminalActivity activity) throws IDException {
        this.id = new ID(id, activity);
    }

    public void checkDependenciesIdExistence() throws IDException {
        if (dependencies != null) {
            for (String id : dependencies) {
                if (ID.getID(id) == null) {
                    throw new IDException(String.format("ID [%s] in Activity[%s] dependencies doesn't exist", id, title));
                }
            }
        }
    }

    public void checkDependenciesBounds() throws DependencyException {
        if (dependencies != null) {
            for (String id : dependencies) {
                if (ID.getActivityByID(id).getStartFinal().getEnd().compareTo(getStartFinal().getStart()) >= 0) {
                    throw new DependencyException("Start date of dependent activity can`t merge with end date of activity that had to be done before this!");
                }
            }
        }
    }

    public void checkDependencies(IDList poolIds) throws DependencyException {
        if (dependencies != null) {
            for (String id : dependencies) {
                if (!poolIds.contains(id)) {
                    throw new DependencyException("Activity mustn't depend on activity from another sub-activities");
                }
            }
        }
    }

    public JsonElement toJson() {
        JsonObject activity = new JsonObject();
        activity.addProperty("title", title);
        activity.addProperty("id", id.toString());
        activity.add("date", startFinal.toJson());
        activity.add("executors", executors.toJson());
        activity.add("progress", progress.toJson());
        if (dependencies != null)
            activity.add("dependencies", dependencies.toJson());
        return activity;
    }
}