package cn.zgc.network.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * @author zhangguicong
 * @date 2020-05-19
 */
public class BioClient {

    public static void main(String[] args)  {
        try (Socket socket = new Socket("127.0.0.1",12345);) {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);

            pw.print("hello socket");
            pw.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
