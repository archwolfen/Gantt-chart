package com.example.gantt_chart.util;

import com.example.gantt_chart.model.activity.Executor;
import com.example.gantt_chart.model.activity.ExecutorList;
import com.example.gantt_chart.model.activity.ID;
import com.example.gantt_chart.view.Window;
import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;
import org.swiftgantt.common.Time;
import org.swiftgantt.event.SelectionChangeEvent;
import org.swiftgantt.event.SelectionChangeListener;
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
    private Map<Task, ExecutorList> executorsMap = new HashMap<>();

    public Chart() {
        chart = new GanttChart();
        chart.setTimeUnit(TimeUnit.Day);

        Config config = chart.getConfig();
        config.setWorkingTimeBackColor(Color.WHITE);
        config.setTimeUnitWidth(50);
        config.setWorkingDaysSpanOfWeek(new int[]{Calendar.MONDAY, Calendar.FRIDAY});

        chart.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JPanel());

        chartData = new GanttModel();
        chart.setModel(chartData);

        chart.addSelectionChangeListener(selectionChangeEvent -> {
            Window window = Window.getInstance();
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            ((JPanel) window.getRoot().getComponent(Window.INFO_PANEL)).add(infoPanel);
            ExecutorList executorList = executorsMap.get(selectionChangeEvent.getSelection());
            StringBuilder executors = new StringBuilder();
            for (Executor e : executorList) {
                executors.append(e.getName() + " " + e.getSurname() + "; ");
            }
            Label label = new Label("Executors: " + executors.toString());
            infoPanel.add(label);
            window.getRoot().invalidate();
            window.getRoot().validate();
            window.getRoot().repaint();
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
        executorsMap.put(task, list);
    }

    public GanttChart getChartView() {
        return chart;
    }
}
