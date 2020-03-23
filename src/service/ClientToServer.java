package service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class ClientToServer implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private String name;
    private static final String SUCCESS="success";

    public ClientToServer(Socket socket, ServerSocket serverSocket) {
        this.serverSocket=serverSocket;
        this.socket=socket;
    }

    @Override
    public void run() {
        String temp;
        try {
            //获取客服端输入流
            BufferedReader message=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取回车符
            while((temp=message.readLine())!=null){
                if(temp.equals("0")){
                    this.name=message.readLine();
                    Server.getMap().put(name,socket);
                    Server.getNames().add(name);
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    //把在线人发送给客服端
                    for(String name:Server.getNames()){
                        bufferedWriter.write(SUCCESS+"\n");
                        bufferedWriter.write(name+"\n");
                    }
                    bufferedWriter.flush();
                    System.out.println("用户"+name+":"+"上线");
                }
                if(temp.equals("-1")){
                    Server.getMap().remove(name);
                    System.out.println("用户"+name+"退出聊天");
                }
                if(temp.equals("1")) {
                    //获取目标名
                    String totalUser=message.readLine();
                    //获取目标名的输出流
                    if(!totalUser.equals("null")&&(!name.equals(totalUser))) {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Server.getMap().get(totalUser).getOutputStream()));
                        //读取要发送的信息
                        temp = message.readLine();
                        //给目标发送联系人和信息
                        bufferedWriter.write(name + "\n");
                        bufferedWriter.write(temp + "\n");
                        bufferedWriter.flush();
                    }else {
                        temp = message.readLine();
                        System.out.println("用户" + name + ":" + temp);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("客服端非正常下线");
        }
    }

}
