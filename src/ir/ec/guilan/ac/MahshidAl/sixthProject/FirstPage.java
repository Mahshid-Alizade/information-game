package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class FirstPage extends JFrame {

	private JPanel contentPane;
	static FirstPage frame;
	public static JLabel proPic;// lable of proPic
	static ArrayList<Icon> proArray = new ArrayList<>();// Array list of proPic Icons
	static JTextField txtID;// name text field
	static boolean isPlaying = false;
	static boolean let = false;
	private JPasswordField passwordField;// J passwordField
	GirlyPic obj = new GirlyPic();
	private JLabel lblWEL;
	static JLabel errorLabel;// the label of error massege
	private static String name;// players name

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FirstPage();
					frame.setLocation(530, 100);
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
	public FirstPage() {

		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(10, 155, 257, 36);
		contentPane.add(panel);
		panel.setLayout(null);

		txtID = new JTextField();

		// mous listener of name text field
		txtID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtID.setText("");
				errorLabel.setText("");
				passwordField.setText("");
			}
		});
		txtID.setForeground(Color.GRAY);
		txtID.setFont(new Font("Agency FB", Font.BOLD, 16));
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setText("  N a m e ");
		txtID.setBounds(44, 0, 213, 36);
		panel.add(txtID);
		txtID.setColumns(10);
		
		
		JLabel label = new JLabel("");
		label.setBounds(6, 0, 40, 36);
		panel.add(label);
		label.setIcon(new ImageIcon(FirstPage.class.getResource("/ir/ec/guilan/ac/MahshidAl/sixthProject/images/user.png")));


		String user_dir = System.getProperty("user.dir");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(10, 202, 257, 36);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				errorLabel.setText("");
			}
		});
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(44, 0, 213, 36);
		panel_1.add(passwordField);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FirstPage.class.getResource("/ir/ec/guilan/ac/MahshidAl/sixthProject/images/pass.png")));
		label_1.setBounds(6, 0, 44, 36);
		panel_1.add(label_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.BLACK, 2));
		panel_4.setBounds(10, 312, 124, 56);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		//log in button
		JButton btnENR = new JButton("L o g   I n");
		btnENR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setName(txtID.getText());
				WaitingPage.main(null);
				String sendToFile;

				// if player host
				if (JoinOrHost.host) {
					sendToFile = txtID.getText().trim() + "   " + GamePage.port + "\r\n";
					proArray.add(proPic.getIcon());
					NecesseryMethods.sendToFile(sendToFile, "Host.txt");
					NecesseryMethods.sendToFile(txtID.getText() + "\r\n", "temp.txt");
					JoinOrHost.frame.setVisible(true);
				}

				// if player join
				else if (JoinOrHost.join) {
					proArray.clear();
					NecesseryMethods.sendToFile(txtID.getText() + "\r\n", "temp.txt");
					GamePage2.joinName = txtID.getText();
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
		});
		btnENR.setToolTipText("already have an account?");
		btnENR.setBounds(2, 2, 120, 52);
		panel_4.add(btnENR);
		btnENR.setForeground(Color.WHITE);
		btnENR.setBackground(new Color(51, 153, 0));
		btnENR.setFont(new Font("Agency FB", Font.BOLD, 15));

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(Color.BLACK, 2));
		panel_5.setBounds(143, 312, 124, 56);
		contentPane.add(panel_5);

		//if player play for the first time
		JButton btnSIG = new JButton("S i g n   U p");
		btnSIG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.main(null);
				frame.setVisible(false);
			}
		});
		btnSIG.setToolTipText("Don't have an account??");
		btnSIG.setForeground(Color.WHITE);
		btnSIG.setFont(new Font("Agency FB", Font.BOLD, 15));
		btnSIG.setBackground(new Color(51, 153, 0));
		btnSIG.setBounds(2, 2, 120, 52);
		panel_5.add(btnSIG);

		lblWEL = new JLabel("W e l l  C o m e   :D");
		lblWEL.setHorizontalAlignment(SwingConstants.CENTER);
		lblWEL.setForeground(Color.WHITE);
		lblWEL.setFont(new Font("Agency FB", Font.BOLD, 21));
		lblWEL.setBounds(58, -1, 151, 36);
		contentPane.add(lblWEL);

		errorLabel = new JLabel("");
		errorLabel.setBounds(10, 275, 257, 30);
		contentPane.add(errorLabel);

		
		//confirmation  information of player
		JButton btnOK = new JButton("O K");
		btnOK.setForeground(Color.WHITE);
		btnOK.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = 0;
				try {
					x = NecesseryMethods.readFromFile(txtID.getText().trim(), new String(passwordField.getPassword()));
				} catch (IOException e) {
				}

				switch (x) {
				//password is incorrect
				case -1: {
					errorLabel.setText("your password is incorrect!!");
					break;
				}
				//new user
				case -2: {
					errorLabel.setText("this userName is not available!!");
					break;
				}
				//sign up 
				case -3: {
					errorLabel.setText("sign Up !!");
					break;

				}

				default: {

					BufferedReader in;
					try {
						in = new BufferedReader(new FileReader("temp.txt"));
						String c;
						while ((c = in.readLine()) != null) {
							if (c.equals(txtID.getText().trim())) {
								isPlaying = true;
								break;
							}
						}
					} catch (FileNotFoundException e) {
					} catch (IOException e) {
					}

					//if we use this name one more time
					if (isPlaying) {
						errorLabel.setText("this user is Playing!!");
						txtID.setText("");
						passwordField.setText("");
					} else {
						proPic.setIcon(new ImageIcon(NecesseryMethods.proPicture.get(x)));
						errorLabel.setText("WellCome  " + NecesseryMethods.name.get(x));
					}

					isPlaying = false;

				}

				}
			}
		});
		btnOK.setBackground(new Color(51, 153, 0));
		btnOK.setBounds(204, 247, 57, 23);
		contentPane.add(btnOK);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel_2.setBounds(71, 46, 138, 98);
		contentPane.add(panel_2);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\6thProject\\6thProject\\src\\ir\\ec\\guilan\\ac\\MahshidAl\\sixthProject\\images\\1.jpg"));
		label_2.setBounds(4, 4, 130, 90);
		panel_2.add(label_2);

		proPic = new JLabel("");
		proPic.setBounds(4, 4, 130, 90);
		panel_2.add(proPic);

		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		proPic.setIcon(new ImageIcon(user_dir + "6thProject\\src\\ir\\ec\\guilan\\ac\\MahshidAl\\sixthProject\\images\\1.jpg"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
