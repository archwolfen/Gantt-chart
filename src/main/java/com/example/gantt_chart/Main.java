package com.example.gantt_chart;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.example.gantt_chart.parser.Parser;

public class Main {
    public static void main(String[] args) {
        System.out.println("started...");

        try {

            Parser xmlParser = new Parser("chart_example.xml");
            ActivityList taskList = xmlParser.parse();

            for (TerminalActivity task : taskList)
                System.out.println(task.toJson());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
