package com.whu.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hulichao on 2017/11/24
 */
public class WebServer {

    ExecutorService pool = Executors.newCachedThreadPool();
    public void serverStart(int port){
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("服务器启动。。。端口：" + port);
                while(true){
                    Socket socket = serverSocket.accept();
                    pool.execute(new Processor(socket));
//                    new Processor(socket).start();
                }
            } catch  (IOException e) {
                e.printStackTrace();
            }
    }
    public static void main(String [] args){
        int port = 80;
        if (args.length == 1){
            port = Integer.valueOf(args[0]);
        }
        new WebServer().serverStart(port);
    }
}
