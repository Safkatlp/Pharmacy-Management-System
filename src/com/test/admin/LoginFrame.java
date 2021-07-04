package com.test.admin;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.test.admin.registration;
import com.test.menuitem.placeOrder;
import com.test.admin.userSide;

public class LoginFrame extends JFrame{
	placeOrder po = new placeOrder();
	userSide us = new userSide();
	
	String name = new String();
	String userid;
	
	JPanel panelcenteruser = new JPanel();
	JPanel panelcentersuper = new JPanel();

	//JPanel panelmainuser = new JPanel();
	JPanel panelregistration = new JPanel();
	JPanel panelmain = new JPanel();
	JPanel panelnorth = new JPanel();
	JPanel panelcenter = new JPanel();
	JLabel lblpharmacy = new JLabel("Pharmacy Management System");
	JLabel lbladminname = new JLabel("UserName:");
	JLabel lbladminpassword = new JLabel("Password:");
	JLabel lblusername = new JLabel("UserName:");
	JLabel lbluserpassword = new JLabel("Password:");
	JLabel lblnewuser = new JLabel("User Name");
	JLabel lblpassword = new JLabel("Password");
	JLabel lblrepassword = new JLabel("Confirm Password");
	
	JPasswordField password = new JPasswordField();
	JPasswordField confirmpassword = new JPasswordField();
	JTextField txtnewusername = new JTextField();
	
	JLabel lbluser = new JLabel("USER");
	JLabel lbladmin = new JLabel("ADMIN");
	JTextField txtusername = new JTextField();
	JTextField txtadminname = new JTextField();
	JPasswordField adminpassword = new JPasswordField();
	JPasswordField userpassword = new JPasswordField();
	JButton btnokuser = new JButton("OK");
	JButton btnokadmin = new JButton("OK");
	JButton btnuser = new JButton("User Login");
	JButton btnadmin = new JButton("Admin Login");
	JButton btnnewuser = new JButton("New User");
	JButton btnconfirm = new JButton("Confirm");
	
	Dimension displaysize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public LoginFrame() {
		frame();
		cmp();
		btnAction();
	}

