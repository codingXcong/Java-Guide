package cn.zgc.network.bio.client;


import cn.zgc.network.nio.NioDemoConfig;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", NioDemoConfig.SOCKET_SERVER_PORT);

        System.out.println("连接的两个端口:" + socket.getPort() + socket.getLocalPort());

        if (socket != null) {
            System.out.println("客户端连接服务器成功！");
        }
        while (true) {
            System.out.println("请输入要发送的字符串：");

            String str = sc.next();

            socket.getOutputStream().write(str.getBytes());
        }
    }

}