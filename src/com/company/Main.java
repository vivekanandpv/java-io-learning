package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

    public static void main(String[] args) throws IOException {
        // Reading
        FileChannel readerChannel = FileChannel.open(Paths.get("readme.txt"), StandardOpenOption.READ);
        FileChannel writerChannel = FileChannel.open(Paths.get("readme-copy.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        ByteBuffer readerBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writerBuffer = ByteBuffer.allocate(1024);

        readerChannel.read(readerBuffer);
        readerBuffer.flip();

        Charset utf8Charset = StandardCharsets.UTF_8;
        CharBuffer readerCharBuffer = utf8Charset.decode(readerBuffer);

        System.out.println(readerCharBuffer.toString());

        ByteBuffer writerByteBuffer = utf8Charset.encode(readerCharBuffer.toString());
        writerBuffer.put(readerBuffer);
        writerChannel.write(writerByteBuffer);

        readerChannel.close();
        writerChannel.close();
    }


}
