package com.example.gantt_chart.parser;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.naming.InvalidNameException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        //clear previous parsing data
        ID.clear();

        ActivityList taskList = new ActivityList();

        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        NodesArrayList activityList = new NodesArrayList(root.getChildNodes());

        for (Node currActivity : activityList) {
            taskList.add(parse(currActivity));
        }

        return taskList;
    }

    //for single activity
    private TerminalActivity parse(Node node)
            throws InvalidNameException, ClassCastException, URISyntaxException, DatesException, IDException {

        TerminalActivity task;

        NodesArrayList dataList = new NodesArrayList(node.getChildNodes());
        NamedNodeMap activityInfo = node.getAttributes();


        if (dataList.hasElement("sub-activities")) {
            task = new SummaryActivity();
        } else {
            task = new TerminalActivity();
        }

        String activityTitle = activityInfo.getNamedItem("title").getNodeValue();
        String activityId = activityInfo.getNamedItem("id").getNodeValue();

        task.setTitle(activityTitle);
        task.setId(new ID(activityId, task));

        for (Node currData : dataList) {
            switch (currData.getNodeName()) {
                case "date":
                    task.setStartFinal(getDates(currData));
                    break;
                case "executors":
                    task.setExecutors(getExecutorList(currData));
                    break;
                case "progress":
                    task.setProgress(getProgress(currData));
                    break;
                case "dependencies":
                    task.setDependencies(getIdList(currData));
                    break;
                case "sub-activities":
                    ((SummaryActivity) task).addSubActivities(getSubActivities(currData));
                    break;
                default:
                    throw new InvalidNameException("Unexpected node name: " + currData.getNodeName());
            }
        }

        return task;
    }

    private Dates getDates(Node node) throws DatesException {
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

        for (Node executor : executors) {
            executorList.add(getExecutor(executor));
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

    private IDList getIdList(Node node) {
        NodesArrayList ids = new NodesArrayList(node.getChildNodes());

        IDList idList = new IDList();

        for (Node id : ids) {
            idList.add(getTextValue(id));
        }

        return idList;
    }

    private SubActivities getSubActivities(Node node)
            throws InvalidNameException, ClassCastException, URISyntaxException, DatesException, IDException {
        NodesArrayList activityList = new NodesArrayList(node.getChildNodes());

        SubActivities subActivities = new SubActivities();

        for (Node currActivity : activityList) {
            subActivities.add(parse(currActivity));
        }

        return subActivities;
    }

    private String getTextValue(Node node) {
        return node.getFirstChild().getNodeValue();
    }

//    // TODO: 26.05.2019 Utilities
//
//    private ArrayList<Node> getElementNodes(NodeList list) {
//
//        ArrayList<Node> nodeList = new ArrayList<Node>();
//
//        // TODO: 26.05.2019 NodeList iterable
//
//        for(int i = 0; i < list.getLength(); i++)
//        {
//            Node currNode = list.item(i);
//            if (currNode.getNodeType() == Node.ELEMENT_NODE) {
//                nodeList.add(list.item(i));
//            }
//        }
//
//        return nodeList;
//    }
//
//    // return index of node with name "elem", in case this node does not exists return -1
//
//    private int getIndexOfElement(String elem, ArrayList<Node> dataList)
//    {
//        // TODO: 26.05.2019 Спитати в Миколи як бути з цим гавном
//        for (int i = 0; i < dataList.size(); i++)
//        {
//            if (dataList.get(i).getNodeName().equals(elem)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }
}
