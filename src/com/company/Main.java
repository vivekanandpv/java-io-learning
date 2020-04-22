package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
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
}
