package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.IDException;

import java.util.ArrayList;

public class ID {
    private static ArrayList<ID> idList = new ArrayList<ID>();
    private String id;

    public ID(String id) throws IDException {
        if(ID.getID(id) != null)
        {
            throw new IDException(id + " id already exist");
        }

        this.id = id;
        idList.add(this);
    }

    public static ID getID(String id) {
        for(ID curID : idList)
        {
            if(curID.toString().equals(id))
            {
                return curID;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return id;
    }
}
