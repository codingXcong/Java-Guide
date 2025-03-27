package cn.zgc.network.bio.fileDemos;


import cn.zgc.network.nio.NioDemoConfig;
import io.zgc.util.IOUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopyDemo {

    /**
     * 演示程序的入口函数
     *
     * @param args
     */
    public static void main(String[] args) {
        //演示复制资源文件
        copyResourceFile();

    }

    /**
     * 复制两个资源目录下的文件
     */
    public static void copyResourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcDecodePath = IOUtil.getResourcePath(sourcePath);
        System.out.println("srcDecodePath=" + srcDecodePath);

        String destPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destDecodePath = IOUtil.builderResourcePath(destPath);
        System.out.println("destDecodePath=" + destDecodePath);


        //blockCopyFile(srcDecodePath, destDecodePath);

        nioCopyFile(srcDecodePath, destDecodePath);
    }


    /**
     * 复制文件
     *
     * @param srcPath
     * @param destPath
     */
    public static void blockCopyFile(String srcPath, String destPath) {
        InputStream input = null;
        OutputStream output = null;
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            //如果目标文件不存在，则新建
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            long startTime = System.currentTimeMillis();

            input = new FileInputStream(srcFile);
            output = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            long endTime = System.currentTimeMillis();
            System.out.println("IO流复制毫秒数：" + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(input);
            IOUtil.closeQuietly(output);
        }
    }

    /**
     * 复制文件
     *
     * @param srcPath
     * @param destPath
     */
    public static void nioCopyFile(String srcPath, String destPath) {

        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            //如果目标文件不存在，则新建
            if (!destFile.exists()) {
                destFile.createNewFile();
            }


            long startTime = System.currentTimeMillis();

            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outchannel = null;
            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outchannel = fos.getChannel();

                int length = -1;
                ByteBuffer buf = ByteBuffer.allocateDirect(1024);
                //从输入通道读取到buf
                while ((length = inChannel.read(buf)) != -1) {

                    //翻转buf,变成成读模式
                    buf.flip();

                    int outlength = 0;
                    //将buf写入到输出的通道
                    while ((outlength = outchannel.write(buf)) != 0) {
                        System.out.println("写入字节数：" + outlength);
                    }
                    //清除buf,变成写入模式
                    buf.clear();
                }


                //强制刷新磁盘
                outchannel.force(true);
            } finally {
                IOUtil.closeQuietly(outchannel);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(inChannel);
                IOUtil.closeQuietly(fis);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(" 复制毫秒数：" + (endTime - startTime));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
