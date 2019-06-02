package com.example.gantt_chart.view;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.CriticalPath;
import com.example.gantt_chart.model.activity.*;
import com.example.gantt_chart.parser.Parser;
import com.example.gantt_chart.util.Chart;
import org.swiftgantt.common.Time;
import org.swiftgantt.model.Task;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OpenFileActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = Window.getInstance();
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            Chart chart = new Chart();
            chart.setViewPort(new Time(2019, 5, 20), new Time(2019, 6, 29));
            //parsing document
            try {
                Parser parser = new Parser(filePath);
                ActivityList activities = parser.parse();
                CriticalPath path = new CriticalPath(activities);
                Dates dates = path.getDuration();
                Date start = dates.getStart();
                Date end = dates.getEnd();
                System.out.println(new SimpleDateFormat("yyyy").format(start) + " " + Integer.parseInt(new SimpleDateFormat("MM").format(start)) + " " + Integer.parseInt(new SimpleDateFormat("dd").format(start)));
                chart.setViewPort(new Time(Integer.parseInt(new SimpleDateFormat("yyyy").format(start)), Integer.parseInt(new SimpleDateFormat("MM").format(start)), Integer.parseInt(new SimpleDateFormat("dd").format(start))),
                        new Time(Integer.parseInt(new SimpleDateFormat("yyyy").format(end)), Integer.parseInt(new SimpleDateFormat("MM").format(end)), Integer.parseInt(new SimpleDateFormat("dd").format(end)))
                );
                ArrayList<IDList> list = path.getCriticalPaths();

                add(path, activities, list, chart, null);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(window.getRoot(),
                        ex.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }

            ((JScrollPane) window.getRoot().getComponent(Window.SCROLL_PANE)).setViewportView(chart.getChartView());
        } else {
            JOptionPane.showMessageDialog(window.getRoot(),
                    "No file was chosen!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void add(CriticalPath path, SubActivities activities, ArrayList<IDList> list, Chart chart, Task parent) {
        Map<String, Task> taskMap = new HashMap<>();
        for (TerminalActivity ac : activities) {
            Task task = new Task();
            task.setName(ac.getTitle());
            for (IDList id : list) {
                if (id.contains(ac.getId())) {
                    task.setBackcolor(Color.RED);
                    break;
                }
            }
            Dates acDates = ac.getStartFinal();
            Date acStart = acDates.getStart();
            Date acEnd = acDates.getEnd();
            task.setStart(new Time(Integer.parseInt(new SimpleDateFormat("yyyy").format(acStart)), Integer.parseInt(new SimpleDateFormat("MM").format(acStart)), Integer.parseInt(new SimpleDateFormat("dd").format(acStart))));
            task.setEnd(new Time(Integer.parseInt(new SimpleDateFormat("yyyy").format(acEnd)), Integer.parseInt(new SimpleDateFormat("MM").format(acEnd)), Integer.parseInt(new SimpleDateFormat("dd").format(acEnd))));


            task.setProgress((int) ((double) acDates.getDurationInDays() * ac.getProgress().getPercents() / 100.0));
            task.calcProgressSteps();
            task.calcTaskSteps();

            chart.addTask(task, ac.getExecutors());

            if (ac.getDependencies() != null) {
                for (String id : ac.getDependencies()) {
                    task.addPredecessor(taskMap.get(id));
                }
            }

            taskMap.put(ac.getId(), task);

            if (parent != null) {
                parent.add(task);
            }


            if (ac instanceof SummaryActivity) {
                add(path, ((SummaryActivity) ac).getSubactivities(), list, chart, task);
            }

        }
    }
}
