package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;

public class GamePage extends JFrame implements Runnable {

	static ArrayList<String> randomLetter = new ArrayList<>();
	private JPanel contentPane;
	static JTable table;
	private static JLabel timeLabel;
	static Random random = new Random();
	static boolean isRunning = false;
	static char x;
	static int finalPOINT;// final point of server
	static int finalPOINT2;// finl point of client
	static JLabel errorlbl;
	static JLabel lblRound;// show round of game
	static JLabel pointlbl;// show point of player
	static JLabel nameLbl;
	static Icon hostProPic;// pro pic of this player

	// value of other players table
	static String clientFirstName;
	static String clientLastName;
	static String clientCountry;
	static String clientAnimal;
	static String clientFruit;
	static String clientInformation;
	static String hostName = "";
	private static BufferedReader in;
	private static PrintWriter out;
	private static ServerSocket listener;
	static GamePage frame;

	/**
	 * Launch the application.
	 */
	static Thread thread = new Thread();

	public static void main(String[] args) throws InterruptedException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GamePage();
					frame.setLocation(700, 0);
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
	static int sec = 180;
	static Timer timer;

	public GamePage() {

		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 584);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 634, 306);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(new Object[][] {},
			new String[] { "First Name", "Last Name", "Country", "Animal", "fruit", "Points", "Letter" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					Object.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(314, 3, 183, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		timeLabel = new JLabel("180");
		timeLabel.setBackground(new Color(0, 153, 0));
		timeLabel.setBounds(3, 3, 177, 42);
		panel.add(timeLabel);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Agency FB", Font.BOLD, 35));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 0));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(314, 57, 183, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// this button start the game
		JButton btnNewButton = new JButton("S T A R T");
		btnNewButton.setBounds(3, 3, 177, 45);
		panel_1.add(btnNewButton);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() < 5) {

					// choose random letter
					do {
						x = (char) ('A' + random.nextInt(26));
					} while (randomLetter.contains(x + ""));

					randomLetter.add(x + "");

					startTime();
					// send protocol
					out.println("START" + x);
					// set round of game
					lblRound.setText(" Round  :   " + model.getRowCount());
				} else {
					lblRound.setText("Game is over");
					out.println("GAME-IS-OVER");
					pointlbl.setText(NecesseryMethods.finalPoint(table) + "");
				}

			}
		});
		btnNewButton.setBackground(new Color(51, 153, 0));
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 20));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(314, 112, 183, 50);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// this button stop the game
		JButton btnSTO = new JButton("S T O P");
		btnSTO.setBounds(3, 3, 177, 45);
		panel_2.add(btnSTO);
		btnSTO.setFocusable(false);
		btnSTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() <= 5) {
					//ask value of other players table
					out.println("GIVE-INFORMATION");
					sec = 180;
					isRunning = false;
					out.println("STOP");
					timer.stop();

					String s = "";
					//senf information
					s = model.getValueAt(model.getRowCount() - 1, 0) + " * ";
					s += model.getValueAt(model.getRowCount() - 1, 1) + " * ";
					s += model.getValueAt(model.getRowCount() - 1, 2) + " * ";
					s += model.getValueAt(model.getRowCount() - 1, 3) + " * ";
					s += model.getValueAt(model.getRowCount() - 1, 4) + " * ";
					out.println("GET-INFORMATION" + s);

				}
				
				if (model.getRowCount() == 5) {
					lblRound.setText("Game is over");
					finalPOINT = NecesseryMethods.finalPoint(table);
					pointlbl.setText(finalPOINT + "");
					out.println("GET-POINT" + finalPOINT);
					out.println("GAME-IS-OVER");
				}
				
			}
		});
		btnSTO.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnSTO.setBackground(new Color(51, 153, 0));

		JLabel lblServer = new JLabel("H O S T");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setFont(new Font("Agency FB", Font.BOLD, 35));
		lblServer.setBackground(new Color(0, 153, 0));
		lblServer.setBounds(172, 11, 132, 50);
		contentPane.add(lblServer);

		JLabel proLabel = new JLabel("");
		proLabel.setBackground(SystemColor.control);
		proLabel.setBounds(10, 11, 132, 89);
		proLabel.setIcon(hostProPic);
		contentPane.add(proLabel);
		
		String user_dir = System.getProperty("user.dir");

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(user_dir + "6thProject\\src\\ir\\ec\\guilan\\ac\\MahshidAl\\sixthProject\\images\\1.jpg"));
		label_1.setBounds(507, 6, 137, 156);
		contentPane.add(label_1);

		//we can choose "New Game" or "Record"
		JLabel lblMOR = new JLabel("M O R E");
		lblMOR.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblMOR.setHorizontalAlignment(SwingConstants.CENTER);
		lblMOR.setBounds(172, 486, 152, 50);
		contentPane.add(lblMOR);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(lblMOR, popupMenu);

		
		//button for start a new game
		JMenuItem mntmNewMenuItem = new JMenuItem("N e w   G a m e");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FirstPage.frame.dispose();
				GamePage.frame.dispose();
				out.println("DISPOSE");
				JoinOrHost.frame.dispose();
				ListOfHosts.frame.dispose();
				WaitingPage.frame.dispose();

				JoinOrHost.main(null);

			}
		});
		mntmNewMenuItem.setFont(new Font("Agency FB", Font.PLAIN, 15));
		popupMenu.add(mntmNewMenuItem);

		
		//show record of this player
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("R e c o r d");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecordPage.nameOfPlyer = nameLbl.getText();
				RecordPage.main(null);
				GamePage.frame.setVisible(false);
				out.println("DISPOSE");
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Agency FB", Font.PLAIN, 15));
		popupMenu.add(mntmNewMenuItem_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBackground(new Color(0, 153, 0));
		panel_3.setBounds(10, 486, 152, 48);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		lblRound = new JLabel(" Round  :   ");
		lblRound.setBounds(0, 0, 152, 48);
		panel_3.add(lblRound);
		lblRound.setHorizontalAlignment(SwingConstants.CENTER);
		lblRound.setFont(new Font("Agency FB", Font.BOLD, 30));

		errorlbl = new JLabel();
		errorlbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorlbl.setFont(new Font("Agency FB", Font.BOLD, 30));
		errorlbl.setBounds(314, 486, 330, 48);
		contentPane.add(errorlbl);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.RED);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_4.setBounds(10, 112, 132, 48);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		nameLbl = new JLabel(hostName);
		nameLbl.setBounds(0, 0, 132, 48);
		panel_4.add(nameLbl);
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setFont(new Font("Agency FB", Font.BOLD, 30));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_5.setBackground(Color.RED);
		panel_5.setBounds(152, 112, 152, 48);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		pointlbl = new JLabel("");
		pointlbl.setBounds(0, 0, 152, 48);
		panel_5.add(pointlbl);
		pointlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pointlbl.setFont(new Font("Agency FB", Font.BOLD, 35));
		pointlbl.setBackground(new Color(0, 153, 0));
	}

	static int port;

	@Override
	public void run() {

		Socket socket = null;

		port = Integer.parseInt(portGetter());

		FirstPage.main(null);

		try {
			listener = new ServerSocket(port);

			socket = listener.accept();

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				String line = in.readLine();
				//TEXT Protocols
				if (line.trim().equals("STOP")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
						//ask value of other players table
						sec = 180;
						isRunning = false;
						timer.stop();

						String s = "";
						//senf information
						s = model.getValueAt(model.getRowCount() - 1, 0) + " * ";
						s += model.getValueAt(model.getRowCount() - 1, 1) + " * ";
						s += model.getValueAt(model.getRowCount() - 1, 2) + " * ";
						s += model.getValueAt(model.getRowCount() - 1, 3) + " * ";
						s += model.getValueAt(model.getRowCount() - 1, 4) + " * ";
						out.println("GET-INFORMATION" + s);


				} else if (line.trim().startsWith("START")) {
					x = line.substring(5).charAt(0);
					startTime();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					lblRound.setText(" Round  :   " + model.getRowCount());
				} else if (line.equals("GAME-IS-OVER")) {//if other user press "stop" button
					lblRound.setText("Game is over");
					finalPOINT = NecesseryMethods.finalPoint(table);
					pointlbl.setText(finalPOINT + "");
					out.println("GET-POINT" + finalPOINT);
				} else if (line.equals("YOU-WIN")) {//if this player win
					errorlbl.setText("Y O U   W I N   D E A R  :D");
					String sendToFile = "";
					sendToFile = nameLbl.getText() + "\r\n";
					NecesseryMethods.sendToFile(sendToFile, "winner.txt");
				} else if (line.equals("YOU-LOSS")) {//if this player loss
					errorlbl.setText("Y O U   L O S S   B A B Y  ;(");
					String sendToFile = "";
					sendToFile = nameLbl.getText() + "\r\n";
					NecesseryMethods.sendToFile(sendToFile, "losser.txt");
				} else if (line.equals("EQUAL")) {
					errorlbl.setText("E Q U A L");
					String sendToFile = "";
					sendToFile = nameLbl.getText() + "\r\n";
					NecesseryMethods.sendToFile(sendToFile, "equals.txt");
				} else if (line.startsWith("GET-POINT")) {
					finalPOINT2 = Integer.parseInt(line.substring(9));
					finalPOINT = NecesseryMethods.finalPoint(table);
					int result = NecesseryMethods.whoWin(finalPOINT, finalPOINT2);
					pointlbl.setText(finalPOINT + "");
					
					switch (result) {
					case 1: {
						errorlbl.setText("Y O U   W I N   D E A R  :D");
						String sendToFile = "";
						sendToFile = nameLbl.getText() + "\r\n";
						NecesseryMethods.sendToFile(sendToFile, "winner.txt");
						break;
					}
					case -1: {
						errorlbl.setText("Y O U   L O S S   B A B Y  ;( ");
						String sendToFile = "";
						sendToFile = nameLbl.getText() + "\r\n";
						NecesseryMethods.sendToFile(sendToFile, "losser.txt");
						out.println("YOU-WIN");
						break;
					}
					case 0: {
						errorlbl.setText("E Q U A L I Z A T I O N");
						String sendToFile = "";
						sendToFile = nameLbl.getText() + "\r\n";
						NecesseryMethods.sendToFile(sendToFile, "equals.txt");
						out.println("EQUAL");
						break;
					}
					}
				} 
				else if (line.equals("DISPOSE")) {
					frame.dispose();
				} else if (line.startsWith("GET-INFORMATION")) {
					clientInformation = line.substring(15);
					clientFirstName = clientInformation.split(" * ")[0];
					clientLastName = clientInformation.split(" * ")[1];
					clientCountry = clientInformation.split(" * ")[2];
					clientAnimal = clientInformation.split(" * ")[3];
					clientFruit = clientInformation.split(" * ")[4];
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setValueAt(getPoint(), model.getRowCount() - 1, 5);
				}
			}
		} catch (Exception e) {

		}

	}

	//this method start the timer
	public static void startTime() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (!isRunning) {
			timeLabel.setText(180 + "");
			isRunning = true;

			model.addRow(new Object[] { "", "", "", "", "", "", x });
			lblRound.setText(" Round  :   " + model.getRowCount());
			timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (sec > 0) {
						sec--;

						timeLabel.setText(sec + "");
					} else {
						//if time was less than 0
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						if (model.getRowCount() <= 5) {
							sec = 180;
							isRunning = false;
							out.println("STOP");
							timer.stop();

							String s = "";
							s = model.getValueAt(model.getRowCount() - 1, 0) + " * ";
							s += model.getValueAt(model.getRowCount() - 1, 1) + " * ";
							s += model.getValueAt(model.getRowCount() - 1, 2) + " * ";
							s += model.getValueAt(model.getRowCount() - 1, 3) + " * ";
							s += model.getValueAt(model.getRowCount() - 1, 4) + " * ";
							out.println("GET-INFORMATION" + s);

						}
						if (model.getRowCount() == 5) {
							lblRound.setText("Game is over");
							out.println("GAME-IS-OVER");
							out.println("GIVE-POINT");

						}

					}
				}

			});

			timer.start();

		}
	}

	// ask for server port
	private static String portGetter() {
		return JOptionPane.showInputDialog(null, "Enter server port:", "Wellcome", JOptionPane.QUESTION_MESSAGE);
	}

	
	// set point of each row
	private static int getPoint() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int point = 0;
		try {
			if (NecesseryMethods.findFirstName((String) (model.getValueAt(model.getRowCount() - 1, 0)), table)) {
				if (NecesseryMethods.findFirstName(clientFirstName, table)) {
					if (clientFirstName.equals(model.getValueAt(model.getRowCount() - 1, 0))) {
						point += 5;
					} else {
						point += 10;
					}
				} else {
					point += 20;
				}

			}

			if (NecesseryMethods.findLastName((String) (model.getValueAt(model.getRowCount() - 1, 1)), table)) {
				if (NecesseryMethods.findLastName(clientLastName, table)) {
					if (clientLastName.equals(model.getValueAt(model.getRowCount() - 1, 1))) {
						point += 5;
					} else {
						point += 10;
					}
				} else {
					point += 20;
				}

			}

			if (NecesseryMethods.findCountry((String) (model.getValueAt(model.getRowCount() - 1, 2)), table)) {
				if (NecesseryMethods.findCountry(clientCountry, table)) {
					if (clientCountry.equals(model.getValueAt(model.getRowCount() - 1, 2))) {
						point += 5;
					} else {
						point += 10;
					}
				} else {
					point += 20;
				}

			}

			if (NecesseryMethods.findAnimal((String) (model.getValueAt(model.getRowCount() - 1, 3)), table)) {
				if (NecesseryMethods.findAnimal(clientAnimal, table)) {
					if (clientAnimal.equals(model.getValueAt(model.getRowCount() - 1, 3))) {
						point += 5;
					} else {
						point += 10;
					}
				} else {
					point += 20;
				}

			}

			if (NecesseryMethods.findFruits((String) (model.getValueAt(model.getRowCount() - 1, 4)), table)) {
				if (NecesseryMethods.findFruits(clientFruit, table)) {
					if (clientFruit.equals(model.getValueAt(model.getRowCount() - 1, 4))) {
						point += 5;
					} else {
						point += 10;
					}
				} else {
					point += 20;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return point;
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
