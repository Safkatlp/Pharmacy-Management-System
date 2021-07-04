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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.test.admin.SuggestText;
import com.toedter.calendar.JDateChooser;
import com.test.admin.DbConnection;
import com.test.admin.FrequentlyUse;
import com.test.admin.LoginFrame;


public class placeOrder extends JPanel{
	int a;
	int qtyStore;
	String userid;
	Font customfont = new Font("serif",Font.PLAIN,25);
	Object row [][] = {};
	String[] column = {"Medicine ID","Medicine Name","Medicine Type",
			"Mfg Date","Exp Date","Qty","Unit","Price(per unit)"};
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
	
	Font bordercustomfont = new Font("serif",Font.BOLD,30);
	DecimalFormat df = new DecimalFormat("#0.00");
	
	JPanel panelcenter = new JPanel();
	JPanel paneleast = new JPanel();
	JPanel paneleastsouth = new JPanel();
	JPanel paneleastnorth = new JPanel();
	JPanel paneleastcenter = new JPanel();
	JPanel panelcenternorth = new JPanel();
	JPanel panelcentercenter = new JPanel();
	
	JButton btnconfirm = new JButton("Confirm");
	JButton btnreset = new JButton("Reset");
	JButton btnremove = new JButton("Remove");
	JButton btnadd = new JButton("Add");
	
	JLabel lblonsellid = new JLabel("online Sell ID");
	JLabel lblselldate = new JLabel("Sell Date");
	JLabel lblmedicineid = new JLabel("Medicine ID");
	JLabel lblmedicinename = new JLabel("Medicine Name");
	JLabel lblmedicinetype = new JLabel("Medicine Type");
	JLabel lblmfgdate = new JLabel("MFG Date");
	JLabel lblexpdate = new JLabel("EXP Date");
	JLabel lblqty = new JLabel("QTY");
	JLabel lblprice = new JLabel("Price(per unit)");
	JLabel lblunit = new JLabel("Unit");
	JLabel lbltotal = new JLabel("Total");
	JLabel lbluserID = new JLabel("User Id");
	JLabel lbltotalamount = new JLabel("Total Amount");
	
	JTextField txtonsellid = new JTextField(20);
	JTextField txtqty = new JTextField(20);
	JTextField txtunit = new JTextField(20);
	JTextField txtprice = new JTextField(20);
	JTextField txttotal = new JTextField(20);
	JTextField txtuserid = new JTextField(20);
	JTextField txttotalamount = new JTextField(20);
	
	JTextField medicineid = new JTextField(20);
	JTextField medicinetype = new JTextField(20);
	JTextField medicinename = new JTextField(20);
	
	
	

	JDateChooser selldate = new JDateChooser();
	JDateChooser mfgdate = new JDateChooser();
	JDateChooser expdate = new JDateChooser();

	
	
	public placeOrder() {
		setPreferredSize(new Dimension(1760,930));
		setBackground(Color.blue);
		setLayout(new BorderLayout());
		cmp();
		btnAction();
		
	}

	private void cmp() {
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(paneleast,BorderLayout.EAST);
		paneleastwork();
		
		
	}

	private void paneleastwork() {
		//paneleast.setBackground(Color.blue);
		paneleast.setPreferredSize(new Dimension(420,0));
		paneleast.setLayout(new BorderLayout());
		paneleast.add(paneleastnorth,BorderLayout.NORTH);
		paneleastnorthwork();
		paneleast.add(paneleastcenter,BorderLayout.CENTER);
		paneleastcenterwork();
		paneleast.add(paneleastsouth,BorderLayout.SOUTH);
		paneleastsouthwork();
		
	}

	private void paneleastsouthwork() {
		//paneleastsouth.setBackground(Color.blue);
		paneleastsouth.setPreferredSize(new Dimension(0,310));
		
	}

