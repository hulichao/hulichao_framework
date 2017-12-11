package com.whu.classload;

import java.io.*;

/**
 * Created by hulichao on 2017/12/6
 */
public class MyClassLoader extends ClassLoader{
    private String name; //类加载器的名字

    private String path = "d:\\"; //加载类的路径

    private final String fileType = ".class"; //class 文件的扩展名

    /**
     * 用来加密加载的类
     * @param ips
     * @param ops
     * @throws IOException
     */
    private static void cypher(InputStream ips, OutputStream ops) throws IOException {
        int b = -1;
        while ((b=ips.read()) != -1){
            ops.write(b ^ 0xff);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        return this.defineClass(name,data,0,data.length);
    }

    public MyClassLoader(){

    }
    public MyClassLoader(String name){
        super();//让系统类加载器成为该类加载器的父加载器

        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name){
        super(parent);//显示指定父加载器
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private byte[] loadClassData(String name){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream bos = null;

        try {
            this.name = this.name.replace(".",File.separator);

            is = new FileInputStream(new File(path + name + fileType));

            bos = new ByteArrayOutputStream();

            int ch = -1;
            while (-1 !=(ch = is.read())){
                bos.write(ch);
            }

            data = bos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                bos.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return data;
    }
}
