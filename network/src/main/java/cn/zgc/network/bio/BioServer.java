package cn.zgc.network.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangguicong
 * @date 2020-05-19
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        while(true) {

            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            Socket socket = serverSocket.accept();

            newCachedThreadPool.execute(new Runnable() {
                @Override public void run() {
                    handler(socket);
                }
            });
        }
    }

    private static void handler(Socket socket) {
        try {
            System.out.println("thread id =" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());
            byte[] bytes = new byte[16];
            //通过socket 获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            int readCount = 0;
            while (readCount >= 0) {

                System.out.println("thread id =" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());

                System.out.println("read....");
                readCount =  inputStream.read(bytes);
                if(readCount != -1) {
                    System.out.println(new String(bytes, 0, readCount)); //输出客户端发送的数据
                } else {
                    break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
