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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.test.admin.DbConnection;
import com.test.admin.FrequentlyUse;
import com.test.admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class Addtocart extends JPanel {
	int a;
	DecimalFormat df = new DecimalFormat("#0.00");
	Font customfont = new Font("serif",Font.PLAIN,25);
	Font bordercustomfont = new Font("serif",Font.BOLD,30);

	Object row [][] = {};
	String[] column = {"Medicine ID","Medicine Name","Medicine Type",
			"Mfg Date","Exp Date","Qty","Unit","Price(per unit)","SupplierID"};
	DefaultTableModel model = new DefaultTableModel(row,column);
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	Object rowcart [][] = {};
	String[] columncart = {"Medicine ID","Medicine Name","Medicine Type",
			"Mfg Date","Exp Date","Qty","Unit","Price(per unit)","Total"};
	DefaultTableModel modelcart = new DefaultTableModel(rowcart,columncart);
	JTable tablecart = new JTable(modelcart);
	JScrollPane scrollcart = new JScrollPane(tablecart,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JPanel panelnorth = new JPanel();
	JPanel panelcenter = new JPanel();
	JPanel panelcentersouth = new JPanel();
	JPanel panelcentercenter = new JPanel();
	JPanel panelsouth = new JPanel();

	JLabel lblsellid = new JLabel("Sell ID");
	JLabel lblmedicineid = new JLabel("Medicine ID");
	JLabel lblmedicinename = new JLabel("Medicine Name");
	JLabel lblmedicinetype = new JLabel("Medicine Type");
	JLabel lblqty = new JLabel("Qty");
	JLabel lblunit = new JLabel("Unit");
	JLabel lblprice = new JLabel("Price(per unit)");
	JLabel lbltotal = new JLabel("Total");
	JLabel lblcustomerid = new JLabel("Customer ID");
	JLabel lblcustomername = new JLabel("Customer Name");
	JLabel lblcustomermobile = new JLabel("Customer Mobile");
	JLabel lblcustomeraddress = new JLabel("Customer Address");
	JLabel lblselldate = new JLabel("Sell Date");
	JLabel lblexpdate = new JLabel("Exp Date");
	JLabel lblmfgdate = new JLabel("Mfg Date");
	JLabel lbltotalamount = new JLabel("Total Amount");


	JTextField txtsellid = new JTextField(15);
	JTextField txtqty = new JTextField(15);
	JTextField txtunit = new JTextField(15);
	JTextField txtprice = new JTextField(15);
	JTextField txttotal = new JTextField(15);
	JTextField txtcustomerid = new JTextField(15);
	JTextField txtcustomername = new JTextField(15);
	JTextField txtcustomeraddress = new JTextField(15);
	JTextField txttotalamount = new JTextField(15);

	JDateChooser selldate = new JDateChooser();
	JDateChooser expdate = new JDateChooser();
	JDateChooser mfgdate = new JDateChooser();

	SuggestText medicinenamesuggest = new SuggestText();
	SuggestText medtypesuggest = new SuggestText();
	SuggestText medicineidsuggest = new SuggestText();
	SuggestText customermobile = new SuggestText();

	JButton btnsave = new JButton("save",new ImageIcon("images/save.png"));
	JButton btnreset = new JButton("reset",new ImageIcon("images/reset.png"));
	JButton btnadd = new JButton("Add");
	JButton btnconfirm = new JButton("Confirm");
	JButton btnresetcart = new JButton("Reset");
	JButton btnremove = new JButton("Remove");

	public Addtocart() {
		//setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(1500,930));
		setLayout(new BorderLayout());
		add(panelnorth,BorderLayout.NORTH);
		panelnorthwork();
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelsouth,BorderLayout.SOUTH);
		panelsouthwork();
		btnAction();

	}
	private void loadFromTable(String medId) {
		try {
			String query="select  medicineid,medicinename,medicinetype,mfgdate,expdate,"
					+ "qty,unit,price from tbmedicinestock where medicineid='"+medId+"'";
			DbConnection.connect();
			ResultSet rs=DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				medicineidsuggest.txtSuggest.setText(rs.getString("medicineid"));
				medicinenamesuggest.txtSuggest.setText(rs.getString("medicinename"));
				medtypesuggest.txtSuggest.setText(rs.getString("medicinetype"));
				Date mfg = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("mfgdate"));
				mfgdate.setDate(mfg);
				Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("expdate"));
				expdate.setDate(exp);
				//new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.setDate(new Date(rs.getString("mfgdate"))));
				//mfgdate.setDate(new );
				//expdate.setDate(new Date("expdate"));
				txtqty.setText(rs.getString("qty"));
				txtunit.setText(rs.getString("unit"));
				txtprice.setText(rs.getString("price"));
			}
			DbConnection.con.close();

		}
		catch(Exception exp) {
			JOptionPane.showMessageDialog(null, exp);
		}
	}
	private void Calculate() {
		double qty;
		if(txtqty.getText().isEmpty()) {
			qty = 0.00;
		}else {
			qty = Float.parseFloat(txtqty.getText());
		}
		double price =Float.parseFloat(txtprice.getText());
		double result = price*qty;
		txttotal.setText(df.format(result));


	}
	public void total() {
		double sum=0;
		double val;
		for(int a=0;a<modelcart.getRowCount();a++) {
			val=Double.parseDouble(""+modelcart.getValueAt(a,8));
			sum=sum+val;
		}
		txttotalamount.setText(df.format(sum));
	}
	private void addIntoCart() {
		modelcart.addRow(new Object[] {
				medicineidsuggest.txtSuggest.getText().trim(),
				medicinenamesuggest.txtSuggest.getText().trim(),
				medtypesuggest.txtSuggest.getText().trim(),
				new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.getDate()),
				new SimpleDateFormat("yyyy-MM-dd").format(expdate.getDate()),
				txtqty.getText().trim(),
				txtunit.getText().trim(),
				txtprice.getText().trim(),
				txttotal.getText().trim()
		});
	}
	private boolean isEmptyCheck(){
		if(!medicineidsuggest.txtSuggest.getText().isEmpty()) {
			if(!medicinenamesuggest.txtSuggest.getText().isEmpty()) {
				if(!medtypesuggest.txtSuggest.getText().isEmpty()) {
					if(!txtqty.getText().isEmpty()) {
						if(!txttotal.getText().isEmpty()) {
							if(!txtunit.getText().isEmpty()) {
								if(!txtprice.getText().isEmpty()) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	private boolean isEmptyCheckCustomer(){
		if(!txtcustomerid.getText().isEmpty()) {
			if(!txtcustomername.getText().isEmpty()) {
				if(!customermobile.txtSuggest.getText().isEmpty()) {
					if(!txtcustomeraddress.getText().isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean isExistIdCheck() {
		String store;
		String id =medicineidsuggest.txtSuggest.getText().trim();
		for(int r=0;modelcart.getRowCount()>r;r++) {
			store = (modelcart.getValueAt(r,0)).toString();
			if(id.equals(store)) {
				return true;
			}
		}
		return false;
	}
	private void removeFromCart() {
		if(tablecart.getSelectedRow()!=-1) {
		modelcart.removeRow(tablecart.getSelectedRow());
		total();
		}
	}
	private void resetCart() {
//		while(modelcart.getRowCount()>0) {
//			modelcart.removeRow(0);
//		}
		for(int a= tablecart.getRowCount()-1;a>=0;a--) {
			modelcart.removeRow(a);
		}
		
	}
	private void changeStock() {
		for(int r = 0;modelcart.getRowCount()>r;r++) {
			//int qty=  modelcart.getValueAt(r, 5);
			int qty = Integer.valueOf((String) modelcart.getValueAt(r, 5));
			 
			 String mid = (String) modelcart.getValueAt(r, 0);
			 Updateqty(qty,mid);}
		
	}
	public void Updateqty(int qty,String mid) {
		//qty update after sell 
		int a= getValue(mid);
		int b = qty;
		int value =a-b;
		
		try {
			DbConnection.connect();
			String query ="update tbmedicinestock set qty='"+value+"' where"
					+ " medicineid='"+mid+"'";
			DbConnection.sta.executeUpdate(query);
			DbConnection.con.close();
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public int getValue(String mid) {
		//int a=0 ;
		try {
			DbConnection.connect();
			String query = "select cast((select qty from tbmedicinestock where medicineid='"+mid+"')as UNSIGNED) qt";
			ResultSet rs = DbConnection.sta.executeQuery(query);

			while(rs.next()) {
				a = rs.getInt("qt");	
			}
			
			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		return a;
	}
	private void getCustomerInfo() {
		try {
			DbConnection.connect();
			String info = "select customerid, customername, customeraddress from "
					+ "tbcustomerinfo where customermobile='"+customermobile.txtSuggest.getText()+"'";
			ResultSet rs = DbConnection.sta.executeQuery(info);
			if(rs.next()) {
				txtcustomerid.setText(rs.getString("customerid"));
				txtcustomername.setText(rs.getString("customername"));
				txtcustomeraddress.setText(rs.getString("customeraddress"));
				} 
			else {
				txtcustomeraddress.setText("");
				txtcustomerid.setText("");
				txtcustomername.setText("");
				AutoId2();
			}
			DbConnection.con.close();
			
		}
		catch(Exception exp) {
			JOptionPane.showMessageDialog(null, exp);
		}
	}
	private void btnAction() {
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				int col=0;
				String medId=model.getValueAt(row, col).toString();
				loadFromTable(medId);
				Calculate();	
			}
		});
		customermobile.cmbSuggest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getCustomerInfo();
				
			}
		});
		
		customermobile.txtSuggest.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				getCustomerInfo();			
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		txtqty.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				Calculate();

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		txtprice.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				Calculate();
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		//btnconfirm
//		btnsave.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if(FrequentlyUse.confirmation("Want to Save?")) {
//					if(FrequentlyUse.insertData(getinsertQuery())) {
//
//						JOptionPane.showMessageDialog(null,"Sell Saved Successfully");
//					}
//					if(FrequentlyUse.insertData(getinsertQuery2())) {
//
//						JOptionPane.showMessageDialog(null,"Customer Saved Successfully");
//					}
//
//				}
//				//Updateqty();
//				tableDataLoad();
//				TextClear();
//				AutoId();
//
//
//			}
//		});
		btnreset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextClearOnConfirmation();
				AutoId2();

			}
		});
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isEmptyCheck()) {
					if(!(isExistIdCheck())) {
						addIntoCart();
						TextClear();
						total();
					}
					else{
						JOptionPane.showMessageDialog(null,"Already added into the Cart !!");
					}

				}
				else {
					JOptionPane.showMessageDialog(null,"Fields Can not be Empty !!");
				}
			}
		});
		btnremove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeFromCart();
			}
		});
		btnresetcart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetCart();
			}
		});
		btnconfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(FrequentlyUse.confirmation("Want to Save?")) {
					if(isEmptyCheckCustomer()) {
						
							if(modelcart.getRowCount()>0) {
								changeStock();
								orderConfirmation();		
								tableDataLoad();
								TextClearOnConfirmation();

							}else
								JOptionPane.showMessageDialog(null, "!!Cart is Empty");
						
						}
					else
						JOptionPane.showMessageDialog(null, "Customer Details is Empty");
					}
					
				//AutoId();
			}
		});
	}
	private void orderConfirmation() {
		try {
			DbConnection.connect();
			DbConnection.con.setAutoCommit(false);
			for(int r=0;modelcart.getRowCount()>r;r++) {
				String cartquery = "insert into tbselldetailsinfo values("
						+ "'"+txtcustomerid.getText().trim()+"',"
						+ "'"+txtsellid.getText().trim()+"',"
						+ "'"+modelcart.getValueAt(r, 0)+"',"
						+ "'"+modelcart.getValueAt(r, 1)+"',"
						+ "'"+modelcart.getValueAt(r, 2)+"',"
						+ "'"+modelcart.getValueAt(r, 3)+"',"
						+ "'"+modelcart.getValueAt(r, 4)+"',"
						+ "'"+modelcart.getValueAt(r, 5)+"',"
						+ "'"+modelcart.getValueAt(r, 6)+"',"
						+ "'"+modelcart.getValueAt(r, 7)+"',"
						+ "'"+modelcart.getValueAt(r, 8)+"')";
				DbConnection.sta.executeUpdate(cartquery);
			}
			String info = "insert into tbsellinfo(sellid,customerid,selldate,total) values("
					+ "'"+txtsellid.getText().trim()+"',"
					+ "'"+txtcustomerid.getText().trim()+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(selldate.getDate()).trim()+"',"
					+ "'"+txttotalamount.getText().trim()+"')";
			DbConnection.sta.executeUpdate(info);
			
			String customercheck = "select * from tbcustomerinfo where customerid ='"+txtcustomerid.getText()+"'";
			ResultSet customer = DbConnection.sta.executeQuery(customercheck);
			if(!customer.next()) {
				String history = "insert into tbcustomerinfo(CustomerID,customername,customermobile,customeraddress) values("
						+ "'"+txtcustomerid.getText().trim()+"',"
						+ "'"+txtcustomername.getText().trim()+"',"
						+ "'"+customermobile.txtSuggest.getText().trim()+"',"
						+ "'"+txtcustomeraddress.getText().trim()+"')";
				DbConnection.sta.executeUpdate(history);
			}
			
			
			
			
			DbConnection.con.commit();
			resetCart();
			JOptionPane.showMessageDialog(null, "Sell Saved Successfully");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	private void panelnorthwork() {
		panelnorth.setPreferredSize(new Dimension(1500,250));
		//panelnorth.setBorder(BorderFactory.createRaisedBevelBorder());

		panelnorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Current Stock", 2, 3,bordercustomfont ));
		panelnorth.add(scroll);
		scroll.setPreferredSize(new Dimension(1440,180));

	}
	private void panelcenterwork() {
		panelcenter.setLayout(new BorderLayout());
		panelcenter.add(panelcentercenter,BorderLayout.CENTER);
		panelcentercenterwork();
		panelcenter.add(panelcentersouth,BorderLayout.SOUTH);
		panelcentersouthwork();
	}
	private void panelcentersouthwork() {
		panelcentersouth.setPreferredSize(new Dimension(1500,300));
		panelcentersouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Cart", 2, 3,bordercustomfont ));
		panelcentersouth.add(scrollcart);
		scrollcart.setPreferredSize(new Dimension(1430,185));
		panelcentersouth.add(btnremove);
		btnremove.setPreferredSize(new Dimension(100,40));
		btnremove.setBackground(Color.red);
		btnremove.setForeground(Color.white);
		panelcentersouth.add(btnresetcart);
		btnresetcart.setPreferredSize(new Dimension(100,40));
		
		panelcentersouth.add(lbltotalamount);
		panelcentersouth.add(txttotalamount);
		txttotalamount.setPreferredSize(new Dimension(0,20));
		txttotalamount.setBackground(Color.lightGray);
		txttotalamount.setEditable(false);
	}
	private void panelcentercenterwork(){
		panelcentercenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		/////////////////////
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblsellid,c);
		lblsellid.setAlignmentX(LEFT_ALIGNMENT);
		lblsellid.setFont(customfont);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(txtsellid,c);


		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblmedicineid,c);
		lblmedicineid.setFont(customfont);

		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(medicineidsuggest.cmbSuggest,c);

		medicineid();

		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblmedicinename,c);
		lblmedicinename.setFont(customfont);


		c.gridx = 5;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(medicinenamesuggest.cmbSuggest,c);
		medicinename();



		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblmedicinetype,c);
		lblmedicinetype.setFont(customfont);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(medtypesuggest.cmbSuggest,c);
		medtypesuggest.v.add("Tablet");
		medtypesuggest.v.add("Capsule");
		medtypesuggest.v.add("Liquid");
		medtypesuggest.v.add("Suspension");
		medtypesuggest.v.add("Drop");
		medtypesuggest.v.add("Cream");
		medtypesuggest.v.add("Ointment");
		medtypesuggest.v.add("Spray");
		medtypesuggest.v.add("Inhalers");
		medtypesuggest.v.add("Injection");
		medtypesuggest.v.add("Suppositories");


		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblmfgdate,c);
		lblmfgdate.setFont(customfont);

		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(mfgdate,c);
		mfgdate.setDateFormatString("dd-MM-yyyy");

		c.gridx = 4;
		c.gridy = 1;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblexpdate,c);
		lblexpdate.setFont(customfont);

		c.gridx = 5;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(expdate,c);
		expdate.setDateFormatString("dd-MM-yyyy");

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblqty,c);
		lblqty.setFont(customfont);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(txtqty,c);

		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblprice,c);
		lblprice.setFont(customfont);

		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(txtprice,c);

		c.gridx = 4;
		c.gridy = 2;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblunit,c);
		lblunit.setFont(customfont);

		c.gridx = 5;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(txtunit,c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lblselldate,c);
		lblselldate.setFont(customfont);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(selldate,c);
		selldate.setDateFormatString("dd-MM-yyyy");
		selldate.setDate(new Date());

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(lbltotal,c);
		lbltotal.setFont(customfont);

		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(txttotal,c);


		c.gridx = 4;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,105);
		c.fill = GridBagConstraints.BOTH;
		panelcentercenter.add(btnadd,c);
	}
	private void panelsouthwork() {

		panelsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelsouth.setPreferredSize(new Dimension(1500,205));
		panelsouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Customer Details", 2, 3,bordercustomfont ));
		panelsouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(lblcustomerid,c);
		lblcustomerid.setFont(customfont);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(txtcustomerid,c);

		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(lblcustomername,c);
		lblcustomername.setFont(customfont);


		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(txtcustomername,c);


		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(lblcustomermobile,c);
		lblcustomermobile.setFont(customfont);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(customermobile.cmbSuggest,c);
		customermobile();

		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(lblcustomeraddress,c);
		lblcustomeraddress.setFont(customfont);

		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(txtcustomeraddress,c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouth.add(btnreset,c);
		btnreset.setBackground(Color.red);
		btnreset.setForeground(Color.WHITE);
		btnreset.setFont(new Font("serif",Font.PLAIN,20));

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		panelsouth.add(btnconfirm,c);
		btnconfirm.setPreferredSize(new Dimension(100,40));
		btnconfirm.setBackground(Color.GREEN);

	}
	private void TextClearOnConfirmation() {
		txtcustomeraddress.setText("");
		txtcustomerid.setText("");
		txtsellid.setText("");
		customermobile.txtSuggest.setText("");
		txtcustomername.setText("");
		AutoId();
		AutoId2();
	}
	private void TextClear() {
		txtprice.setText("");
		txtqty.setText("");
		txttotal.setText("");
		txtunit.setText("");

		medtypesuggest.txtSuggest.setText("");
		medicinenamesuggest.txtSuggest.setText("");
		medicineidsuggest.txtSuggest.setText("");
		selldate.setDate(new Date());
		expdate.setDate(new Date());
		mfgdate.setDate(new Date());
	}

	public void AutoId() {
		try {
			DbConnection.connect();
			String query = "select ifnull(max(cast(substring(SellID,locate('-',SellID)+1,"
					+ "length(SellID)-locate('-',SellID))as UNSIGNED)),0)+1 id from tbsellinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtsellid.setText("Sell-"+rs.getString("id"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public void AutoId2() {
		try {
			DbConnection.connect();
			String query = "select ifnull(max(cast(substring(CustomerID,locate('-',CustomerID)+1,"
					+ "length(CustomerID)-locate('-',CustomerID))as UNSIGNED)),0)+1 id from tbcustomerinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtcustomerid.setText("Cus-"+rs.getString("id"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	

	public void medicineid() {
		try {
			DbConnection.connect();
			String query = "select medicineid from tbmedicinestock";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				medicineidsuggest.v.add(rs.getString("medicineid"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public void medicinename() {
		try {
			DbConnection.connect();
			String query = "select MedicineName from tbmedicinestock";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				medicinenamesuggest.v.add(rs.getString("MedicineName"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}

	}
	public void customermobile() {
		try {
			DbConnection.connect();
			String query = "select customermobile from tbcustomerinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				customermobile.v.add(rs.getString("customermobile"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}

	}

	public void tableDataLoad() {
		for(int a= table.getRowCount()-1;a>=0;a--) {
			model.removeRow(a);
		}
		try {
			DbConnection.connect();
			String query ="select MedicineID,MedicineName,MedicineType,MfgDate,ExpDate,"
					+ "Qty,Unit,Price,SupplierID from tbmedicinestock order by medicinename";
			ResultSet rs =DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("MedicineID"),
						rs.getString("MedicineName"),
						rs.getString("MedicineType"),
						rs.getString("MfgDate"),
						rs.getString("ExpDate"),
						rs.getString("Qty"),
						rs.getString("Unit"),
						rs.getString("Price"),
						rs.getString("SupplierID")});
			}
			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public void paneleast() {

	}

}
