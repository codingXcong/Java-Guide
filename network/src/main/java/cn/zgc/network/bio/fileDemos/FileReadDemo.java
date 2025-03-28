package cn.zgc.network.bio.fileDemos;


import cn.zgc.network.nio.NioDemoConfig;
import io.zgc.util.IOUtil;

import java.io.*;

public class FileReadDemo {

    /**
     * 演示程序的入口函数
     *
     * @param args
     */
    public static void main(String[] args) {
//        nioReadFile();
        readSourceFile();
    }

    /**
     * 读取
     */
    public static void readSourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String decodePath = IOUtil.getResourcePath(sourcePath);

        System.out.println("decodePath=" + decodePath);
        readFile(decodePath);
    }


    /**
     * 读取一个固定目录的文件
     */
    public static void readFile() {
        String fileName = "D:/iodemo/system.properties";

        readFile(fileName);
    }

    /**
     * 读取文件内容并输出
     *
     * @param fileName 文件名
     */
    public static void readFile(String fileName) {
        File file = new File(fileName);
        try {
            Reader reader = new FileReader(file);
            BufferedReader buffered = new BufferedReader(reader);
            String data = null;
            while ((data = buffered.readLine()) != null) {
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
