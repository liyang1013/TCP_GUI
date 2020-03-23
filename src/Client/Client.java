package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket=null;
    private static BufferedWriter bufferedWriter=null;
    private String name;
    private TextArea message;
    private JList online;
    private static BufferedReader bufferedReader;
    //开始信号量
    private static final String START="0";
    //发送信号量
    private static final String SEND="1";
    //退出信号量
    private  static final String EXIT="-1";


    public Client(String name) {
        this.name=name;
        JFrame jFrame = new JFrame(name);
        Container container = jFrame.getContentPane();
        container.setLayout(null);
        jFrame.setBounds(600,600,1000,780);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel L1 = new JLabel("IP");
        L1.setBounds(80,50,40,30);
        container.add(L1);

        JLabel L2 = new JLabel("PORT");
        L2.setBounds(80,100,40,30);
        container.add(L2);

        JTextField ip = new JTextField();
        ip.setText("127.0.0.1");
        ip.setBounds(300, 50, 120, 40);
        container.add(ip);

        JTextField port = new JTextField();
        port.setText("10086");
        port.setBounds(300, 100, 120, 40);
        container.add(port);

        JButton start = new JButton("开启连接");
        start.setBounds(850,50,100,30);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //判断是否已经连接
                    if(socket==null||socket.isClosed()) {
                        socket = new Socket(ip.getText(), Integer.parseInt(port.getText()));
                        new Thread(new ClienToClient()).start();
                        //Online.getDlm().addElement(name);
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bufferedWriter.write(START+"\n");
                        bufferedWriter.write(name+"\n");
                        bufferedWriter.flush();
                        message.append("成功连接服务器\n");
                    }else{
                        message.append("请先断开当前连接\n");
                    }
                } catch (IOException ex) {
                    message.append("请先开启服务器\n");
                }

            }

        });
        container.add(start);

        JButton stop = new JButton("断开连接");
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(socket.isClosed()||socket==null){
                    message.append("未连接\n");
                }else{
                    try {
                        Online.getDlm().removeAllElements();
                        bufferedWriter.write(EXIT+"\n");
                        bufferedWriter.flush();
//                        bufferedReader.close();
                        bufferedWriter.close();
                        socket.close();
                        message.append("断开已连接\n");
                    } catch (IOException ex) {
                        message.append("服务器失去连接\n");
                    }
                }
            }
        });
        stop.setBounds(850,100,100,30);
        container.add(stop);

        message = new TextArea();
        message.setBounds(250, 180, 800, 400);
        container.add(message);

        JLabel worldWordsLabel = new JLabel("msg:");
        worldWordsLabel.setBounds(250, 630, 70, 30);
        container.add(worldWordsLabel);

        JTextField msg = new JTextField();
        msg.setText("hello,world");
        msg.setBounds(350, 630, 300, 40);
        container.add(msg);
        msg.setColumns(10);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!socket.isClosed()) {
                        bufferedWriter.write(SEND+"\n");
                        bufferedWriter.write(online.getSelectedValue()+"\n");
                        bufferedWriter.write(msg.getText() + "\n");
                        bufferedWriter.flush();
                    }else{
                        message.append("请先建立连接\n");
                    }
                } catch (IOException ex) {
                    message.append("服务器失去连接\n");
                }
            }

        });
        sendButton.setBounds(800, 630, 100, 40);
        container.add(sendButton);

        online=Online.getJList();
        online.setBounds(50, 180, 180, 400);
        container.add(online);
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

    class ClienToClient implements Runnable{
        String sender;
        public void run() {
            try {

                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while((sender=bufferedReader.readLine())!=null){
                    if(sender.equals("success")){
                        Online.getDlm().addElement(bufferedReader.readLine());
                    }else {
                        message.append(sender + ":");
                        message.append(bufferedReader.readLine() + "\n");
                    }
                }
            } catch (IOException e) {
                if(sender==null)
                    message.append("已下线\n");
                else
                    message.append(sender+"已下线\n");
            }


        }
    }
}
