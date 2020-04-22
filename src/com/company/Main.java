package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);  // 1MB

        byteBuffer.putInt(100);
        byteBuffer.putInt(200);
        byteBuffer.putInt(300);
        byteBuffer.putInt(400);
        byteBuffer.putInt(500);

        System.out.println("Before Flip: Position: " + byteBuffer.position());
        System.out.println("Before Flip: Limit: " + byteBuffer.limit());

        byteBuffer.flip();

        System.out.println("After Flip: Position: " + byteBuffer.position());
        System.out.println("After Flip: Limit: " + byteBuffer.limit());

        IntBuffer intBuffer = byteBuffer.asIntBuffer();

        for (int i =0; i < intBuffer.limit(); i++) {
            System.out.println("> " + intBuffer.get());
        }
    }


}
