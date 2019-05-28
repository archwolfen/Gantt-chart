package com.example.gantt_chart.model.activity;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class ExecutorListTest {

    @Test
    public void toJson() throws URISyntaxException {
        ExecutorList executorList = new ExecutorList();
        executorList.add(new Executor("David", "Da", new URI("kek")));
        executorList.add(new Executor("Dav", "Na", new URI("kek2")));
        executorList.add(new Executor("Dev", "Ne", new URI("kek3")));
        String expectedJsonString = "[" +
                "{\"name\":\"David\",\"surname\":\"Da\",\"img-src\":\"kek\"}," +
                "{\"name\":\"Dav\",\"surname\":\"Na\",\"img-src\":\"kek2\"}," +
                "{\"name\":\"Dev\",\"surname\":\"Ne\",\"img-src\":\"kek3\"}" +
                "]";
        Assert.assertEquals(expectedJsonString, executorList.toJson().toString());
    }
}