	private void frame() {
		setVisible(true);
		setTitle("Login");
		setSize(600,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	private void cmp() {
		add(panelmain);
		//newlyadded from here
		//panelsuper.setLayout(new BorderLayout());
		
		//panelsuper.add(panelmainuser,BorderLayout.CENTER);
		//panelsuper.add(panelmain,BorderLayout.CENTER);
		//till here
		//panelmain.setPreferredSize(new Dimension(600,500));
		
		panelmain.setLayout(new BorderLayout());
		panelmain.add(panelnorth,BorderLayout.NORTH);
		panelnorthwork();
		//add(BorderLayout.CENTER);
		panelmain.add(panelcentersuper,BorderLayout.CENTER);
		panelcentersuperwork();
		///////////////////////////////////////////////////////////////////////////////////////
		//	panelmain.add(panelcenteruser,BorderLayout.CENTER);								///
		//	panelcenteruserwork();															///
		//	panelmain.add(panelcenter,BorderLayout.CENTER);									///
		//	panelcenterwork();																///
		///////////////////////////////////////////////////////////////////////////////////////
	
	}

	private void panelnorthwork() {
		panelnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelnorth.setOpaque(true);
		panelnorth.setPreferredSize(new Dimension(0,90));
		panelnorth.add(lblpharmacy);
		lblpharmacy.setFont(new Font("serif",Font.BOLD,35));
		lblpharmacy.setForeground(Color.white);
		
		panelnorth.setBackground(Color.red);
	
		
	}
	private void panelcentersuperwork() {
		//panelcentersuper.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow = new FlowLayout();
		panelcentersuper.setLayout(flow);
		panelcentersuper.add(panelcenter);
		panelcenterwork();
		panelcentersuper.add(panelcenteruser);
		panelcenteruserwork();
	}


	private void panelcenterwork() {
		panelcenteruser.setVisible(false);
		//panelcenter.setBorder(BorderFactory.createRaisedBevelBorder());
		panelcenter.setPreferredSize(new Dimension(600,415));
		panelcenter.setOpaque(false);
		panelcenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		panelcenter.add(lbladmin,c);
		lbladmin.setFont(new Font("Arial Black",Font.BOLD,20));
		
		c.gridx = 0;
		c.gridy = 1;
		panelcenter.add(lbladminname,c);
		lbladminname.setFont(new Font("serif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 1;
		panelcenter.add(txtadminname,c);
		txtadminname.setPreferredSize(new Dimension(200,40));
		
		
		c.gridx = 0;
		c.gridy = 2;
		panelcenter.add(lbladminpassword,c);
		lbladminpassword.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		panelcenter.add(adminpassword,c);
		adminpassword.setPreferredSize(new Dimension(200,20));
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		panelcenter.add(btnuser,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5,115,5,5);
		panelcenter.add(btnokadmin,c);
		
	}
	///user panel
	private void panelcenteruserwork() {
		//panelcenteruser.setBorder(BorderFactory.createRaisedBevelBorder());
		panelcenteruser.setPreferredSize(new Dimension(600,415));
		panelcenteruser.setOpaque(true);
		panelcenteruser.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		panelcenteruser.add(lbluser,c);
		lbluser.setFont(new Font("Arial Black",Font.BOLD,20));
		
		c.gridx = 0;
		c.gridy = 1;
		panelcenteruser.add(lblusername,c);
		lblusername.setFont(new Font("serif",Font.PLAIN,20));
		
		
		c.gridx = 1;
		c.gridy = 1;
		panelcenteruser.add(txtusername,c);
		txtusername.setPreferredSize(new Dimension(200,40));
		
		
		c.gridx = 0;
		c.gridy = 2;
		panelcenteruser.add(lbluserpassword,c);
		lbluserpassword.setFont(new Font("serif",Font.PLAIN,20));
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		panelcenteruser.add(userpassword,c);
		userpassword.setPreferredSize(new Dimension(200,20));
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		panelcenteruser.add(btnadmin,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5,115,5,5);
		panelcenteruser.add(btnokuser,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5,5,5,5);
		panelcenteruser.add(btnnewuser,c);
		btnnewuser.setBackground(Color.GRAY);
		btnnewuser.setForeground(Color.white);
	}
	private void btnAction() {
		btnokadmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*int choice=JOptionPane.showConfirmDialog(null,"Do you want to proceed?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					yesaction();
				}*/
				try {
					
					DbConnection.connect();
					String query = "Select * from tbadmin where UserName='"+txtadminname.getText()+"' and Password='"+adminpassword.getText().toString()+"'";
					ResultSet rs = DbConnection.sta.executeQuery(query);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null,"Login Successfull");
						yesactionadmin();}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
						DbConnection.con.close();
					//stmt.close();
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		btnokuser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*int choice=JOptionPane.showConfirmDialog(null,"Do you want to proceed?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					yesaction();
				}*/
				try {
					DbConnection.connect();
					String query = "Select * from tbuserinfo where UserName='"+txtusername.getText()+"' and Password='"+userpassword.getText().toString()+"'";
					ResultSet rs = DbConnection.sta.executeQuery(query);
					if(rs.next()) {
			//////////////////////////////////////////////////////////////////////////////////////////////
			////////////							getting userID									//////
						String getuserid = "select userid from tbuserinfo where "					//////
								+ "userName='"+txtusername.getText().trim()+"'";					//////
						ResultSet rs3 = DbConnection.sta.executeQuery(getuserid);					//////
						while(rs3.next()) {															//////
						 userid = rs3.getString("userid");											//////		
						 																			//////
						}																			//////
			////////////																			//////
			/////////////////////--------------------------------------------------------------///////////
			////////////							clearing table in database						//////
						String loggedinuser = "delete from tbloggedininfo";							//////
						DbConnection.sta.executeUpdate(loggedinuser);								//////
			/////////////																			//////
			//////////////////////-------------------------------------------------------------///////////
			////////////							inserting data into database					//////
						String loggedinuser2 = "insert into tbloggedininfo"							//////
								+ " values('"+txtusername.getText().trim()+"','"+userid+"')";		//////
						DbConnection.sta.executeUpdate(loggedinuser2);								//////
			////////////																			//////
			//////////////////////////////////////////////////////////////////////////////////////////////
						 
						JOptionPane.showMessageDialog(null,"Login Successfull");
						yesactionuser();
						//sendusername();
						}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
						DbConnection.con.close();
						
					//stmt.close();
				} catch (Exception e) {
					System.out.print(e);
				}
			}
		});
		btnuser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelcenter.setVisible(false);
				panelcenteruser.setVisible(true);

			}
		});
		btnadmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelcenteruser.setVisible(false);
				panelcenter.setVisible(true);

			}
		});
		btnnewuser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registration r = new registration();
			}
		});
		
		
	}
//	public void sendusername() {
//		name =txtusername.getText().trim();
//		po.recievename(name);
//		//po.recievename(name);
//		
//	}
	
	private void yesactionuser() {
		panelmain.setVisible(false);
		userSide us = new userSide();
		add(us);
		setVisible(true);
		setSize(displaysize);
		setLocationRelativeTo(null);
		setTitle("Pharmacy Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private void yesactionadmin() {
		panelmain.setVisible(false);
		FrontFrame ff = new FrontFrame();
		add(ff);
		setVisible(true);
		setSize(displaysize);
		setLocationRelativeTo(null);
		setTitle("Pharmacy Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
}
