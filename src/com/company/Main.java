package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(3000)) {

            while (true) {
                service.submit(() -> {
                    try {
                        handleRequest(serverSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private static void handleRequest(ServerSocket serverSocket) throws IOException {
        try(Socket incomingRequest = serverSocket.accept()) {
            InputStream inputStream = incomingRequest.getInputStream();
            OutputStream outputStream = incomingRequest.getOutputStream();

            try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

                boolean done = false;

                while(!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    printWriter.println(line);

                    if (line.trim().equals("q")) {
                        done = true;
                    }
                }
            }
        }
    }
}
