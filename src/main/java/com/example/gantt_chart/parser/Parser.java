package com.example.gantt_chart.parser;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.naming.InvalidNameException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

public class Parser {

    private Document document;

    public Parser(String pathToXml)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        document = documentBuilder.parse(pathToXml);
    }

    public ActivityList parse() throws Exception {

        ActivityList taskList = new ActivityList();

        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        NodesArrayList activityList = new NodesArrayList(root.getChildNodes());

        for(Node currActivity : activityList)
        {
            taskList.addActivity(parse(currActivity));
        }

        return taskList;
    }

    //for single activity
    private TerminalActivity parse(Node node)
            throws InvalidNameException, ClassCastException, URISyntaxException {

        TerminalActivity task;

        NodesArrayList dataList = new NodesArrayList(node.getChildNodes());

        if(dataList.hasElement("sub-activities"))
            task = new SummaryActivity();
        else
            task = new TerminalActivity();

        for(Node currData : dataList) {
            if (currData.getNodeName().equals("date")) {

                task.setStartFinal(getDates(currData));

            } else if (currData.getNodeName().equals("executors")) {

                task.setExecutors(getExecutorList(currData));

            } else if (currData.getNodeName().equals("progress")) {

                task.setProgress(getProgress(currData));

            } else if (currData.getNodeName().equals("next-ids")) {

                task.setNextIds(getIdList(currData));

            } else if (currData.getNodeName().equals("sub-activities")) {

                ((SummaryActivity)task).addSubActivities(getSubActivities(currData));

            } else {

                throw new InvalidNameException("Unexpected node name: " + currData.getNodeName());

            }
        }

        return task;
    }

    private Dates getDates(Node node) {
        NodesArrayList dates = new NodesArrayList(node.getChildNodes());

        String start = getTextValue(dates.get(dates.indexOfElement("start")));
        Date startDate = new Date(Long.parseLong(start));

        String end = getTextValue(dates.get(dates.indexOfElement("end")));
        Date endDate = new Date(Long.parseLong(end));

        return new Dates(startDate, endDate);
    }

    private ExecutorList getExecutorList(Node node) throws URISyntaxException {

        NodesArrayList executors = new NodesArrayList(node.getChildNodes());

        ExecutorList executorList = new ExecutorList();

        for(Node executor : executors)
        {
            executorList.addExecutor(getExecutor(executor));
        }

        return executorList;
    }

    private Executor getExecutor(Node node) throws URISyntaxException {

        NodesArrayList dates = new NodesArrayList(node.getChildNodes());

        String executorName = getTextValue(dates.get(dates.indexOfElement("name")));
        String executorSurname = getTextValue(dates.get(dates.indexOfElement("surname")));

        NamedNodeMap attributeList = dates.get(dates.indexOfElement("photo")).getAttributes();
        String photoURI = attributeList.getNamedItem("src").getNodeValue();

        return new Executor(executorName, executorSurname, new URI(photoURI));
    }

    private Progress getProgress(Node node) {
        String progress = getTextValue(node);

        return new Progress(Integer.parseInt(progress));
    }

    private Ids getIdList(Node node) {
        NodesArrayList ids = new NodesArrayList(node.getChildNodes());

        Ids idList = new Ids();

        for (Node id : ids)
        {
            String newID = getTextValue(id);

            idList.addId(Integer.parseInt(newID));
        }

        return idList;
    }

    private SubActivities getSubActivities(Node node)
            throws InvalidNameException, ClassCastException, URISyntaxException {
        NodesArrayList activityList = new NodesArrayList(node.getChildNodes());

        SubActivities subActivities = new SubActivities();

        for(Node currActivity : activityList)
        {
            subActivities.addActivity(parse(currActivity));
        }

        return subActivities;
    }

    private String getTextValue(Node node) {
        return node.getFirstChild().getNodeValue();
    }
}
