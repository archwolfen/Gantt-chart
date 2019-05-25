package com.example.gantt_chart.parser;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Parser {

    private Document document;
    private Node root;

    public Parser(String pathToXml)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        document = documentBuilder.parse(pathToXml);
    }

    public ActivityList parse() {

        // Получаем корневой элемент
        root = document.getDocumentElement();

        System.out.println(root.getTextContent());

        return null;
    }

    private Dates getDates(Node node) {
        return null;
    }

    private Progress getProgress(Node node) {
        return null;
    }

    private Ids getIdList(Node node) {
        return null;
    }

    private SubActivities getSubActivities(Node node) {
        return null;
    }

    private Activity parse(Node node) {
        return null;
    }

}
