package com.example.gantt_chart;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.launch(80);
        System.out.println("Enter any symbol to stop server..");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        server.stop();
    }
}
