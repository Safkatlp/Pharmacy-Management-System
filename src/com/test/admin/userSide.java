package com.test.admin;

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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.test.menuitem.changeUser;
import com.test.menuitem.orderHistory;
import com.test.menuitem.orderStatus;
import com.test.menuitem.placeOrder;

public class userSide extends JPanel{
	JPanel panelnorth = new JPanel();
	JPanel panelnorthwest = new JPanel();
	JPanel panelnortheast = new JPanel();
	JPanel panelwest = new JPanel();
	JPanel paneleast = new JPanel();
	JPanel panelcenter = new JPanel();
	
	JButton btnplaceorder = new JButton(new ImageIcon("images/place order.png"));
	JButton btnorderstatus = new JButton(new ImageIcon("images/order history.png"));
	JButton btnorderhistory = new JButton(new ImageIcon("images/order history.png"));
	JButton btnchangeuser = new JButton(new ImageIcon("images/changeadmin.png"));
	
	JLabel logo = new JLabel(new ImageIcon("images/pms logo - Copy.jpg"));
	JLabel lblusertag = new JLabel("User Home Page");
	JLabel lblplaceorder = new JLabel("Place Order");
	JLabel lblorderstatus = new JLabel("Status / History");
	JLabel lblorderhistory = new JLabel("Order History");
	JLabel lblchangeuser = new JLabel("Change User");
	
	orderStatus orderstatus = new orderStatus();
	placeOrder placeorder = new placeOrder();
	orderHistory orderhistory = new orderHistory();
	changeUser changeuser = new changeUser();
	
	String o1;
	
	public userSide() {
		setLayout(new BorderLayout());
		add(panelnorth,BorderLayout.NORTH);
		panelnorthwork();
		add(panelwest,BorderLayout.WEST);
		panelwestwork();
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		btnAction();
	}
	
	public void recieve(String o) {
		o1 = o;
		System.out.print(o1+"to\n");
	}
	
	private void btnAction() {
		btnplaceorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				placeorder.setVisible(true);
				placeorder.gettingUserid();
				placeorder.tableDataLoad();
				placeorder.AutoId();
				
				placeorder.total();
				//placeorder.setname();
				//addstuff.AutoId();
			}
		});
		
		btnorderstatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				orderstatus.setVisible(true);
				orderstatus.tableHistoryDataLoad();
				orderstatus.tableDetailsDataLoad(null);
				//addsupplier.AutoId();
			}
		});
		
		btnorderhistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				orderhistory.setVisible(true);
				//customerinfo.tableDataLoad();
				//customerinfo.setID();
			}
		});
		
		btnchangeuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				changeuser.setVisible(true);
				changeuser.autofill();
			}
		});
	}
	
	private void panelcenterwork() {
		panelcenter.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		panelcenter.setLayout(flow);
		panelcenter.add(changeuser);
		panelcenter.add(orderhistory);
		panelcenter.add(orderstatus);
		panelcenter.add(placeorder);
		panelcenterfalse();
	}
	
	private void panelcenterfalse() {
		changeuser.setVisible(false);
		orderhistory.setVisible(false);
		orderstatus.setVisible(false);
		placeorder.setVisible(false);
	}
	
	private void panelnorthwork() {
		panelnorth.setBackground(Color.red);
		panelnorth.setLayout(new BorderLayout());
		panelnorth.add(panelnorthwest,BorderLayout.WEST);
		panelnorthwestwork();
		panelnorth.add(panelnortheast,BorderLayout.EAST);
		panelnortheastwork();
		panelnorth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelnorth.setPreferredSize(new Dimension(0,110));
	}
	
	private void panelnortheastwork() {
		panelnortheast.setBorder(BorderFactory.createEmptyBorder());
		panelnortheast.setBackground(Color.red);
		panelnortheast.setPreferredSize(new Dimension(900,0));
		BorderLayout b = new BorderLayout();
		panelnortheast.setLayout(b);
		panelnortheast.add(lblusertag,BorderLayout.EAST);
		lblusertag.setFont(new Font("serif", Font.BOLD, 25));
	}
	
	private void panelnorthwestwork() {
		panelnorthwest.setBorder(BorderFactory.createEmptyBorder());
		panelnorthwest.setPreferredSize(new Dimension(320,0));
		panelnorthwest.setBackground(Color.red);
		panelnorthwest.add(logo);
	}
	
	private void panelwestwork() {
		panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
		panelwest.setPreferredSize(new Dimension(150,0));
		panelwest.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(btnplaceorder,c);
		btnplaceorder.setBackground(Color.RED);
		btnplaceorder.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(lblplaceorder,c);
		lblplaceorder.setBackground(Color.RED);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(btnorderstatus,c);
		btnorderstatus.setBackground(Color.RED);
		btnorderstatus.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(lblorderstatus,c);
		lblorderstatus.setBackground(Color.RED);
//		
//		c.gridx = 0;
//		c.gridy = 4;
//		c.fill = GridBagConstraints.BOTH;
//		c.insets= new Insets(5,5,5,5);
//		panelwest.add(btnorderhistory,c);
//		btnorderhistory.setBackground(Color.RED);
//		btnorderhistory.setForeground(Color.WHITE);
//		
//		c.gridx = 0;
//		c.gridy = 5;
//		c.fill = GridBagConstraints.BOTH;
//		c.insets= new Insets(5,5,5,5);
//		panelwest.add(lblorderhistory,c);
//		lblorderhistory.setBackground(Color.RED);
		
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(btnchangeuser,c);
		btnchangeuser.setBackground(Color.RED);
		btnchangeuser.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelwest.add(lblchangeuser,c);
		lblchangeuser.setBackground(Color.RED);
	}
}
