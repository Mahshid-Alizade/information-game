package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class WaitingPage extends JFrame {

	private JPanel contentPane;
	static WaitingPage frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new WaitingPage();
					frame.setLocation(805 , 0);
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
	public WaitingPage() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 354);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWaitingForPlayer = new JLabel("W a i t i n g   F o r   P l a y e r   . . . ");
		lblWaitingForPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitingForPlayer.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblWaitingForPlayer.setBounds(54, 0, 434, 57);
		contentPane.add(lblWaitingForPlayer);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\tenor (2).gif"));
		label.setBounds(23, 58, 501, 246);
		contentPane.add(label);
	}

}
