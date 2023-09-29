package ir.ec.guilan.ac.MahshidAl.sixthProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class NecesseryMethods {

	static void sendToFile(String sendToFile , String fileName) {
		BufferedWriter output = null;

		try {
			File file = new File(fileName);
			output = new BufferedWriter(new FileWriter(file, true));
			output.write(sendToFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
				}
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean no = false;
	static ArrayList<String> name = new ArrayList<>();
	static ArrayList<String> passWord = new ArrayList<>();
	static ArrayList<String> proPicture = new ArrayList<>();

	static int readFromFile(String userName, String Password) throws IOException {

		try {
			SignUpPage.itIs = false;
			name.clear();
			passWord.clear();
			proPicture.clear();
			BufferedReader in = new BufferedReader(new FileReader("information.txt"));

			String c;
			while ((c = in.readLine()) != null) {
				name.add(c.split("   ")[0]);
				passWord.add(c.split("   ")[1]);
				proPicture.add(c.split("   ")[2]);
			}

			if (name.contains(userName))
				SignUpPage.itIs = true;
			if (name.contains(userName) && passWord.get(name.indexOf(userName)).equals(Password))
				return name.indexOf(userName);
			else if (name.contains(userName) && !passWord.get(name.indexOf(userName)).equals(Password))
				return -1;// password is incorrect
			else
				return -2;// username is not availble

		} catch (FileNotFoundException e) {
			return -3;// file doesn't exist
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	static Random random = new Random();

	public static char randomLetter() {

		char x;
		do {
			x = (char) ('A' + random.nextInt(26));
		} while (GamePage.randomLetter.contains(x + ""));
		GamePage.randomLetter.add(x + "");

		return x;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	static boolean halfPoint = false;
	static boolean maxPoint = false;
	public static boolean findAnimal(String animal , JTable table ) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("animals.txt"));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		maxPoint = false;
		halfPoint = false;
		String s;
		while((s = in.readLine()) != null) {
			
			
			if(s.equals(animal) && (s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))  || s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))+32)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean findCountry(String country , JTable table ) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("countries.txt"));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		maxPoint = false;
		halfPoint = false;
		String s;
		while((s = in.readLine()) != null)
			
			if(s.equals(country) && (s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))  || s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))+32)) {
				return true;
			}
		return false;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean findLastName(String lastName ,  JTable table ) throws IOException {
		maxPoint = false;
		halfPoint = false;
		BufferedReader in = new BufferedReader(new FileReader("lastNames.txt"));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		String s;
		while((s = in.readLine()) != null)
			
			if(s.equals(lastName) && (s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6)) || s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))+32)) {
				return true;
			}
		return false;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean findFruits(String fruit ,  JTable table ) throws IOException {

		
		
		BufferedReader in = new BufferedReader(new FileReader("fruits.txt"));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		String s;
		while((s = in.readLine()) != null)
			
			if(s.equals(fruit) && (s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6)) || s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))+32)) {
				return true;
			}
		return false;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean findFirstName(String firstName , JTable table ) throws IOException {

	
		BufferedReader in = new BufferedReader(new FileReader("firstNames.txt"));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	
		String s;
		while((s = in.readLine()) != null)
			
			if(s.equals(firstName) && 
					(s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6)) || s.charAt(0) == (char)(model.getValueAt(model.getRowCount()- 1, 6))+32)){
				
				return true;
			}
		
		return false;
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void clearFile(String fileName) throws IOException {

		
		FileWriter fwOb = new FileWriter(fileName , false);
		PrintWriter pwOb = new PrintWriter(fwOb , false);
		
		pwOb.flush();
		pwOb.close();
		fwOb.close();
	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int whoWin(int yourPoint , int yourNeighbourPoint) {
		
		if(yourPoint > yourNeighbourPoint)
			return 1;
		else if(yourPoint < yourNeighbourPoint)
			return -1;
		else
			return 0;
		
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static int finalPoint(JTable table) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int finalPoint = 0;
		
	for(int i = 0 ; i < model.getRowCount() ; i ++)
		try {
		finalPoint += (int)(model.getValueAt(i , 5));
		}catch (Exception e) {
		}
		
		return finalPoint;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int numberOfWLE(String fileName , String name) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(fileName));

		String c;
		int num=0;
		while ((c = in.readLine()) != null) {
			if(c.equals(name))
				num ++;
		}
		
		return num;
	}
	
	///////////////////////////////////////////////
	
	

}
