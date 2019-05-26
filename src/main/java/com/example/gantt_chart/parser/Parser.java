package com.example.gantt_chart.parser;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private Document document;
    private Node root;

    public Parser(String pathToXml)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        document = documentBuilder.parse(pathToXml);
    }

    public ActivityList parse() throws Exception {

        // Получаем корневой элемент
        root = document.getDocumentElement();

        ArrayList<Node> activityList = getElementNodes(root.getChildNodes());

        for(Node currActivity : activityList)
        {
            Activity task = parse(currActivity);
        }

        return null;
    }

    //for single activity
    private Activity parse(Node node) throws Exception {

        Activity task;

        ArrayList<Node> dataList = getElementNodes(node.getChildNodes());

        if(hasElement("sub-activities", dataList))
            task = new SummaryActivity();
        else
            task = new TerminalActivity();

        for(Node currData : dataList) {
            if (currData.getNodeName().equals("date")) {
                Dates date = getDates(currData);
            } else if (currData.getNodeName().equals("executors")) {
                ExecutorList executorList = getExecutorList(currData);
            } else if (currData.getNodeName().equals("progress")) {
                Progress progress = getProgress(currData);
            } else if (currData.getNodeName().equals("next-ids")) {
                Ids idList = getIdList(currData);
            } else if (currData.getNodeName().equals("sub-activities")) {
                SubActivities subActivities = getSubActivities(currData);
            } else {
                throw new Exception("Unexpected node name: " + currData.getNodeName());
            }
        }

        return null;
    }

    private Dates getDates(Node node) {
        System.out.println("\t" + node.getTextContent());
        return null;
    }

    private ExecutorList getExecutorList(Node node) {
        System.out.println("\t" + node.getTextContent());
        return null;
    }

    private Progress getProgress(Node node) {
        System.out.println("\t" + node.getTextContent());
        return null;
    }

    private Ids getIdList(Node node) {
        System.out.println("\t" + node.getTextContent());
        return null;
    }

    private SubActivities getSubActivities(Node node) throws Exception {
        System.out.println("{");
        ArrayList<Node> activityList = getElementNodes(node.getChildNodes());

        for(Node currActivity : activityList)
        {
            Activity task = parse(currActivity);
        }
        System.out.println("}");
        return null;
    }

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

    private boolean hasElement(String elem, ArrayList<Node> dataList)
    {
        // TODO: 26.05.2019 Спитати в Миколи як бути з цим гавном
        for (Node currData : dataList)
        {
            if (currData.getNodeName().equals(elem)) {
                return true;
            }
        }

        return false;
    }
}
