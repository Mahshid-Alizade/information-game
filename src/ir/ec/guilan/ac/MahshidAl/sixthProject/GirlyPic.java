package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Window.Type;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GirlyPic extends JFrame {

	static GirlyPic frame;
	private Icon proPic;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GirlyPic();
					frame.setLocation(450 , 100);
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
	public GirlyPic() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 349);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button.getIcon()); 
				frame.setVisible(false);
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_235207.jpg"));
		button.setBounds(10, 11, 130, 90);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_1.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_06062018_095625.jpg"));
		button_1.setBounds(150, 11, 130, 90);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_2.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_06062018_095710.jpg"));
		button_2.setBounds(290, 11, 130, 90);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_3.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_3.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_235408.jpg"));
		button_3.setBounds(10, 112, 130, 90);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_4.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_4.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_235419.jpg"));
		button_4.setBounds(150, 112, 130, 90);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_5.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_5.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_06062018_095723.jpg"));
		button_5.setBounds(290, 112, 130, 90);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_6.getIcon()); 
//				FirstPage.main(null);
				frame.setVisible(false);
			}
		});
		button_6.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_06062018_095805.jpg"));
		button_6.setBounds(10, 213, 130, 90);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_7.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_7.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_31052018_235642.jpg"));
		button_7.setBounds(150, 213, 130, 90);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpPage.proPic.setIcon(button_8.getIcon()); 
				frame.setVisible(false);
			}
		});
		button_8.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\IMG_06062018_095639.jpg"));
		button_8.setBounds(290, 213, 130, 90);
		contentPane.add(button_8);
	}

}
