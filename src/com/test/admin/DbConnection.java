package com.test.admin;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class DbConnection {

	public static Connection con=null;
	public static Statement sta=null;
	public static void connect() {
		try {
			String server="",port="",username="",password="",dbFile="";
			 File fileLocation=new File("src/dbPharmacyManagementSystem.txt"); 
			 Scanner scan=new Scanner(fileLocation); 
			 
			 int a=1;
			 while(scan.hasNextLine()) { 
				 String line=scan.nextLine(); 
				 StringTokenizer token=new StringTokenizer(line); 
				 token.nextToken();
				 
				 String secondToken=token.nextToken();
				 if(a==1) {
					 server=secondToken;
				 }
				 else if(a==2) {
					 port=secondToken;
				 }
				 else if(a==3) {
					 username=secondToken;
				 }
				 else if(a==4) {
					 dbFile=secondToken;
					 break;
				 }
				 a++;
			 }
			 
			String url="jdbc:mysql://"+server+":"+port+"/"+dbFile;	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(url,username,password);
			sta=con.createStatement();
			//System.out.println("Connect");
		}
		catch(Exception exp) {
			JOptionPane.showMessageDialog(null, exp);
		}
	}
	
}
