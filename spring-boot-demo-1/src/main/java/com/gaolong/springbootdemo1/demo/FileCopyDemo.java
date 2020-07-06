package com.gaolong.springbootdemo1.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.channels.FileChannel;

public class FileCopyDemo {

    public static void main(String[] args) throws IOException {
        final File source = new File("pom.xml");// todo 为什么 idea 自动在前面加了 final ？
        final File dest1 = new File("pom_copy_test_1.xml");
        final File dest2 = new File("pom_copy_test_2.xml");
        copyFileFromStream(source, dest1);
        copyFileByChannel(source, dest2);
    }

    private static void copyFileFromStream(File source, File dest) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(source);
             FileOutputStream outputStream = new FileOutputStream(dest);) {
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    /**
     * 没怎么看懂！
     */
    private static void copyFileByChannel(File source, File dest) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                final long transferred = sourceChannel.transferTo(sourceChannel.position(), count, destChannel);
                count -= transferred;
            }
        }

    }

}
