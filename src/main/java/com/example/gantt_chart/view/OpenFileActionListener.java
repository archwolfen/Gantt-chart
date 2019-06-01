package com.example.gantt_chart.view;

import com.example.gantt_chart.model.activity.Executor;
import com.example.gantt_chart.model.activity.ExecutorList;
import com.example.gantt_chart.util.Chart;
import org.swiftgantt.GanttChart;
import org.swiftgantt.common.Time;
import org.swiftgantt.model.Task;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
            ExecutorList executorList = new ExecutorList();
            executorList.add(new Executor("Name", "Surname"));

            
            //TODO parse xml and add chart creation
            ((JScrollPane) window.getRoot().getComponent(Window.SCROLL_PANE)).setViewportView(chart.getChartView());
        } else {
            JOptionPane.showMessageDialog(window.getRoot(),
                    "No file was chosen!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
