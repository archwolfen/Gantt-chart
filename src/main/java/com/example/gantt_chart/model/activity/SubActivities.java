package com.example.gantt_chart.model.activity;

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

    public boolean checkDependencies() {
//        TODO Implement the method
        return true;
    }

    public JsonElement toJson() {
        JsonArray jsonArray = new JsonArray();
        for (TerminalActivity activity : this) {
            jsonArray.add(activity.toJson());
        }
        return jsonArray;
    }
}
