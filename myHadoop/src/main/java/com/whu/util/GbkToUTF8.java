package com.whu.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by hulichao on 2018/1/18
 */
public class GbkToUTF8 {
    public static void main(String[] args) throws IOException {
        //GBK编码格式源码路径
        String srcDirPath = "C:\\Users\\hulichao\\Desktop\\分布并行\\深证A股个股_日线_前复权\\深证A股gbk";
        //转为UTF-8编码格式源码路径
        String utf8DirPath = "C:\\Users\\hulichao\\Desktop\\分布并行\\深证A股个股_日线_前复权\\深圳A股UTF8";

        //获取所有java文件
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), new String[]{"CSV"}, true);

        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }
    }
}
