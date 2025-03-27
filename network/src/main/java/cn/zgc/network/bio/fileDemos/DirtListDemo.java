package cn.zgc.network.bio.fileDemos;


import io.zgc.util.IOUtil;

import java.io.File;
import java.io.IOException;

public class DirtListDemo {

    public static void main(String[] args) {
        String path = IOUtil.getResourcePath("/");
        try {
            listFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listFile(String path) throws IOException {

        // 需要进行判空的校验
        // 如果path为空，后边的构造File对象会报异常
        if (path == null) {
            return;
        }

        File pFile = new File(path);
        if (pFile.isFile()) {
            //文件：输出完整名称
            System.out.println("File:" + pFile.getCanonicalPath());
        } else if (pFile.isDirectory()) {
            //目录：输出完整名称
            System.out.println("Directory:" + pFile.getCanonicalPath());
            File[] subFiles = pFile.listFiles();
            if (subFiles == null) {
                return;
            }
            //目录：递归遍历每一个下级File对象
            for (File file : subFiles) {
                listFile(file.getCanonicalPath());
            }
        } else {
            System.out.println("error:" + pFile.getCanonicalPath());
        }


    }


}
