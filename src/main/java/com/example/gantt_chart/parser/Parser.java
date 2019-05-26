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

        ArrayList<Node> activityList = getElementNodes(root.getChildNodes());

        for(Node currActivity : activityList)
        {
            taskList.addActivity(parse(currActivity));
        }

        return taskList;
    }

    //for single activity
    private Activity parse(Node node)
            throws InvalidNameException, ClassCastException, URISyntaxException {

        Activity task;

        ArrayList<Node> dataList = getElementNodes(node.getChildNodes());

        if(getIndexOfElement("sub-activities", dataList) > -1)
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

                ((SummaryActivity)task).addActivity(getSubActivities(currData));

            } else {

                throw new InvalidNameException("Unexpected node name: " + currData.getNodeName());

            }
        }

        return task;
    }

    private Dates getDates(Node node) {
        ArrayList<Node> dates = getElementNodes(node.getChildNodes());

        String start = getTextValue(dates.get(getIndexOfElement("start", dates)));
        Date startDate = new Date(Long.parseLong(start));

        String end = getTextValue(dates.get(getIndexOfElement("end", dates)));
        Date endDate = new Date(Long.parseLong(end));

        return new Dates(startDate, endDate);
    }

    private ExecutorList getExecutorList(Node node) throws URISyntaxException {

        ArrayList<Node> executors = getElementNodes(node.getChildNodes());

        ExecutorList executorList = new ExecutorList();

        for(Node executor : executors)
        {
            executorList.addExecutor(getExecutor(executor));
        }

        return executorList;
    }

    private Executor getExecutor(Node node) throws URISyntaxException {

        ArrayList<Node> dates = getElementNodes(node.getChildNodes());

        String executorName = getTextValue(dates.get(getIndexOfElement("name", dates)));
        String executorSurname = getTextValue(dates.get(getIndexOfElement("surname", dates)));

        NamedNodeMap attributeList = dates.get(getIndexOfElement("photo", dates)).getAttributes();
        String photoURI = attributeList.getNamedItem("src").getNodeValue();

        return new Executor(executorName, executorSurname, new URI(photoURI));
    }

    private Progress getProgress(Node node) {
        String progress = getTextValue(node);

        return new Progress(Integer.parseInt(progress));
    }

    private Ids getIdList(Node node) {
        ArrayList<Node> ids = getElementNodes(node.getChildNodes());

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
        ArrayList<Node> activityList = getElementNodes(node.getChildNodes());

        SubActivities subActivities = new SubActivities();

        for(Node currActivity : activityList)
        {
            subActivities.addActivity(parse(currActivity));
        }

        return subActivities;
    }

    // TODO: 26.05.2019 Utilities

    private ArrayList<Node> getElementNodes(NodeList list) {

        ArrayList<Node> nodeList = new ArrayList<Node>();

        // TODO: 26.05.2019 NodeList iterable

        for(int i = 0; i < list.getLength(); i++)
        {
            Node currNode = list.item(i);
            if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                nodeList.add(list.item(i));
            }
        }

        return nodeList;
    }

    private int getIndexOfElement(String elem, ArrayList<Node> dataList)
    {
        // TODO: 26.05.2019 Спитати в Миколи як бути з цим гавном
        for (int i = 0; i < dataList.size(); i++)
        {
            if (dataList.get(i).getNodeName().equals(elem)) {
                return i;
            }
        }

        return -1;
    }

    private String getTextValue(Node node) {
        return node.getFirstChild().getNodeValue();
    }
}
