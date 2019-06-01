package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.ProgressException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class Progress implements Convertible {
    private int percents;

    public Progress(int percents) throws ProgressException {
        checkProgress(percents);
        this.percents = percents;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) throws ProgressException {
        checkProgress(percents);
        this.percents = percents;
    }

    public JsonElement toJson() {
        return new JsonPrimitive(percents);
    }

    private void checkProgress(int percents) throws ProgressException {
        if (percents < 0 || percents > 100) {
            throw new ProgressException("Progress value is [0 - 100]! Given value: " + percents);
        }
    }
}
