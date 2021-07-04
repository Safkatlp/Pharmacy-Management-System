package com.test.admin;

import com.test.menuitem.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrontFrame extends JPanel{
	Dimension displaysize = Toolkit.getDefaultToolkit().getScreenSize();
	
	JPanel panelnorth = new JPanel();
	JPanel panelnorthwest = new JPanel();
	JPanel panelnortheast = new JPanel();
	JPanel panelwest = new JPanel();
	JPanel paneleast = new JPanel();
	JPanel panelcenter = new JPanel();
	JPanel panelmedicine = new JPanel();
	JPanel panelcustomer = new JPanel();
	JPanel panelsupplier = new JPanel();
	JPanel panelstuff = new JPanel();
	JPanel paneltools = new JPanel();
	JPanel panelsales = new JPanel();
	//==================================================================
	JButton btnmedicine = new JButton("Medicine");
	JButton btncustomer = new JButton("Customer");
	JButton btnsupplier = new JButton("Supplier");
	JButton btnstuff = new JButton("Stuff");
	JButton btntools = new JButton("Tools");
	JButton btnsales = new JButton("Sales");
	//===============================================================
	JButton btnaddmedicine = new JButton(new ImageIcon("images/addmedicine.png"));
	JButton btnstock = new JButton(new ImageIcon("images/stock.png"));
	JButton btncustomerinfo = new JButton(new ImageIcon("images/customerinfo.png"));
	JButton btnsupplierinfo = new JButton(new ImageIcon("images/supplierinfo.png"));
	JButton btnsetup = new JButton("Setup");
	JButton btnchangeadmin = new JButton(new ImageIcon("images/changeadmin.png"));
	JButton btnaddstuff = new JButton(new ImageIcon("images/addstuff.png"));
	JButton btnstuffinfo = new JButton(new ImageIcon("images/stuffinfo.png"));
	JButton btnaddsupplier = new JButton(new ImageIcon("images/addstuff.png"));
	JButton btnaddtocart = new JButton(new ImageIcon("images/add to cart.png"));
	JButton btnsellreport = new JButton(new ImageIcon("images/sell report.png"));
	JButton btnorder = new JButton(new ImageIcon("images/add to cart.png"));
	JButton btnonsellreport = new JButton(new ImageIcon("images/sell report.png"));
	
	//=============================================================
	JLabel lbladdmedicine = new JLabel("Add Medicine");
	JLabel lblstock = new JLabel("Stock");
	JLabel lblcustomerinfo = new JLabel("Customer Info");
	JLabel lblsupplierinfo = new JLabel("Supplier Info");
	JLabel lbladdsupplier = new JLabel("Add Supplier");
	JLabel lbladdstuff = new JLabel("Add Stuff");
	JLabel lblstuffinfo = new JLabel("Stuff Info");
	JLabel lblchangeadmin = new JLabel("Change Admin");
	JLabel lbladdtocart = new JLabel("Add to Cart");
	JLabel lblsellreport = new JLabel("Sell Report");
	JLabel lblorder = new JLabel("Online Order");
	JLabel lblonsellreport = new JLabel("Online Sell Report");
	
	JLabel logo = new JLabel(new ImageIcon("images/pms logo - Copy.jpg"));
	
	Changeadmin changeadmin = new Changeadmin();
	Addmedicine addmedicine = new Addmedicine();
	Addstuff addstuff = new Addstuff();
	Customerinfo customerinfo = new Customerinfo();
	Stock stock = new Stock();
	Stuffinfo stuffinfo = new Stuffinfo();
	Supplierinfo supplierinfo = new Supplierinfo();
	Addtocart addtocart = new Addtocart();
	Sellreport sellreport = new Sellreport();
	Addsupplier addsupplier = new Addsupplier();
	order order = new order();
	onSellReport onsellreport = new onSellReport();
	
	
	public FrontFrame() {
		setLayout(new BorderLayout());
		add(panelnorth,BorderLayout.NORTH);
		panelnorthwork();
		add(panelwest,BorderLayout.WEST);
		panelwestwork();
		add(paneleast,BorderLayout.EAST);
		paneleastwork();
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		btnAction();
		
		
	}
	private void btnAction() {
		btnmedicine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				panelmedicine.setVisible(true);
				
				
			}
		});
		
		btncustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				panelcustomer.setVisible(true);
			}
		});
		btnsupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				panelsupplier.setVisible(true);
			}
		});
		btnstuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				panelstuff.setVisible(true);
			}
		});
		btntools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				paneltools.setVisible(true);
			}
		});
		btnsales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelwestfalse();
				panelcenterfalse();
				panelsales.setVisible(true);
			}
		});
		/* ;;;;;;;;;;; menu-item buttons ;;;;;;;;; */
		btnchangeadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				changeadmin.setVisible(true);
			}
		});
		btnaddmedicine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				addmedicine.setVisible(true);
				addmedicine.AutoId();
				addmedicine.supplierid();
				addmedicine.medicinename();
				
			
			}
		});
		btnaddstuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				addstuff.setVisible(true);
				addstuff.AutoId();
			}
		});
		btnaddsupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				addsupplier.setVisible(true);
				addsupplier.AutoId();
			}
		});
		btncustomerinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				customerinfo.setVisible(true);
				customerinfo.tableDataLoad();
				customerinfo.setID();
			}
		});
		btnstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				stock.setVisible(true);
				stock.tableDataLoad();
				stock.setID();
				
			}
		});
		btnstuffinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				stuffinfo.setVisible(true);
				stuffinfo.tableDataLoad();
				stuffinfo.setID();
			}
		});
		btnsupplierinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				supplierinfo.setVisible(true);
				supplierinfo.tableDataLoad();
				supplierinfo.setID();
			}
		});
		btnaddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				addtocart.setVisible(true);
				addtocart.tableDataLoad();
				addtocart.AutoId();
				addtocart.AutoId2();
			}
		});
		btnsellreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				sellreport.setVisible(true);
				sellreport.tableOrderDataLoad();
				//sellreport.setID();
			}
		});
		btnorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				order.setVisible(true);
				order.tableOrderDataLoad();
				order.tableDetailsDataLoad(null);
			}
		});
		btnonsellreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelcenterfalse();
				onsellreport.setVisible(true);
				onsellreport.tableOrderDataLoad();
				onsellreport.tableDetailsDataLoad(null);
			}
		});
	}
	private void panelcenterwork() {
		panelcenter.setBorder(BorderFactory.createRaisedBevelBorder());
	
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		panelcenter.setLayout(flow);
		panelcenter.add(changeadmin);
		panelcenter.add(addmedicine);
		panelcenter.add(addstuff);
		panelcenter.add(customerinfo);
		panelcenter.add(stock);
		panelcenter.add(stuffinfo);
		panelcenter.add(supplierinfo);
		panelcenter.add(addtocart);
		panelcenter.add(sellreport);
		panelcenter.add(addsupplier);
		panelcenter.add(onsellreport);
		panelcenter.add(order);
		panelcenterfalse();
		
		
	}
	private void panelcenterfalse() {
		changeadmin.setVisible(false);
		addmedicine.setVisible(false);
		addstuff.setVisible(false);
		customerinfo.setVisible(false);
		stock.setVisible(false);
		stuffinfo.setVisible(false);
		supplierinfo.setVisible(false);
		addtocart.setVisible(false);
		sellreport.setVisible(false);
		addsupplier.setVisible(false);
		order.setVisible(false);
		onsellreport.setVisible(false);
		
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
		FlowLayout flow = new FlowLayout();
		panelnortheast.setLayout(flow);
		flow.setHgap(15);
		flow.setVgap(58);
		panelnortheast.add(btnmedicine);
		btnmedicine.setPreferredSize(new Dimension(130,40));
		panelnortheast.add(btncustomer);
		btncustomer.setPreferredSize(new Dimension(130,40));
		panelnortheast.add(btnsupplier);
		btnsupplier.setPreferredSize(new Dimension(130,40));
		panelnortheast.add(btnstuff);
		btnstuff.setPreferredSize(new Dimension(130,40));
		panelnortheast.add(btnsales);
		btnsales.setPreferredSize(new Dimension(130,40));
		panelnortheast.add(btntools);
		btntools.setPreferredSize(new Dimension(130,40));
		
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
		panelwest.add(panelmedicine);
		panelmedicinework();
		panelwest.add(panelcustomer);
		panelcustomerwork();
		panelwest.add(panelsupplier);
		panelsupplierwork();
		panelwest.add(panelstuff);
		panelstuffwork();
		panelwest.add(paneltools);
		paneltoolswork();
		panelwest.add(panelsales);
		panelsaleswork();
		panelwestfalse();
	}
	private void panelwestfalse() {
		panelmedicine.setVisible(false);
		panelcustomer.setVisible(false);
		panelsupplier.setVisible(false);
		panelstuff.setVisible(false);
		paneltools.setVisible(false);
		panelsales.setVisible(false);
		
	}
	private void panelsaleswork() {
		panelsales.setPreferredSize(new Dimension(150,920));
		//panelsales.setBackground(Color.BLACK);
		panelsales.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(btnaddtocart,c);
		btnaddtocart.setBackground(Color.RED);
		btnaddtocart.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(lbladdtocart,c);
		lbladdtocart.setBackground(Color.RED);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(btnsellreport,c);
		btnsellreport.setBackground(Color.RED);
		btnsellreport.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(lblsellreport,c);
		lblsellreport.setBackground(Color.RED);
		
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(btnorder,c);
		btnorder.setBackground(Color.RED);
		btnorder.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(lblorder,c);
		lblorder.setBackground(Color.RED);
		
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(btnonsellreport,c);
		btnonsellreport.setBackground(Color.RED);
		btnonsellreport.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsales.add(lblonsellreport,c);
		lblonsellreport.setBackground(Color.RED);
		
	}
	private void panelmedicinework() {
		//panelmedicine.setBorder(BorderFactory.createRaisedBevelBorder());
		panelmedicine.setPreferredSize(new Dimension(150,920));
		System.out.println(displaysize);
		//panelmedicine.setBackground(Color.blue);
		panelmedicine.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelmedicine.add(btnaddmedicine,c);
		btnaddmedicine.setBackground(Color.RED);
		btnaddmedicine.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelmedicine.add(lbladdmedicine,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelmedicine.add(btnstock,c);
		btnstock.setBackground(Color.RED);
		btnstock.setForeground(Color.WHITE);
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelmedicine.add(lblstock,c);
		
	}
	private void panelcustomerwork() {
		panelcustomer.setPreferredSize(new Dimension(150,920));
		panelcustomer.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelcustomer.add(btncustomerinfo,c);
		btncustomerinfo.setBackground(Color.RED);
		btncustomerinfo.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelcustomer.add(lblcustomerinfo,c);
		
		
		
	}
	private void panelsupplierwork() {
		//panelsupplier.setBackground(Color.blue);
		panelsupplier.setPreferredSize(new Dimension(150,920));
		panelsupplier.setPreferredSize(new Dimension(150,920));
		panelsupplier.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsupplier.add(btnsupplierinfo,c);
		btnsupplierinfo.setBackground(Color.RED);
		btnsupplierinfo.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsupplier.add(lblsupplierinfo,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsupplier.add(btnaddsupplier,c);
		btnaddsupplier.setBackground(Color.RED);
		btnaddsupplier.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelsupplier.add(lbladdsupplier,c);
		
	}
	private void panelstuffwork() {
		//panelstuff.setBackground(Color.orange);
		panelstuff.setPreferredSize(new Dimension(150,920));
		panelstuff.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelstuff.add(btnaddstuff,c);
		btnaddstuff.setBackground(Color.RED);
		btnaddstuff.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelstuff.add(lbladdstuff,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelstuff.add(btnstuffinfo,c);
		btnstuffinfo.setBackground(Color.RED);
		btnstuffinfo.setForeground(Color.WHITE);
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		panelstuff.add(lblstuffinfo,c);
		
	}
	private void paneltoolswork() {
		//paneltools.setBackground(Color.yellow);
		paneltools.setPreferredSize(new Dimension(150,920));
		paneltools.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		paneltools.add(btnchangeadmin,c);
		btnchangeadmin.setBackground(Color.RED);
		btnchangeadmin.setForeground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets= new Insets(5,5,5,5);
		paneltools.add(lblchangeadmin,c);
		
		
		
		
	}
	private void paneleastwork() {
		//paneleast.setBorder(BorderFactory.createRaisedBevelBorder());
		paneleast.setPreferredSize(new Dimension(260,0));
		
	}

}
