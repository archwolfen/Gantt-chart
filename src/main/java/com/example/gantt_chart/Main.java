package com.example.gantt_chart;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.model.activity.TerminalActivity;
import com.example.gantt_chart.parser.Parser;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("started...");

        String xmlPath = "chart_example.xml";

        File schemaFile = new File("xmlconf.xsd");
        Source xmlFile = new StreamSource(new File(xmlPath));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");

            try {
                Parser xmlParser = new Parser(xmlPath);
                ActivityList taskList = xmlParser.parse();

                for (TerminalActivity task : taskList)
                    System.out.println(task.toJson());

            } catch (Exception e)
            {
                e.printStackTrace();
            }

        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server server = new Server();
        server.launch(80);
        System.out.println("Enter any symbol to stop server..");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        server.stop();
    }
}
