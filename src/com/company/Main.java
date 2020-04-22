package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

            for (int i = 0; i < intBuffer.limit(); i++) {
                System.out.println("> " + intBuffer.get());
            }
        }
    }
}
