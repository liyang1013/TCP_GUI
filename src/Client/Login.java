package Client;

import Client.Dao.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	private JButton registerButton;

public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(120, 60, 100, 30);
		contentPane.add(usernameLabel);

		JLabel passworLabel = new JLabel("Password:");
		passworLabel.setBounds(120, 120, 100, 30);
		contentPane.add(passworLabel);

		username = new JTextField();
		username.setBounds(240, 60, 200, 30);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(240, 120, 200, 30);
		contentPane.add(password);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=username.getText();
				String passwd=password.getText();
				if(new User().equals(name,passwd)){
					dispose();
					new Client(name);
				}else{
					System.out.println("账号密码错误");
				}
			}
		});
		loginButton.setBounds(130, 200, 120, 30);
		contentPane.add(loginButton);

		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Register().setVisible(true);
			}
		});
		registerButton.setBounds(330, 200, 120, 30);
		contentPane.add(registerButton);
	}
}
