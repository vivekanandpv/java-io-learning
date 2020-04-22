package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putInt(100);
        byteBuffer.putInt(200);
        byteBuffer.putInt(300);
        byteBuffer.putInt(400);

        byteBuffer.flip();

        try(FileChannel fileChannel = FileChannel.open(Paths.get("ints.bin"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            fileChannel.write(byteBuffer);
        }

        System.out.println("File size: " + Files.size(Paths.get("ints.bin")) + " bytes");

        try(FileChannel fileChannel = FileChannel.open(Paths.get("ints.bin"), StandardOpenOption.READ)) {
            ByteBuffer readerBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(readerBuffer);
            readerBuffer.flip();
            IntBuffer intBuffer = readerBuffer.asIntBuffer();

            List<Integer> intsRead = new ArrayList<>();

            try {
                while(true) {
                    intsRead.add(intBuffer.get());
                }
            } catch (BufferUnderflowException e) {
                //...no action required here
            }

            //using stream API
            intsRead.forEach(System.out::println);
        }
    }
}
