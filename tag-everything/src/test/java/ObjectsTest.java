import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectsTest {

    @Test
    public void Test() {

//        long raw = (((long) Integer.MAX_VALUE) << 32) + Integer.MAX_VALUE;
//        System.out.println(raw);
//        System.out.println(Long.toBinaryString(raw));
//
//        System.out.println(LocalDate.now().getMonth().maxLength());
//
//        System.out.println(LocalDate.of(2019, 1, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 2, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 3, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 4, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 5, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 6, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 7, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 8, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 9, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 10, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 11, 1).getMonth().length(LocalDate.now().isLeapYear()));
//        System.out.println(LocalDate.of(2019, 12, 1).getMonth().length(LocalDate.now().isLeapYear()));


//        System.out.println(Integer.numberOfTrailingZeros(0));
//        System.out.println(Integer.numberOfLeadingZeros(0));
//
//        System.out.println(Integer.numberOfTrailingZeros(1 << 1));
//        System.out.println(Integer.numberOfLeadingZeros(1 << 1));
//
//        System.out.println(Integer.toBinaryString(1 << 1));
//        System.out.println(Integer.toBinaryString(Integer.reverse(Integer.MIN_VALUE)));
//        System.out.println(Integer.toBinaryString(Integer.reverseBytes(Integer.MIN_VALUE)));

//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(getComplement(1)));
//        System.out.println(Integer.toBinaryString(getComplement(Integer.MIN_VALUE)));

//        LocalDate localDate = LocalDate.now().minusMonths(1);
//        List<?> collapse = collapse(localDate, ((1 << 2) + 1) << 8);
//        System.out.println(collapse);
//
//        System.out.println(Integer.numberOfTrailingZeros(0));

        /*{
//            int i = Integer.valueOf("1111111111111111111111111111111", 2);
//            System.out.println(i);
//            System.out.println(Integer.toBinaryString(i));
//
//            System.out.println(i >>> 32);
//            System.out.println((long)(-1) >>> 32);

            System.out.println(Integer.toBinaryString(-1));
            System.out.println(Integer.toBinaryString((-1 >>> 31) >>> 1));
            System.out.println(Integer.toBinaryString(-1 >>> 32));


            Long aLong = Long.valueOf(Integer.toBinaryString(-1), 2);
            System.out.println(Long.toBinaryString(aLong));
            System.out.println(Long.toBinaryString(aLong >>> 32));


        }*/


        collapse(LocalDate.of(2019, 1, 1), Integer.valueOf("1111111111111111111111111111111", 2));
    }

    private int getComplement(int i) {
        return (int) ((1L << 32) - 1 - i);
    }

    private List<Integer> collapse(LocalDate month, int signInValue) {
        System.out.println(month);
        System.out.println(Integer.toBinaryString(signInValue));

        LocalDate today = LocalDate.now();

        int monthLength = month.getMonth().length(month.isLeapYear());
        if (month.getYear() == today.getYear() && month.getMonthValue() == today.getMonthValue()) {// 如果是当前月
            monthLength = today.getDayOfMonth();
        }
        int mask = 1 << monthLength;

        signInValue = signInValue | mask;

        List<Integer> bitCountList = new ArrayList<>();
        while (Integer.numberOfTrailingZeros(signInValue) != 32) {
            {
                int trailingZeros = Integer.numberOfTrailingZeros(signInValue);
                bitCountList.add(-trailingZeros);// index 为【偶数】的位置代表0的数量，负数代表0
                if (trailingZeros == 32) {
                    signInValue = 0;
                } else {
                    signInValue = signInValue >>> trailingZeros;
                }
            }
            {
                int trailingOnes = Integer.numberOfTrailingZeros(getComplement(signInValue));
                bitCountList.add(trailingOnes);// index 为【奇数】的位置代表1的数量；正数代表1
                if (trailingOnes == 32) {
                    signInValue = 0;
                } else {
                    signInValue = signInValue >>> trailingOnes;
                }
            }
        }
        {
            Integer maxIndexValue = bitCountList.get(bitCountList.size() - 1);
            if (maxIndexValue == 1) {
                bitCountList.remove(bitCountList.size() - 1);
            } else if (maxIndexValue > 1) {
                bitCountList.set(bitCountList.size() - 1, maxIndexValue - 1);
            } else {
                throw new RuntimeException("写错了！！！");
            }
        }
        {
            Integer minIndexValue = bitCountList.get(0);
            if (minIndexValue == 0) {
                bitCountList.remove(0);
            }
        }

        return bitCountList;
    }

    @Test
    public void nioCopyFileTest() {
        File file = new File("/Users/pxcm-0101-01-0128/Documents/OneDrive/网易博客日志列表.xml");
        File copyFile = new File("/Users/pxcm-0101-01-0128/Documents/OneDrive/网易博客日志列表_copy.xml");

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(copyFile)
        ) {

            FileChannel in_channel = fileInputStream.getChannel();
            FileChannel out_channel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (in_channel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                out_channel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void socketServerTest() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(9000), 100);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            LinkedBlockingQueue<SelectionKey> requestQueue = new LinkedBlockingQueue<>(500);
            ExecutorService threadPool = Executors.newFixedThreadPool(10);

            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    // todo
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void socketTest() {
        try (SocketChannel channel = SocketChannel.open()) {

            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("localhost", 9000));

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_CONNECT);

            while (true) {
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();

                while (keysIterator.hasNext()) {
                    SelectionKey key = keysIterator.next();
                    keysIterator.remove();
                    if (key.isConnectable()) {
                        try (SocketChannel channel1 = (SocketChannel) key.channel()) {
                            if (channel1.isConnectionPending()) {
                                channel1.finishConnect();

                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                buffer.put("你好".getBytes());
                                buffer.flip();
                                channel1.write(buffer);
                            }
                            channel1.register(selector, SelectionKey.OP_READ);
                        }

                    } else if (key.isReadable()) {
                        try (SocketChannel channel2 = (SocketChannel) key.channel()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int len = channel2.read(buffer);
                            if (len > 0) {
                                System.out.println("[" + Thread.currentThread().getName() + "]收到响应：" + new String(buffer.array(), 0, len));
                                Thread.sleep(5000);
                                channel2.register(selector, SelectionKey.OP_WRITE);
                            }
                        }
                    } else if (key.isWritable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put("你好".getBytes());
                        buffer.flip();

                        try (SocketChannel channel3 = (SocketChannel) key.channel()) {
                            channel3.write(buffer);
                            channel3.register(selector, SelectionKey.OP_READ);
                        }

                    }

                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
