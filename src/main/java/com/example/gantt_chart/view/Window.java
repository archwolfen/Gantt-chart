package com.example.gantt_chart.view;


import javax.swing.*;
import java.awt.*;

public class Window {
    public static final int SCROLL_PANE = 0;
    public static final int INFO_PANEL = 1;

    private static Window windowInstance = null;

    private JFrame window;

    private Window() {
        JFrame frame = new JFrame("Gantt chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER, SCROLL_PANE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem fileItem = new JMenuItem("Open file");
        fileItem.addActionListener(new OpenFileActionListener());
        fileMenu.add(fileItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        JPanel executorInfoPanel = new JPanel();
        executorInfoPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(executorInfoPanel, BorderLayout.SOUTH, INFO_PANEL);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        executorInfoPanel.add(infoPanel);
        infoPanel.add(new JLabel());

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        this.window = frame;
    }

    public static Window getInstance() {
        if (windowInstance == null) {
            windowInstance = new Window();
        }
        return windowInstance;
    }

    public Container getRoot() {
        return window.getContentPane();
    }
}
