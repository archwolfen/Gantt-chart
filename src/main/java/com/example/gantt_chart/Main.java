package com.example.gantt_chart;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.example.gantt_chart.parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("started...");

//        try {
//
//            Parser xmlParser = new Parser("chart_example.xml");
//            ActivityList taskList = xmlParser.parse();
//
//            for (TerminalActivity task : taskList)
//                System.out.println(task.toJson());
//
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        Server server = new Server();
        server.launch(80);
        System.out.print("Enter any symbol to stop server: ");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        server.stop();
    }
}
