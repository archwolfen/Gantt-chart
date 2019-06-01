package com.example.gantt_chart.model;

import com.google.gson.JsonElement;

public interface Convertible {
    JsonElement toJson();
}