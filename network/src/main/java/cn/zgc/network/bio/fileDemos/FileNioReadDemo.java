package cn.zgc.network.bio.fileDemos;


import cn.zgc.network.nio.NioDemoConfig;
import io.zgc.util.IOUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileNioReadDemo {

    public static final int CAPACITY = 1024;

    public static void main(String[] args) {
        readSourceFile();
    }

    /**
     * 读取
     */
    public static void readSourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String readPath = IOUtil.getResourcePath(sourcePath);

        System.out.println("readPath=" + readPath);
        nioReadFile(readPath);
    }


    /**
     * 读取文件内容并输出
     *
     * @param fileName 文件名
     */
    public static void nioReadFile(String fileName) {
        try {
            RandomAccessFile aFile = new RandomAccessFile(fileName, "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(CAPACITY);

            int length = -1;

            while ((length = inChannel.read(buf)) != -1) {
                buf.flip();
                byte[] bytes = buf.array();
                String str = new String(bytes, 0, length);
                System.out.println(str);
            }

            IOUtil.closeQuietly(inChannel);
            IOUtil.closeQuietly(aFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
