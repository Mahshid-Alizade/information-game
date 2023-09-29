package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passField;
	private JPasswordField confirmPass;
	static JLabel proPic ;
	private JLabel errorLabel;
	static boolean itIs = false;
	static SignUpPage frame ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SignUpPage();
					frame.setLocation(530 , 100);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpPage() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 514);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(88, 408, 124, 56);
		contentPane.add(panel);
		
		//sign up button
		JButton button = new JButton("S i g n   U p");
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					NecesseryMethods.readFromFile(nameField.getText(), new String(passField.getPassword()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(!itIs) {
					itIs = false;
				if(nameField.getText().equals("  N a m e ") || nameField.getText().equals(""))
					errorLabel.setText("enter your name!!");
				else if(!(new String(passField.getPassword())).equals(new String(confirmPass.getPassword())))
					errorLabel.setText("Please make sure your passwords match !!!");
				else if((new String(passField.getPassword())).length() < 6)
					errorLabel.setText("your password should be more than 6 character!!");
				else {
					String sendToFile;
					sendToFile = nameField.getText() + "   " + new String(passField.getPassword()) + "   " + proPic.getIcon() + "\r\n";
					NecesseryMethods.sendToFile(sendToFile , "information.txt");
					
					WaitingPage.main(null);
					if(JoinOrHost.host) {
						FirstPage.proArray.add(proPic.getIcon());
						sendToFile = nameField.getText().trim() + "   " + GamePage.port + "\r\n";
						NecesseryMethods.sendToFile(sendToFile, "Host.txt");
						NecesseryMethods.sendToFile(nameField.getText() + "\r\n", "temp.txt");
						JoinOrHost.frame.setVisible(true);
					}
					else if(JoinOrHost.join) {
						FirstPage.proArray.clear();
						NecesseryMethods.sendToFile(nameField.getText() + "\r\n", "temp.txt");
						GamePage2.joinName = nameField.getText() ;
						GamePage2.joinProPic = proPic.getIcon();
						try {
							NecesseryMethods.clearFile("temp.txt");
						} catch (IOException e) {
						}
						
						try {
							GamePage2.main(null);
							GamePage.main(null);
						} catch (InterruptedException e) {
						}
						
					}
					
					frame.setVisible(false);
				}
				
				
				
				}else {
					errorLabel.setText("this userName is exist!!");
					passField.setText("");
					confirmPass.setText("");
					nameField.setText("");
					proPic.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_234620.jpg"));
					itIs = false;
				}
				
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Agency FB", Font.BOLD, 15));
		button.setBackground(new Color(51, 153, 0));
		button.setBounds(2, 2, 120, 52);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 128, 0), 3));
		panel_1.setBounds(77, 347, 56, 50);
		contentPane.add(panel_1);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GirlyPic.main(null);
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_223812.jpg"));
		button_1.setBounds(3, 3, 50, 44);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 128, 0), 3));
		panel_2.setBounds(166, 347, 56, 50);
		contentPane.add(panel_2);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BoyPic.main(null);
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_223757.jpg"));
		button_2.setBounds(3, 3, 50, 44);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(20, 174, 245, 36);
		contentPane.add(panel_3);
		
		nameField = new JTextField();
		nameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nameField.setText("");
			}
		});
		nameField.setText("  N a m e ");
		nameField.setHorizontalAlignment(SwingConstants.LEFT);
		nameField.setForeground(Color.GRAY);
		nameField.setFont(new Font("Agency FB", Font.BOLD, 16));
		nameField.setColumns(10);
		nameField.setBounds(44, 0, 201, 36);
		panel_3.add(nameField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_232915.jpg"));
		label.setBounds(0, 0, 265, 36);
		panel_3.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(20, 216, 245, 36);
		contentPane.add(panel_4);
		
		passField = new JPasswordField();
		passField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				errorLabel.setText("");
				passField.setText("");
			}
		});
		passField.setHorizontalAlignment(SwingConstants.LEFT);
		passField.setBounds(44, 0, 201, 36);
		panel_4.add(passField);
		
		JLabel label_1 = new JLabel("");
		label_1.setToolTipText("enter your password");
		label_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_233038.jpg"));
		label_1.setBounds(0, 0, 245, 36);
		panel_4.add(label_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(20, 258, 245, 36);
		contentPane.add(panel_5);
		
		confirmPass = new JPasswordField();
		confirmPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			errorLabel.setText("");
			}
		});
		confirmPass.setHorizontalAlignment(SwingConstants.LEFT);
		confirmPass.setBounds(44, 0, 201, 36);
		panel_5.add(confirmPass);
		
		JLabel label_2 = new JLabel("");
		label_2.setToolTipText("Confirm PassWord");
		label_2.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_233038.jpg"));
		label_2.setBounds(0, 0, 265, 36);
		panel_5.add(label_2);
		
		JLabel lblSIG = new JLabel("S i g n   U p");
		lblSIG.setHorizontalAlignment(SwingConstants.CENTER);
		lblSIG.setForeground(Color.WHITE);
		lblSIG.setFont(new Font("Agency FB", Font.BOLD, 21));
		lblSIG.setBounds(41, 16, 212, 35);
		contentPane.add(lblSIG);
		
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
		errorLabel.setForeground(new Color(0, 0, 0));
		errorLabel.setBounds(10, 305, 279, 36);
		contentPane.add(errorLabel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel_6.setBounds(79, 62, 138, 98);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		proPic = new JLabel("");
		proPic.setBounds(4, 4, 130, 90);
		panel_6.add(proPic);
		proPic.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_234620.jpg"));
	}
}
