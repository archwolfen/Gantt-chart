package com.example.gantt_chart.util;

import com.example.gantt_chart.model.activity.Executor;
import com.example.gantt_chart.model.activity.ExecutorList;
import com.example.gantt_chart.view.Window;
import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;
import org.swiftgantt.common.Time;
import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Chart {
    private GanttChart chart;
    private GanttModel chartData;
    private Map<String, ExecutorList> executorsMap = new HashMap<>();

    public Chart() {
        chart = new GanttChart();
        chart.setTimeUnit(TimeUnit.Day);

        Config config = chart.getConfig();
        config.setWorkingTimeBackColor(Color.WHITE);
        config.setTimeUnitWidth(50);
        config.setWorkingDaysSpanOfWeek(new int[]{Calendar.MONDAY, Calendar.SATURDAY});

        chart.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JPanel());

        chartData = new GanttModel();
        chart.setModel(chartData);

        chart.addSelectionChangeListener(selectionChangeEvent -> {
            Window window = Window.getInstance();
            if (selectionChangeEvent.getSelection() != null) {
                ExecutorList executorList = executorsMap.get(selectionChangeEvent.getSelection().getName());


                StringBuilder executors = new StringBuilder();
                for (Executor e : executorList) {
                    executors.append(e.getName() + " " + e.getSurname() + "; ");
                }
                ((JLabel) ((JPanel) ((JPanel) window.getRoot().getComponent(Window.INFO_PANEL)).getComponent(0)).getComponent(0)).setText("Executors: " + executors.toString());
                window.getRoot().invalidate();
                window.getRoot().validate();
                window.getRoot().repaint();
            } else {
                ((JLabel) ((JPanel) ((JPanel) window.getRoot().getComponent(Window.INFO_PANEL)).getComponent(0)).getComponent(0)).setText("");
                window.getRoot().invalidate();
                window.getRoot().validate();
                window.getRoot().repaint();
            }
        });
    }

    public void clearTasks() {
        chartData.removeAll();
    }

    public void setViewPort(Time start, Time end) {
        if (start != null) {
            chartData.setKickoffTime(start);
        }
        if (end != null) {
            chartData.setDeadline(end);
        }
    }

    public void addTask(Task task, ExecutorList list) {
        chartData.addTask(task);
        executorsMap.put(task.getName(), list);
    }

    public GanttChart getChartView() {
        return chart;
    }
}
