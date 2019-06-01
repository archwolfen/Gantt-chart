package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.exceptions.ProgressException;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class TerminalActivityTest {

    @Test
    public void toJson() throws URISyntaxException, DatesException, IDException, ProgressException {
        TerminalActivity terminalActivity = new TerminalActivity();
        terminalActivity.setTitle("Act");
        terminalActivity.setId(new ID("32131", terminalActivity));
        ExecutorList list = new ExecutorList();
        list.add(new Executor("Kek", "Lol", new URI("invalid")));
        terminalActivity.setExecutors(list);
        terminalActivity.setProgress(new Progress(65));
        IDList ids = new IDList();
        ids.add("32");
        terminalActivity.setDependencies(ids);
        terminalActivity.setStartFinal(new Dates(new Date(), new Date()));
        System.out.println(terminalActivity.toJson().toString());
    }
}