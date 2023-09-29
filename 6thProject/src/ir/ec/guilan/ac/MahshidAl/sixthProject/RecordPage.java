package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecordPage extends JFrame {

	private JPanel contentPane;
	static JTextArea txtrMahshid;
	static RecordPage frame;
	static String nameOfPlyer ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RecordPage();
					frame.setLocation(430 , 100);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public RecordPage() throws IOException {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 362);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\9502b3491f613feba15592ba6bf92be4.gif"));
		label.setBounds(10, 11, 300, 300);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3));
		scrollPane.setBounds(331, 88, 175, 171);
		contentPane.add(scrollPane);
		
		txtrMahshid = new JTextArea();
		txtrMahshid.setFont(new Font("Agency FB", Font.BOLD, 20));
		txtrMahshid.setBackground(new Color(0, 153, 0));
		scrollPane.setViewportView(txtrMahshid);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(331, 11, 175, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMashi = new JLabel("");
		lblMashi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMashi.setFont(new Font("Agency FB", Font.PLAIN, 35));
		lblMashi.setBounds(2, 2, 171, 62);
		panel.add(lblMashi);
		String textAreaText = "";
		lblMashi.setText(nameOfPlyer);
		
		try {
		textAreaText = "  W i n  :  " + NecesseryMethods.numberOfWLE("winner.txt", nameOfPlyer) + "\r\n" + "\r\n";
		textAreaText += "  L o s s  :  " + NecesseryMethods.numberOfWLE("losser.txt", nameOfPlyer) + "\r\n" + "\r\n";
		textAreaText += "  E q u a l s  :  " + NecesseryMethods.numberOfWLE("equals.txt", nameOfPlyer) ;
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		txtrMahshid.setText(textAreaText);
		
		JButton btnOK = new JButton("O K");
		btnOK.setFocusable(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
				GamePage.frame.setVisible(true);
				GamePage2.frame.setVisible(true);
			}
		});
		btnOK.setBackground(new Color(0, 153, 0));
		btnOK.setFont(new Font("Agency FB", Font.BOLD, 35));
		btnOK.setBounds(331, 266, 175, 45);
		contentPane.add(btnOK);
		
	}
}
