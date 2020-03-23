package Client;

import Client.Dao.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField username;
    private JPasswordField password_1;
    private JPasswordField password_2;
    private JButton confirmButton;

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 600, 600, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 50, 100, 20);
        contentPane.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 110, 100, 20);
        contentPane.add(passwordLabel);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(60, 170, 140, 20);
        contentPane.add(confirmPasswordLabel);

        username = new JTextField();
        username.setBounds(230, 40, 190, 24);
        contentPane.add(username);
        username.setColumns(10);


        password_1 = new JPasswordField();
        password_1.setBounds(230, 100, 190, 24);
        contentPane.add(password_1);

        password_2 = new JPasswordField();
        password_2.setBounds(230, 160, 190, 24);
        contentPane.add(password_2);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //判断用户名或者密码是否为空
                if(username.getText().equals("")||password_1.getText().equals("")||password_2.getText().equals("")){
                    System.out.println("警告,用户名和密码不能为空");
                    //判断2次输入的密码是否一样
                }else if (!password_1.getText().equals(password_2.getText())){
                    System.out.println("警告,二次输入的密码不一致");
                    //注册成功，关闭窗口
                } else{
                    new User().Register(username.getText(),Integer.parseInt(password_1.getText()));
                    System.out.println("注册成功");
                    dispose();
                }
            }
        });
        confirmButton.setBounds(120, 220, 335, 30);
        contentPane.add(confirmButton);
    }

}
