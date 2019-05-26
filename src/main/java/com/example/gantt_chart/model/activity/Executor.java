package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;

public class Executor implements Convertible {
    private String name;
    private String surname;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public Executor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Executor(String name, String surname, String photoUrl) {
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public JsonElement toJson() {
        return null;
    }
}
