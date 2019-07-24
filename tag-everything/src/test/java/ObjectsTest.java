import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectsTest {

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

                        try (SocketChannel channel3 = (SocketChannel) key.channel()){
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
