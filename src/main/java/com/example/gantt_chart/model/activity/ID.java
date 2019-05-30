package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.ActivityList;

import java.util.ArrayList;
import java.util.HashMap;

public class ID {
    private static ArrayList<ID> idList = new ArrayList<ID>();

    private String id;
    private TerminalActivity activity;

    public ID(String id, TerminalActivity activity) throws IDException {
        if (ID.getID(id) != null) {
            throw new IDException(id + " id already exist");
        }

        this.id = id;
        this.activity = activity;
        idList.add(this);
    }

    public static ID getID(String id) {
        for (ID curID : idList) {
            if (curID.toString().equals(id)) {
                return curID;
            }
        }

        return null;
    }

    public static ArrayList<ID> getIdList() {
        return idList;
    }

    public TerminalActivity getActivity() {
        return activity;
    }

    public static TerminalActivity getActivityByID(String id) {
        for (ID curID : idList) {
            if (curID.toString().equals(id)) {
                return curID.activity;
            }
        }

        return null;
    }

    public static void clear() {
        idList.clear();
    }

    @Override
    public String toString() {
        return id;
    }
}
