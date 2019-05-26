package com.example.gantt_chart.model.activity;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class TerminalActivityTest {

    @Test
    public void toJson() throws URISyntaxException {
        TerminalActivity terminalActivity = new TerminalActivity();
        terminalActivity.setTitle("Act");
        terminalActivity.setId(32131);
        ExecutorList list = new ExecutorList();
        list.addExecutor(new Executor("Kek", "Lol", new URI("invalid")));
        terminalActivity.setExecutors(list);
        terminalActivity.setProgress(new Progress(65));
        Ids ids = new Ids();
        ids.addId(33);
        terminalActivity.setNextIds(ids);
        terminalActivity.setStartFinal(new Dates(new Date(), new Date()));
        System.out.println(terminalActivity.toJson().toString());
    }
}