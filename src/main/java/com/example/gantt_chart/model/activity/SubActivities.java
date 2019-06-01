package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DependencyException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

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
        for (TerminalActivity activity : this)
            if (activity.getDependencies() != null)
                for (String id : activity.getDependencies())
                    if (idsPool.indexOf(id) == -1)
                        throw new DependencyException("Subactivity of current activity can`t have previous activity as one of previous activity from the path!");
    }

    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : this) {
            jsonArray.add(activity.toJson());
        }
        return jsonArray;
    }
}
