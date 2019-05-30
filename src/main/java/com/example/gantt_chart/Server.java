package com.example.gantt_chart;

import com.example.gantt_chart.parser.Parser;
import com.google.gson.JsonPrimitive;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.stream.Collectors;

public class Server {
    public static final String TMP_XML_FILE = "tmp.xml";
    HttpServer server = null;

    public void launch(Integer port) throws IOException {
        if (server != null) {
            server.stop(0);
        }
        if (port < 1 || port > 65535) {
            port = 80;
        }
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 8);
        server.createContext("/get", new ChartRequestHandler());
        server.start();
        System.out.println(new Date().toString() + ": Started sever! Listening to port " + port);
    }

    public void stop() {
        server.stop(0);
    }

    //here we receive xml and return json
    static class ChartRequestHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if (method.equalsIgnoreCase("GET")) {
                String error = "<h1>403 Forbidden!</h1>";
                exchange.sendResponseHeaders(403, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }
            Headers headers = exchange.getRequestHeaders();
            Headers respHeaders = exchange.getResponseHeaders();
            if (!headers.containsKey("Content-Type")) {
                String error = "<h1>400 Bad request: Content-Type is missing!</h1>";
                respHeaders.add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(400, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }
            if (!(headers.getFirst("Content-Type").equals("application/xml") || headers.getFirst("Content-Type").equals("text/xml"))) {
                String error = "<h1>400 Bad request: Content-Type is not application/xml!</h1>";
                respHeaders.add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(400, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            String responseXml = new BufferedReader(
                    new InputStreamReader(
                            exchange.getRequestBody()
                    )
            ).lines().collect(Collectors.joining("\n"));
            //save into tmp file
            FileOutputStream fos = new FileOutputStream(TMP_XML_FILE);
            fos.write(responseXml.getBytes());
            fos.close();

            Parser parser = null;
            try {
                parser = new Parser(TMP_XML_FILE);
                //TODO send parsed data
                exchange.getResponseHeaders().set("Content-Type", "application/js");
                byte[] jsonBytes = parser.parse().toJson().toString().getBytes();
                exchange.sendResponseHeaders(200, jsonBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonBytes);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String errorReturnJson = new JsonPrimitive("Error").toString();
                exchange.sendResponseHeaders(200, errorReturnJson.length());
                OutputStream os = exchange.getResponseBody();
                os.write(errorReturnJson.getBytes());
                os.close();
            } finally {
                //deleting file
                new File(TMP_XML_FILE).delete();
            }
        }
    }
}
