package com.test.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.test.admin.DbConnection;
import com.test.admin.FrequentlyUse;

public class registration extends JFrame{
	JPanel panelregistration = new JPanel();
	
	JLabel lblnewuser = new JLabel("User Name");
	JLabel lblpassword = new JLabel("Password");
	JLabel lblrepassword = new JLabel("Confirm Password");
	JLabel lblid = new JLabel("User ID");
	JLabel lbladdress = new JLabel("Address");
	JLabel lblmobileno = new JLabel("Mobile No");
	
	JTextField txtnewusername = new JTextField();
	JTextField txtid = new JTextField();
	JTextField txtaddress = new JTextField();
	JTextField txtmobileno = new JTextField();
	
	JPasswordField password = new JPasswordField();
	JPasswordField confirmpassword = new JPasswordField();
	
	JButton btnconfirm = new JButton("Confirm");
	
	public  registration() {
		setVisible(true);
		setTitle("New User Registration");
		setSize(500,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//================================================================
		add(panelregistration);
		panelregistration.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		////////////////////////////////////////////////////////////////////////////////////////////
		c.gridx = 0;
		c.gridy = 0;
		panelregistration.add(lblid,c);
		lblid.setFont(new Font("Arial Black",Font.BOLD,22));
		
		c.gridx = 1;
		c.gridy = 0;
		panelregistration.add(txtid,c);
		txtid.setPreferredSize(new Dimension(200,20));
		txtid.setOpaque(false);
		txtid.setEditable(false);
		///////////////////////////////////////////////////////////////////////////////////txtid.sete
		//////////////////////
		c.gridx = 0;
		c.gridy = 1;
		panelregistration.add(lblnewuser,c);
		lblnewuser.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 1;
		panelregistration.add(txtnewusername,c);
		txtnewusername.setPreferredSize(new Dimension(200,40));
		
		c.gridx = 0;
		c.gridy = 2;
		panelregistration.add(lblmobileno,c);
		lblmobileno.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 2;
		panelregistration.add(txtmobileno,c);
		txtmobileno.setPreferredSize(new Dimension(200,40));
		
		c.gridx = 0;
		c.gridy = 3;
		panelregistration.add(lbladdress,c);
		lbladdress.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 3;
		panelregistration.add(txtaddress,c);
		txtaddress.setPreferredSize(new Dimension(200,40));
		
		c.gridx = 0;
		c.gridy = 4;
		panelregistration.add(lblpassword,c);
		lblpassword.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5,5,5,5);
		panelregistration.add(password,c);
		password.setPreferredSize(new Dimension(200,20));
		
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5,5,5,5);
		panelregistration.add(lblrepassword,c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5,5,5,5);
		panelregistration.add(confirmpassword,c);
		confirmpassword.setPreferredSize(new Dimension(200,20));
		
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(5,115,5,5);
		panelregistration.add(btnconfirm,c);
		btnconfirm.setBackground(Color.green);
		btnconfirm.setForeground(Color.black);
		
		btnAction();
		setAutoid();
	}

	private void setAutoid() {
		try {
			DbConnection.connect();
			String query = "select ifnull(max(cast(substring(userId,locate('-',userId)+1,"
					+ "length(userId)-locate('-',userId))as UNSIGNED)),0)+1 id from tbuserinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtid.setText("User-"+rs.getString("id"));
			}
			DbConnection.con.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}	
	}

	private void btnAction() {
		btnconfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(FrequentlyUse.confirmation("Want to Save?")) {
						if(!password.getText().isEmpty()) {
							if(Arrays.equals(password.getPassword(),confirmpassword.getPassword())) {
								if(FrequentlyUse.insertData(getinsertQuery())) {
									JOptionPane.showMessageDialog(null,"User Registration Done Successfully");
									dispose();
								}
								}
							else {
								JOptionPane.showMessageDialog(null,"Password is not Matched");
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"Fields Can't be Empty");							
							}
					}
			}
		});
	}
	
	private String getinsertQuery() {
		String query = "insert into tbuserinfo(userId,userName,mobileno,address,Password) values('"+txtid.getText().trim()+"',"
				+ "'"+txtnewusername.getText().trim()+"','"+txtmobileno.getText().trim()+"','"+txtaddress.getText().trim()+"','"
				+password.getText().trim()+"')";
		return query;
	}
}
