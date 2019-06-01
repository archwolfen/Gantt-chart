package com.example.gantt_chart;

import com.example.gantt_chart.model.ActivityList;
import com.example.gantt_chart.parser.Parser;
import com.google.gson.JsonPrimitive;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        File schemaFile = new File("xmlconf.xsd");
//        Source xmlFile = new StreamSource(new File("chart_example.xml"));
//        SchemaFactory schemaFactory = SchemaFactory
//                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        try {
//            Schema schema = schemaFactory.newSchema(schemaFile);
//            Validator validator = schema.newValidator();
//            validator.validate(xmlFile);
//            System.out.println(xmlFile.getSystemId() + " is valid");
//            Parser parser = new Parser("chart_example.xml");
//            ActivityList a = parser.parse();
//            a.forEach(d->System.out.println(d.toJson()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }

        Server server = new Server();
        server.launch(80);
        System.out.println("Enter any symbol to stop server..");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        server.stop();
    }
}
