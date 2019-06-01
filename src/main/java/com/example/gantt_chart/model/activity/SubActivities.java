package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.exceptions.DependencyException;
import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SubActivities extends ArrayList<TerminalActivity> implements Convertible {
    private IDList idsPool = new IDList();

    @Override
    public boolean add(TerminalActivity terminalActivity) {
        idsPool.add(terminalActivity.getId());
        return super.add(terminalActivity);
    }

    @Override
    public void add(int i, TerminalActivity terminalActivity) {
        idsPool.add(terminalActivity.getId());
        super.add(i, terminalActivity);
    }

    @Override
    public boolean addAll(Collection<? extends TerminalActivity> collection) {
        collection.forEach(activity -> idsPool.add(activity.getId()));
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends TerminalActivity> collection) {
        collection.forEach(activity -> idsPool.add(activity.getId()));
        return super.addAll(i, collection);
    }

    public void checkDependencies() throws DependencyException {
        for (TerminalActivity activity : this) {
            activity.checkDependencies(idsPool);
        }
    }

    public void checkDateBounds() throws DatesException {
        for (TerminalActivity activity : this) {
            if (activity instanceof SummaryActivity) {
                ((SummaryActivity) activity).checkDateBounds();
            }
        }
    }

    public void checkDependenciesBounds() throws DependencyException {
        for (TerminalActivity activity : this) {
            activity.checkDependenciesBounds();
        }
    }

    public void checkDependenciesIdExistence() throws IDException {
        for (TerminalActivity activity : this) {
            activity.checkDependenciesIdExistence();
        }
    }

    public void sort() {
        for (TerminalActivity activity : this) {
            if (activity instanceof SummaryActivity) {
                ((SummaryActivity) activity).getSubactivities().sort();
            }
        }
        Collections.sort(this, (o1, o2) -> (int) (o1.getStartFinal().getStart().compareTo(o2.getStartFinal().getStart())));
    }

    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : this) {
            jsonArray.add(activity.toJson());
        }
        return jsonArray;
    }
}
