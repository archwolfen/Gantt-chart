package com.example.gantt_chart.model;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.activity.Dates;
import com.example.gantt_chart.model.activity.ID;
import com.example.gantt_chart.model.activity.IDList;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class CriticalPath implements Convertible {
    private ActivityList activities;
    private Dates duration;

    public CriticalPath(ActivityList activities) {
        this.activities = activities;
        try {
            duration = new Dates(0, 1000000);
        } catch (DatesException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<IDList> getCriticalPaths() {
        TerminalActivity start = activities.get(0);
        TerminalActivity finish = start;
        for (TerminalActivity activity : activities) {
            Dates current = activity.getStartFinal();
            if (current.getStart().compareTo(start.getStartFinal().getStart()) < 0) {
                start = activity;
            }
            if (current.getEnd().compareTo(finish.getStartFinal().getEnd()) > 0) {
                finish = activity;
            }
        }
        try {
            duration.setEnd(finish.getStartFinal().getEnd());
            duration.setStart(start.getStartFinal().getStart());
        } catch (DatesException e) {
            e.printStackTrace();
        }

        Date earliest = duration.getStart();
        AtomicLong maxDuration = new AtomicLong(0);
        long tmpDuration = finish.getStartFinal().getDurationInDays();
        TerminalActivity current = finish;
        IDList path = new IDList();
        path.add(current.getId());

        ArrayList<IDList> criticalPaths = new ArrayList<IDList>();
        calculateCriticalPaths(criticalPaths,
                earliest,
                maxDuration,
                tmpDuration,
                current,
                path);
        return criticalPaths;
    }

    private void calculateCriticalPaths(ArrayList<IDList> criticalPaths,
                                        Date earliest,
                                        AtomicLong maxDuration,
                                        long tmpDuration,
                                        TerminalActivity current,
                                        IDList path) {
        if (current.getStartFinal().getStart().compareTo(earliest) == 0) {
            if (tmpDuration > maxDuration.get()) {
                maxDuration.set(tmpDuration);
                criticalPaths.clear();
            }
            if (tmpDuration == maxDuration.get()) {
                criticalPaths.add(path);
            }
        }
        //Keep going
        for (String id : current.getDependencies()) {
            TerminalActivity next = ID.getActivityByID(id);
            long nextDuration = tmpDuration + next.getStartFinal().getDurationInDays();
            path.add(id);
            calculateCriticalPaths(criticalPaths, earliest, maxDuration, nextDuration, next, path);
            path.remove(path.size() - 1);
        }
    }

    public JsonElement toJson() {
        ArrayList<IDList> criticalPaths = getCriticalPaths();
        //Dates
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("duration", duration.toJson());
        //Ids
        JsonArray jsonArray = new JsonArray();
        for (IDList path : criticalPaths) {
            jsonArray.add(path.toJson());
        }
        jsonObject.add("paths", jsonArray);
        return jsonObject;
    }
}
