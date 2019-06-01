package com.example.gantt_chart.model;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.activity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

public class CriticalPath implements Convertible {
    private ActivityList activities;
    private Dates duration;

    public CriticalPath(ActivityList activities) {
        this.activities = activities;
        try {
            duration = new Dates(0, Long.MAX_VALUE);
        } catch (DatesException e) {
            //Exception couldn't be thrown because date is already correct
        }
    }

    public Dates getDuration() {
        getCriticalPaths();
        return duration;
    }

    public ArrayList<IDList> getCriticalPaths() {
        //For correctly calculation in subactivities
        HashSet<String> checkedIds = new HashSet<String>();
        ArrayList<IDList> criticalPath = calcCriticalPath(activities);
        //Setduration
        IDList tmp = criticalPath.get(0);
        //Now they are in reverse order
        TerminalActivity first = ID.getActivityByID(tmp.get(tmp.size() - 1));
        TerminalActivity last = ID.getActivityByID(tmp.get(0));
        try {
            duration.setStart(first.getStartFinal().getStart());
            duration.setEnd(last.getStartFinal().getEnd());
        } catch (DatesException e) {
            //Exception couldn't be thrown because date is already correct
        }
        //Calculate also subactivites, if they present
        for (int k = 0; k < criticalPath.size(); ++k) {
            IDList list = criticalPath.get(k);
            for (int i = 0; i < list.size(); ++i) {
                String currId = list.get(i);
                //Skip id if it has been already checked
                if (checkedIds.contains(currId)) {
                    continue;
                }
                TerminalActivity activity = ID.getActivityByID(currId);
                if (activity instanceof SummaryActivity) {
                    ArrayList<IDList> subCritical = calcCriticalPath(
                            ((SummaryActivity) activity).getSubactivities()
                    );
                    IDList tmpCopy = new IDList();
                    tmpCopy.addAll(list);
                    list.addAll(i, subCritical.get(0));
                    //If subcritical path is not only one
                    for (int j = 1; j < subCritical.size(); ++j) {
                        IDList copyList = new IDList();
                        copyList.addAll(tmpCopy);
                        copyList.addAll(i, subCritical.get(j));
                        criticalPath.add(copyList);
                    }
                    //For check subactivities id
                    --i;
                }
                checkedIds.add(currId);
            }
        }
        //Reverse the order
        criticalPath.forEach(Collections::reverse);
        return criticalPath;
    }

    private ArrayList<IDList> calcCriticalPath(ArrayList<TerminalActivity> subactivities) {
        TerminalActivity start = subactivities.get(0);
        TerminalActivity finish = start;
        for (TerminalActivity activity : subactivities) {
            Dates current = activity.getStartFinal();
            if (current.getStart().compareTo(start.getStartFinal().getStart()) < 0) {
                start = activity;
            }
            if (current.getEnd().compareTo(finish.getStartFinal().getEnd()) > 0) {
                finish = activity;
            }
        }

        Date earliest = start.getStartFinal().getStart();
        AtomicLong maxDuration = new AtomicLong(0);
        long tmpDuration = finish.getStartFinal().getDurationInDays();
        TerminalActivity current = finish;
        IDList path = new IDList();
        path.add(current.getId());

        ArrayList<IDList> criticalPaths = new ArrayList<IDList>();
        calcSubCriticalPath(criticalPaths,
                earliest,
                maxDuration,
                tmpDuration,
                current,
                path);
        return criticalPaths;
    }

    /*Calculates critical path that ends
     * by a specified element*/
    private void calcSubCriticalPath(ArrayList<IDList> criticalPaths,
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
            //Save path
            if (tmpDuration == maxDuration.get()) {
                IDList copy = new IDList();
                copy.addAll(path);
                criticalPaths.add(copy);
            }
        }
        //Keep going
//        TODO refactor to foreach
        if (current.getDependencies() != null) {
            for (String id : current.getDependencies()) {
                TerminalActivity previous = ID.getActivityByID(id);
                long nextDuration = tmpDuration + previous.getStartFinal().getDurationInDays();
                path.add(id);
                calcSubCriticalPath(criticalPaths, earliest, maxDuration, nextDuration, previous, path);
                path.remove(path.size() - 1);
            }
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
