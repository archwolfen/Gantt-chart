package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;

public class Executor implements Convertible {
    private String name;
    private String surname;
    private URI photoUrl;

    public Executor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Executor(String name, String surname, URI photoUrl) {
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
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

    public URI getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(URI photoUrl) {
        this.photoUrl = photoUrl;
    }

    public JsonElement toJson() {
        JsonObject executor = new JsonObject();
        executor.addProperty("name", name);
        executor.addProperty("surname", surname);
        executor.addProperty("img-src", photoUrl.toString());
        return executor;
    }
}
