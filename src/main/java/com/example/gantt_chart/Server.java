package com.example.gantt_chart;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.xml.internal.ws.api.message.Header;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Date;

public class Server {
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
            if (headers.getFirst("Content-Type") != "application/xml") {
                String error = "<h1>400 Bad request: Content-Type is not application/xml!</h1>";
                respHeaders.add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(400, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            InputStream is = exchange.getRequestBody();
            byte[] buffer = new byte[2048];
            while (is.read(buffer) != -1) {
                System.out.println(buffer);
            }
            is.close();

            //response
            exchange.getResponseHeaders().set("Content-Type", "application/js");
            //here we return json array of activities(code below in test purposes only)
            JsonObject object = new JsonObject();
            object.addProperty("kaka", 3);
            object.addProperty("kee", "safsaf");
            exchange.sendResponseHeaders(200, object.toString().length());
            OutputStream os = exchange.getResponseBody();
            os.write(object.toString().getBytes());
            os.close();
        }
    }
}
