package com.dawang.androidsample;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description
 * @Author: louie.wang
 * @Email:
 * @Date: 2018/5/23
 */
public class AppendTest {

    @Test
    public void appendTest() throws IOException {
        FileOutputStream fos = new FileOutputStream("AppendTest.txt");

        for(int i = 0; i < 100; i++){
            fos.write(("HelloWorld"+i+"\n").getBytes());
        }
        fos.close();


        RandomAccessFile randomFile1 = new RandomAccessFile("AppendTest.txt", "rw");
        RandomAccessFile randomFile2 = new RandomAccessFile("AppendTest.txt", "rw");

        byte[] buffer1 = new byte[8];
        byte[] buffer2 = new byte[8];
        byte[] head = "louie\n".getBytes();

        int len1;
        int len2;

        len1 = randomFile1.read(buffer1, 0, buffer1.length);
        randomFile2.write(head, 0, head.length);


        while ((len2 = randomFile1.read(buffer2)) != -1){
            randomFile2.write(buffer1, 0, len1);

            byte[] temp = buffer1;
            buffer1 = buffer2;
            buffer2 = temp;
            len1 = len2;

            System.err.println(new String(buffer1));
        }

        randomFile1.close();
        System.err.println(new String(buffer1));
        randomFile2.write(buffer1, 0, len1);

        randomFile2.close();
    }
}
