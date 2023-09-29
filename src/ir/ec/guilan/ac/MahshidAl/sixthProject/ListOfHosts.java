package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.sound.midi.Patch;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListOfHosts extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static ListOfHosts frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ListOfHosts();
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
	 * 
	 * @throws IOException
	 */
	public ListOfHosts() throws IOException {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\SHAREit\\SM-G955F\\file\\giphy.gif"));
		label.setBounds(10, 232, 305, 255);
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 260, 209);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"            Name", "              Port", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(109);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(115);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(34);
		scrollPane.setViewportView(table);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				for(int i = 0 ; i < model.getRowCount() ; i++) {
					if((boolean)model.getValueAt(i, 2)) {
						GamePage2.port =(int) model.getValueAt(i, 1);
						GamePage.hostName = (String)model.getValueAt(i, 0) ;
						GamePage.hostProPic = FirstPage.proArray.get(i);
					}
				}
				
				
				
				GamePage2 g2 = new GamePage2();
				Thread t = new Thread(g2);
				t.start();
				
				frame.setVisible(false);
				
				try {
					NecesseryMethods.clearFile("Host.txt");
				} catch (IOException e) {
				}
			}
		});
		button.setBackground(new Color(102, 153, 0));
		button.setBounds(280, 11, 35, 209);
		contentPane.add(button);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		BufferedReader in = new BufferedReader(new FileReader("Host.txt"));

		String c;
		while ((c = in.readLine()) != null) {
				String name = c.split("   ")[0];
				int port = Integer.parseInt(c.split("   ")[1]);
				model.addRow(new Object[] { name , port , false});
		}
		
	}
}
