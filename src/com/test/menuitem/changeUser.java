package com.test.menuitem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.test.admin.DbConnection;

public class changeUser extends JPanel{
	String id;

	JPanel panelsouth = new JPanel();
	JPanel panelcenter = new JPanel();
	
	JLabel lblcurrentpassword = new JLabel("Current Password");
	JLabel lblusername = new JLabel("User Name");
	JLabel lblnewpassword = new JLabel("New Password");
	JLabel lblmobileno = new JLabel("Mobile NO");
	JLabel lbladdress = new JLabel("Address");
	
	JTextField txtusername = new JTextField(20);
	JTextField txtmobileno = new JTextField(20);
	JTextField txtaddress = new JTextField(20);
	
	JPasswordField currentpassword = new JPasswordField(20);
	JPasswordField newpassword = new JPasswordField(20);
	
	JButton btnconfirm = new JButton("Confirm");
	
	public changeUser() {
		setPreferredSize(new Dimension(1750,930));
		//setBackground(Color.yellow);
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		flow.setVgap(100);
		add(panelsouth);
		panelsouth.setPreferredSize(new Dimension(880,700));
		panelsouth.setBorder(BorderFactory.createRaisedBevelBorder());
//		add(panelcenter,BorderLayout.CENTER);
//		panelcenter.setPreferredSize(new Dimension(0,10));
	//	panelsouth.setBackground(Color.yellow);
		
		panelsouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblusername,c);
		lblusername.setPreferredSize(new Dimension(160,40));
		lblusername.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(txtusername,c);
		txtusername.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblmobileno,c);
		lblmobileno.setPreferredSize(new Dimension(160,40));
		lblmobileno.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(txtmobileno,c);
		txtmobileno.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lbladdress,c);
		lbladdress.setPreferredSize(new Dimension(160,40));
		lbladdress.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(txtaddress,c);
		txtaddress.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblcurrentpassword,c);
		lblcurrentpassword.setPreferredSize(new Dimension(160,40));
		lblcurrentpassword.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(currentpassword,c);
		currentpassword.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(lblnewpassword,c);
		lblnewpassword.setPreferredSize(new Dimension(160,40));
		lblnewpassword.setFont(new Font("sherif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panelsouth.add(newpassword,c);
		newpassword.setPreferredSize(new Dimension(40,40));
		
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,115,5,5);
		panelsouth.add(btnconfirm,c);
		btnconfirm.setBackground(Color.green);
		
		btnAction();
	}
	
	public void autofill() {
		try {
			DbConnection.connect();
			String user = "select userid as id from tbloggedininfo";
			ResultSet rs2 = DbConnection.sta.executeQuery(user);
			while(rs2.next()) {
			 id = rs2.getString("id");
			//System.out.print(id);
			}
			
			
			String query = "select username,mobileno,address from tbuserinfo where userid='"+id+"'";
			ResultSet data = DbConnection.sta.executeQuery(query);
			while(data.next()) {
				txtusername.setText(""+data.getString("username"));
				txtmobileno.setText(""+data.getString("mobileno"));
				txtaddress.setText(""+data.getString("address"));
			}
			DbConnection.con.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void change() {
		//if(txtnewusername.getText() != null && newpassword.getText().toString() != null) {
		
		
		try {
			DbConnection.connect();
			String checkquery = "select * from tbuserinfo where password = '"+currentpassword.getText().toString()+"'";
			ResultSet rs = DbConnection.sta.executeQuery(checkquery);
			
		
			if(rs.next()) {
				try {
					String test = rs.getString("password");
					//System.out.print(test);
					//Class.forName("com.mysql.jdbc.Driver");
					//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpharmacymanagementsystem", "root", "");
					//Statement stmt = con.createStatement();
					
					String user = "select userid as id from tbloggedininfo";
					ResultSet rs2 = DbConnection.sta.executeQuery(user);
					while(rs2.next()) {
					 id = rs2.getString("id");
					//System.out.print(id);
					}
					String query = "update tbuserinfo set username='"+txtusername.getText().trim()+"',"
							+ "mobileno='"+txtmobileno.getText().trim()+"',"
							+ "address='"+txtaddress.getText().trim()+"',"
							+ "password='"+newpassword.getText().toString()+"' "
							+ "where userid='"+id+"'";
					int count = DbConnection.sta.executeUpdate(query);
			
					JOptionPane.showMessageDialog(null,"Changed Succesfully");
					
					DbConnection.con.close();
					textClear();
					autofill();
		
					//stmt.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Current Password is wrong");
		
			}
		} catch(Exception e) {
		System.out.print(e);
	 }
	}
	private boolean isEmptyCheck() {
		if(!txtusername.getText().isEmpty()) {
			if(!txtmobileno.getText().isEmpty()) {
				if(!txtaddress.getText().isEmpty()) {
					if(!currentpassword.getText().isEmpty()) {
						if(!newpassword.getText().isEmpty()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	private void textClear() {
		txtusername.setText("");
		txtmobileno.setText("");
		txtaddress.setText("");
		currentpassword.setText("");
		newpassword.setText("");
	}
	private void btnAction() {
		btnconfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isEmptyCheck()) {
					change();
					
				}
				else
					JOptionPane.showMessageDialog(null, "Fields must be Filled Up");
		   }
		});
		
	}

}
