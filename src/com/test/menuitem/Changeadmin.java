package com.test.menuitem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.test.admin.DbConnection;

public class Changeadmin extends JPanel {
	JButton btnconfirm = new JButton("Confirm");
	
	JLabel lblcurrentpassword = new JLabel("Current Password");
	JLabel lblnewusername = new JLabel("New UserName");
	JLabel lblnewpassword = new JLabel("New Password");
	
	JTextField txtnewusername = new JTextField(20);
	JPasswordField newpassword = new JPasswordField(20);
	JPasswordField currentpassword = new JPasswordField(20);
	
	JPanel panelcenter = new JPanel();
	JPanel panelsouth = new JPanel();
	
	public Changeadmin() {
		//setPreferredSize(new Dimension(800,500));
		//setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		add(panelsouth,BorderLayout.SOUTH);
		panelsouth.setPreferredSize(new Dimension(800,500));
		panelsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		add(panelcenter,BorderLayout.CENTER);
		panelcenter.setPreferredSize(new Dimension(0,180));
		panelsouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblcurrentpassword,c);
		lblcurrentpassword.setPreferredSize(new Dimension(160,40));
		lblcurrentpassword.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(currentpassword,c);
		currentpassword.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblnewusername,c);
		lblnewusername.setPreferredSize(new Dimension(140,40));
		lblnewusername.setFont(new Font("sherif",Font.PLAIN,20));
		lblnewusername.setAlignmentX(RIGHT_ALIGNMENT);
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(txtnewusername,c);
		txtnewusername.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblnewpassword,c);
		lblnewpassword.setPreferredSize(new Dimension(140,40));
		lblnewpassword.setFont(new Font("sherif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(newpassword,c);
		newpassword.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,65,5,5);
		panelsouth.add(btnconfirm,c);
		//btnconfirm.setPreferredSize(new Dimension(40,40));
		
		btnAction();
		
	}

	private void btnAction() {
		btnconfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if(txtnewusername.getText() != null && newpassword.getText().toString() != null) {
				
				DbConnection.connect();
				String checkquery = "select * from tbadmin where password = '"+currentpassword.getText().toString()+"'";
				try {
					ResultSet rs = DbConnection.sta.executeQuery(checkquery);
				
					if(rs.next()) {
						try {
							//Class.forName("com.mysql.jdbc.Driver");
							//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpharmacymanagementsystem", "root", "");
							//Statement stmt = con.createStatement();
					
							String query = "Update tbadmin set UserName='"+txtnewusername.getText()+"', Password='"+newpassword.getText().toString()+"' where userid='1'";
							int count = DbConnection.sta.executeUpdate(query);
					
							JOptionPane.showMessageDialog(null,"Changed Succesfully");
							DbConnection.con.close();
							
							txtnewusername.setText("");
							currentpassword.setText("");
							newpassword.setText("");
				
							//stmt.close();
						} catch (Exception e) {
							System.out.print(e);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Current Password is wrong");
				
					}
				} catch(Exception e) {
				System.out.print(e);
			 }
		   }
		});
		
	}
}
