package com.whu.classload;

/**
 * Created by hulichao on 2017/12/6
 */
public class MyClassLoaderAttachMent {

    public MyClassLoaderAttachMent() {
        System.out.println("attachMent is loader by:" + this.getClass().getClassLoader());
    }

    public String toString(){
        return "hello,hulichao";
    }
}