	private void paneleastcenterwork() {
		//paneleastcenter.setBorder(BorderFactory.createRaisedBevelBorder());
		paneleastcenter.setLayout(new GridBagLayout());
		//paneleastcenter.setBackground(Color.green);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		paneleastcenter.add(lblmedicineid,c);
		lblmedicineid.setFont(customfont);
		

		c.gridx = 1;
		c.gridy = 0;
		//c.insets = new Insets(1,5,1,5);
		paneleastcenter.add(medicineid,c);
		
		//medicineid();
		medicineid.setEditable(false);
		medicineid.setBackground(Color.LIGHT_GRAY);

		c.gridx = 0;
		c.gridy = 2;
		paneleastcenter.add(lblmedicinename,c);
		lblmedicinename.setFont(customfont);

		c.gridx = 1;
		c.gridy = 2;
		paneleastcenter.add(medicinename,c);
		medicinename.setEditable(false);
		medicinename.setBackground(Color.LIGHT_GRAY);
		//medicinename();
		
		c.gridx = 0;
		c.gridy = 3;
		paneleastcenter.add(lblmedicinetype,c);
		lblmedicinetype.setFont(customfont);

		c.gridx = 1;
		c.gridy = 3;
		//c.insets = new Insets(1,5,1,5);
		paneleastcenter.add(medicinetype,c);
		medicinetype.setEditable(false);
		medicinetype.setBackground(Color.LIGHT_GRAY);

		c.gridx = 0;
		c.gridy = 4;
		paneleastcenter.add(lblmfgdate,c);
		lblmfgdate.setFont(customfont);

		c.gridx = 1;
		c.gridy = 4;
		paneleastcenter.add(mfgdate,c);
		mfgdate.setEnabled(false);
		mfgdate.setBackground(Color.LIGHT_GRAY);
		
		c.gridx = 0;
		c.gridy = 5;
		paneleastcenter.add(lblexpdate,c);
		lblexpdate.setFont(customfont);

		c.gridx = 1;
		c.gridy = 5;
		//c.insets = new Insets(1,5,1,5);
		paneleastcenter.add(expdate,c);
		expdate.setEnabled(false);
		expdate.setBackground(Color.LIGHT_GRAY);
		
		c.gridx = 0;
		c.gridy = 6;
		paneleastcenter.add(lblqty,c);
		lblqty.setFont(customfont);

		c.gridx = 1;
		c.gridy = 6;
		paneleastcenter.add(txtqty,c);
		
		
		c.gridx = 0;
		c.gridy = 7;
		paneleastcenter.add(lblunit,c);
		lblunit.setFont(customfont);

		c.gridx = 1;
		c.gridy = 7;
		//c.insets = new Insets(1,5,1,5);
		paneleastcenter.add(txtunit,c);
		txtunit.setEditable(false);
		txtunit.setBackground(Color.LIGHT_GRAY);
		
		c.gridx = 0;
		c.gridy = 8;
		paneleastcenter.add(lblprice,c);
		lblprice.setFont(customfont);

		c.gridx = 1;
		c.gridy = 8;
		paneleastcenter.add(txtprice,c);
		txtprice.setEditable(false);
		txtprice.setBackground(Color.LIGHT_GRAY);
		
		c.gridx = 0;
		c.gridy = 9;
		paneleastcenter.add(lbltotal,c);
		lbltotal.setFont(customfont);

		c.gridx = 1;
		c.gridy = 9;
		paneleastcenter.add(txttotal,c);
		txttotal.setEditable(false);
		txttotal.setBackground(Color.lightGray);
		
		c.gridx = 1;
		c.gridy = 10;
		c.insets  = new Insets(5, 115, 5, 5);
		paneleastcenter.add(btnadd,c);
		btnadd.setPreferredSize(new Dimension(100,40));
		}
	public void gettingUserid() {
		try {
			DbConnection.connect();
			String query = "select userid from tbloggedininfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			if(rs.next()) {
				userid = rs.getString("userid");
				txtuserid.setText(userid);
			}
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
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
	private boolean isExistIdCheck() {
		String store;
		String id =medicineid.getText().trim();
		for(int r=0;modelcart.getRowCount()>r;r++) {
			store = (modelcart.getValueAt(r,0)).toString();
			if(id.equals(store)) {
				return true;
			}
		}
		return false;
	}
	private void addIntoCart() {
		modelcart.addRow(new Object[] {
				medicineid.getText().trim(),
				medicinename.getText().trim(),
				medicinetype.getText().trim(),
				new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.getDate()),
				new SimpleDateFormat("yyyy-MM-dd").format(expdate.getDate()),
				txtqty.getText().trim(),
				txtunit.getText().trim(),
				txtprice.getText().trim(),
				txttotal.getText().trim()
		});
	}
	public void AutoId() {
		try {
			DbConnection.connect();
			String query = "select ifnull(max(cast(substring(onsellid,locate('-',onsellid)+1,"
					+ "length(onsellid)-locate('-',onsellid))as UNSIGNED)),0)+1 id from tbonsellhistoryinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtonsellid.setText("OnSell-"+rs.getString("id"));
			}

			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
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
	private void loadFromTable(String medId) {
		try {
			String query="select  medicineid,medicinename,medicinetype,mfgdate,expdate,"
					+ "qty,unit,price from tbmedicinestock where medicineid='"+medId+"'";
			DbConnection.connect();
			ResultSet rs=DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				medicineid.setText(rs.getString("medicineid"));
				medicinename.setText(rs.getString("medicinename"));
				medicinetype.setText(rs.getString("medicinetype"));
				Date mfg = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("mfgdate"));
				mfgdate.setDate(mfg);
				Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("expdate"));
				expdate.setDate(exp);
				//new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.setDate(new Date(rs.getString("mfgdate"))));
				//mfgdate.setDate(new );
				//expdate.setDate(new Date("expdate"));
				qtyStore = Integer.parseInt(rs.getString("qty"));
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//public void recievename(String n) {																	////
//		 h=n;																							////
//		System.out.print(h+"from place order\n");			//loginframe()line: 291,232-237				////
//														//userside() line: 70							////
//																										////
//	}																									////
//	public void setname() {																				////
//				txtuserid.setText(h);																	////					////
//																										////
//																										////
//	}																									////
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void paneleastnorthwork() {
		paneleastnorth.setBorder(BorderFactory.createLoweredBevelBorder());
		paneleastnorth.setBackground(Color.white);
		paneleastnorth.setPreferredSize(new Dimension(0,150));
		paneleastnorth.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		paneleastnorth.add(lbluserID,c);
		lbluserID.setFont(customfont);

		c.gridx = 1;
		c.gridy = 0;
		//c.insets = new Insets(1,5,1,5);
		paneleastnorth.add(txtuserid,c);
		txtuserid.setEditable(false);
		txtuserid.setBackground(Color.gray);
		txtuserid.setForeground(Color.white);
		txtuserid.setFont(new Font("serif",Font.BOLD,16));
		
		c.gridx = 0;
		c.gridy = 1;
		paneleastnorth.add(lblonsellid,c);
		lblonsellid.setFont(customfont);

		c.gridx = 1;
		c.gridy = 1;
		paneleastnorth.add(txtonsellid,c);
		txtonsellid.setEditable(false);
		txtonsellid.setBackground(Color.gray);
		txtonsellid.setForeground(Color.white);
		txtonsellid.setFont(new Font("serif",Font.BOLD,16));
		
		c.gridx = 0;
		c.gridy = 2;
		paneleastnorth.add(lblselldate,c);
		lblselldate.setFont(customfont);

		c.gridx = 1;
		c.gridy = 2;
		paneleastnorth.add(selldate,c);
		selldate.setDateFormatString("dd-MM-yyyy");
		selldate.setDate(new Date());

	}

	private void panelcenterwork() {
		panelcenter.setBackground(Color.DARK_GRAY);
		panelcenter.setLayout(new BorderLayout());
		panelcenter.add(panelcenternorth,BorderLayout.NORTH);
		panelcenternorthwork();
		panelcenter.add(panelcentercenter,BorderLayout.CENTER);
		panelcentercenterwork();
		
		
	}

	private void panelcenternorthwork() {
		panelcenternorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Medicine Available", 2, 3,bordercustomfont ));
		panelcenternorth.setPreferredSize(new Dimension(0,460));
		panelcenternorth.add(scroll);
		scroll.setPreferredSize(new Dimension(1300,330));
	}

	private void panelcentercenterwork() {
		panelcentercenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Cart", 2, 3,bordercustomfont ));
		panelcentercenter.setPreferredSize(new Dimension(0,400));
		panelcentercenter.add(scrollcart);
		scrollcart.setPreferredSize(new Dimension(1300,330));
		panelcentercenter.add(btnremove);
		btnremove.setPreferredSize(new Dimension(100,40));
		btnremove.setBackground(Color.red);
		btnremove.setForeground(Color.white);
		panelcentercenter.add(btnreset);
		btnreset.setPreferredSize(new Dimension(100,40));
		panelcentercenter.add(btnconfirm);
		btnconfirm.setPreferredSize(new Dimension(100,40));
		btnconfirm.setBackground(Color.GREEN);
		panelcentercenter.add(lbltotalamount);
		panelcentercenter.add(txttotalamount);
		txttotalamount.setPreferredSize(new Dimension(0,20));
		txttotalamount.setBackground(Color.lightGray);
		txttotalamount.setEditable(false);
		
	}
	private void TextClear() {
		txtqty.setText("");
		txtunit.setText("");
		txtprice.setText("");
		txttotal.setText("");
		medicinetype.setText("");
		medicinename.setText("");
		medicineid.setText("");
		mfgdate.setDate(new Date());
		expdate.setDate(new Date());
		}
//	public void medicinename() {
//		try {
//			DbConnection.connect();
//			String query = "select distinct MedicineName from tbmedicinestock";
//			ResultSet rs = DbConnection.sta.executeQuery(query);
//			medicinenamesuggest.v.clear();
//			while(rs.next()) {
//				medicinenamesuggest.v.add(rs.getString("MedicineName"));
//			}
//			
//			DbConnection.con.close();
//			
//		}catch(Exception e) {
//			JOptionPane.showMessageDialog(null,e);
//		}
//	}
	public void total() {
		double sum=0;
		double val;
		for(int a=0;a<modelcart.getRowCount();a++) {
			val=Double.parseDouble(""+modelcart.getValueAt(a,8));
			sum=sum+val;
		}
		txttotalamount.setText(df.format(sum));
	}
//	public void medicineid() {
//		try {
//			DbConnection.connect();
//			String query = "select  distinct medicineid from tbmedicinestock";
//			ResultSet rs = DbConnection.sta.executeQuery(query);
//			medicineidsuggest.v.clear();
//			while(rs.next()) {
//				medicineidsuggest.v.add(rs.getString("medicineid"));
//			}
//			
//			DbConnection.con.close();
//			
//		}catch(Exception e) {
//			JOptionPane.showMessageDialog(null,e);
//		}
//	}
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
	private void orderConfirmation() {
		try {
			DbConnection.connect();
			DbConnection.con.setAutoCommit(false);
			for(int r=0;modelcart.getRowCount()>r;r++) {
				String cartquery = "insert into tbonselldetailsinfo values("
						+ "'"+txtuserid.getText().trim()+"',"
						+ "'"+txtonsellid.getText().trim()+"',"
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
			String info = "insert into tbonsellinfo(onsellid,userid,selldate,total) values("
					+ "'"+txtonsellid.getText().trim()+"',"
					+ "'"+txtuserid.getText().trim()+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(selldate.getDate()).trim()+"',"
					+ "'"+txttotalamount.getText().trim()+"')";
			DbConnection.sta.executeUpdate(info);
			
			String history = "insert into tbonsellhistoryinfo(onsellid,userid,selldate,total,status) values("
					+ "'"+txtonsellid.getText().trim()+"',"
					+ "'"+txtuserid.getText().trim()+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(selldate.getDate()).trim()+"',"
					+ "'"+txttotalamount.getText().trim()+"',"
					+ "'pending')";
			DbConnection.sta.executeUpdate(history);
			
			DbConnection.con.commit();
			resetCart();
			JOptionPane.showMessageDialog(null, "Order Placed Successfully");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private boolean isEmptyCheck(){
		if(!medicineid.getText().isEmpty()) {
			if(!medicinename.getText().isEmpty()) {
				if(!medicinetype.getText().isEmpty()) {
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
	private void changeStock() {
		for(int r = 0;modelcart.getRowCount()>r;r++) {
			//int qty=  modelcart.getValueAt(r, 5);
			int qty = Integer.valueOf((String) modelcart.getValueAt(r, 5));
			 
			 String mid = (String) modelcart.getValueAt(r, 0);
			 Updateqty(qty,mid);}
		
	}
	private boolean qtyCheck() {
		int value = Integer.parseInt(txtqty.getText());
		if(qtyStore>=value) {
			return true;
		}
		return false;
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
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isEmptyCheck()) {
					if(qtyCheck()) {
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
						JOptionPane.showMessageDialog(null, "Not Enough Stock !!");
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
		btnreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetCart();
			}
		});
		btnconfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(FrequentlyUse.confirmation("Want to Save?")) {
					if(modelcart.getRowCount()>0) {
					changeStock();
					orderConfirmation();		
					tableDataLoad();
					}else
						JOptionPane.showMessageDialog(null, "!!Cart is Empty");
				}
				AutoId();
			}
		});
		
	}
}
