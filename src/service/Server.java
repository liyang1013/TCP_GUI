package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static HashMap<String, Socket> map;
    private static Set<String> names;
    static {
         map=new HashMap<String,Socket>();
         names=new HashSet<String>();
    }
    private final static Integer PORT=10086;

    public static void main(String[] args) {
        try {
            //开启服务
           ServerSocket serverSocket=new ServerSocket(PORT);
            System.out.println("服务器已启动");
            //服务器保持阻塞状态,等待连接
            while(true){
                Socket socket=serverSocket.accept();
                //开启线程连接客服端
                new Thread(new ClientToServer(socket,serverSocket)).start();
//                System.out.println(socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"用户"+count+"连接成功");
            }
        } catch (IOException e) {
             System.out.println("端口被占用");
        }
    }
    public  static Map<String,Socket> getMap(){
        return map;
    }
    public static Set<String> getNames(){return names;}
}
