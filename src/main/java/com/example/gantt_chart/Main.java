package com.example.gantt_chart;

import com.example.gantt_chart.parser.Parser;

public class Main {
    public static void main(String[] args) {
        System.out.println("started...");

        try {
            new Parser("chart_example.xml").parse();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
