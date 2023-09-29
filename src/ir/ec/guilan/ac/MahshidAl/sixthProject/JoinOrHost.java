package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Label;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JoinOrHost extends JFrame {

	private JPanel contentPane;
	static JoinOrHost frame;
	static JLabel errorLabel;// the lbel of error massege
	static boolean host = false;// if player host
	static boolean join = false;// if player join
	boolean isPlaying = false;
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
				
					frame = new JoinOrHost();
					frame.setLocation(430, 220);
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
	public JoinOrHost() {
		
		
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 264);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//action listener of Join Button
		JButton btnJOI = new JButton("J O I N");
		btnJOI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				join = true;
				host = false;
				
				WaitingPage.frame.dispose();
				ListOfHosts.main(null);
				frame.setVisible(false);
				

			}
		});
		btnJOI.setFocusable(false);
		btnJOI.setBackground(new Color(51, 153, 0));
		btnJOI.setFont(new Font("Agency FB", Font.BOLD, 50));
		btnJOI.setBounds(32, 66, 170, 133);
		contentPane.add(btnJOI);
		
		//action listener of host Button
		JButton btnHOS = new JButton("H O S T");
		btnHOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				host = true;
				join = false;
				GamePage g = new GamePage();
				Thread t = new Thread(g);
				t.start();
				frame.setVisible(false);

			}
		});
		btnHOS.setFocusable(false);
		btnHOS.setBackground(new Color(0, 153, 0));
		btnHOS.setFont(new Font("Agency FB", Font.BOLD, 50));
		btnHOS.setBounds(227, 66, 170, 133);
		contentPane.add(btnHOS);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(10, 11, 409, 45);
		contentPane.add(errorLabel);
		
		FirstPage obj = new FirstPage();
	}
}
