package com.company;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int b;
        FileInputStream fileInputStream = new FileInputStream("readme.txt");

        try {
            do {
                b = fileInputStream.read();
                if (b != -1) {
                    System.out.print((char) b);
                }
            } while (b != -1);
        } finally {
            fileInputStream.close();
        }
    }
}
