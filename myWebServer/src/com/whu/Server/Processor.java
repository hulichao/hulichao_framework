package com.whu.Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by hulichao on 2017/11/24
 */
public class Processor extends Thread {

    private static int vistorNum = 0;
    private Socket socket;
    private InputStream in;
    private PrintStream out;
    public final static String WEB_ROOT = "J:\\Hu_work\\tools\\hulichao_framework\\web\\htmDoc";

    public Processor(Socket socket) {
        this.socket = socket;
        try {
            in = socket.getInputStream();
            out = new PrintStream(socket.getOutputStream());
            System.out.println("总访问人数：" + this.vistorNum++);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String filename = parse(in);
        sendFile(filename);
    }

    public String parse(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String filename = null;
        try {
            String httpMeaage = br.readLine();
            String[] content = httpMeaage.split(" ");

            if (content.length != 3) {
                sendErrorMessage(400, "Client query error!");
                return null;
            }
            System.out.println("code:" + content[0] + ",\nfilename:" + content[1] + "\nhttp version:" + content[2]);
            filename = content[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public void sendErrorMessage(int errorCode, String errorMessage) {
        out.println("HTTP/1.0 " + errorCode + " " + errorMessage);
        out.println("content-type:text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> ERROR MESSAGE");
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> ErrorCode:" + errorCode + ",Message" + errorMessage + "</h1>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String filename) {
        File file = new File(Processor.WEB_ROOT + filename);
        System.out.println("请求资源全路径：" + file.getAbsolutePath());
        if (!file.exists()) {
            sendErrorMessage(405, "file not found!");
            return;
        }
        try {
            InputStream in = new FileInputStream(file);
            byte[] content = new byte[(int) file.length()];
            in.read(content);
            out.println("HTTP/1.0 200 queryfile");
            out.println("content-length:" + content.length);
            out.println();
            out.write(content);
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
