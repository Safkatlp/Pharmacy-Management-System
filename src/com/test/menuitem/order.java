package com.test.menuitem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.test.admin.DbConnection;

public class order extends JPanel{
	int a;
	Font customfont = new Font("serif",Font.PLAIN,25);
	Object row [][] = {};
	String[] column = {"User ID","Online Sell ID","Sell Date","Total"};
	DefaultTableModel model = new DefaultTableModel(row,column);
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	Object rowdetails [][] = {};
	String[] columndetails = {"Medicine ID","Medicine Name","Medicine Type",
			"Mfg Date","Exp Date","Qty","Unit","Price(per unit)","Total"};
	DefaultTableModel modeldetails = new DefaultTableModel(rowdetails,columndetails);
	JTable tabledetails = new JTable(modeldetails);
	JScrollPane scrolldetails = new JScrollPane(tabledetails,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JPanel panelwest = new JPanel();
	JPanel panelcenter = new JPanel();
	JPanel panelcentercenter = new JPanel();
	JPanel panelcentersouth = new JPanel();
	
	JButton btndeliver = new JButton("Deliver");
	JButton btncancel = new JButton("Cancel");
	String storedid;
	
	public order() {
		setPreferredSize(new Dimension(1500,930));
		setLayout(new BorderLayout());
		cmp();
		btnAction();
	}


	private void btnAction() {
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = 1;
				String id= model.getValueAt(row,col).toString();
				storedid = id;
				tableDetailsDataLoad(id);
				
				
			}
		});
		btndeliver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DbConnection.connect();
					String query = "update tbonsellhistoryinfo set status='delivered' where onsellid='"+storedid+"'";
					DbConnection.sta.executeUpdate(query);
					String delete= "delete from tbonsellinfo where onsellid='"+storedid+"' ";
					DbConnection.sta.executeUpdate(delete);
					tableOrderDataLoad();
					tableDetailsDataLoad(null);
					storedid = null;
					DbConnection.con.close();
					
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(null, exp);
					
				}
				
			}
		});
		btncancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DbConnection.connect();
					String query = "update tbonsellhistoryinfo set status='canceled' where onsellid='"+storedid+"'";
					DbConnection.sta.executeUpdate(query);
					String delete= "delete from tbonsellinfo where onsellid='"+storedid+"' ";
					DbConnection.sta.executeUpdate(delete);
					changeStock();
					tableOrderDataLoad();
					tableDetailsDataLoad(null);
					storedid = null;
					DbConnection.con.close();
					
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(null, exp);
					
				}
				
			}
		});
		
	}
	private void changeStock() {
		for(int r = 0;modeldetails.getRowCount()>r;r++) {
			//int qty=  modelcart.getValueAt(r, 5);
			int qty = Integer.valueOf((String) modeldetails.getValueAt(r, 5));
			 
			 String mid = (String) modeldetails.getValueAt(r, 0);
			 Updateqty(qty,mid);}
		
	}
	public void Updateqty(int qty,String mid) {
		//qty update after sell 
		int a= getValue(mid);
		int b = qty;
		int value =a+b;
		
		try {
			System.out.print("Ordered qty = "+b+" ___");
			System.out.print("Stock qty = "+a+" ___");
			
			DbConnection.connect();
			String query ="update tbmedicinestock set qty='"+value+"' where"
					+ " medicineid='"+mid+"'";
			DbConnection.sta.executeUpdate(query);
			DbConnection.con.close();
			System.out.print("Updated qty = "+value+" ___");
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
	public void tableDetailsDataLoad(String id) {
		for(int a= tabledetails.getRowCount()-1;a>=0;a--) {
			modeldetails.removeRow(a);
		}
		try {
			DbConnection.connect();
			DbConnection.con.setAutoCommit(false);
			String query ="select MedicineID,MedicineName,MedicineType,MfgDate,ExpDate,"
					+ "Qty,Unit,Price,total from tbonselldetailsinfo where onsellid='"+id+"'";
			ResultSet rs =DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				modeldetails.addRow(new Object[] {rs.getString("MedicineID"),
						rs.getString("MedicineName"),
						rs.getString("MedicineType"),
						rs.getString("MfgDate"),
						rs.getString("ExpDate"),
						rs.getString("Qty"),
						rs.getString("Unit"),
						rs.getString("Price"),
						rs.getString("total")});
			}
			DbConnection.con.commit();
			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public void tableOrderDataLoad() {
		for(int a= table.getRowCount()-1;a>=0;a--) {
			model.removeRow(a);
		}
		try {
			DbConnection.connect();
			String query ="select userid,onsellid,selldate,total "
					+ "from tbonsellinfo order by selldate";
			ResultSet rs =DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("userid"),
						rs.getString("onsellid"),
						rs.getString("selldate"),
						rs.getString("total")});
			}
			DbConnection.con.close();

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}

	private void cmp() {
	
			add(panelwest,BorderLayout.WEST);
			panelwestwork();
			add(panelcenter,BorderLayout.CENTER);
			panelcenterwork();
			
		}

		private void panelwestwork() {
			//panelwest.setBackground(Color.blue);
			panelwest.setBorder(BorderFactory.createRaisedBevelBorder());
			panelwest.setPreferredSize(new Dimension(480,0));
			panelwest.add(scroll);
			scroll.setPreferredSize(new Dimension(470,900));
			
		}

		private void panelcenterwork() {
			panelcenter.setLayout(new BorderLayout());
			panelcenter.add(panelcentercenter,BorderLayout.CENTER);
			panelcentercenterwork();
			panelcenter.add(panelcentersouth,BorderLayout.SOUTH);
			panelcentersouthwork();
			
		}

		private void panelcentersouthwork() {
			//panelcentersouth.setBackground(Color.red);
			panelcentersouth.setBorder(BorderFactory.createRaisedBevelBorder());
			panelcentersouth.setPreferredSize(new Dimension(0,200));
			panelcentersouth.add(btndeliver);
			btndeliver.setPreferredSize(new Dimension(100,40));
			btndeliver.setBackground(Color.GREEN);
			btndeliver.setFont(new Font("serif",Font.PLAIN,20));
			panelcentersouth.add(btncancel);
			btncancel.setPreferredSize(new Dimension(100,40));
			btncancel.setBackground(Color.red);
			btncancel.setFont(new Font("serif",Font.PLAIN,20));
			
		}

		private void panelcentercenterwork() {
			//panelcentercenter.setBackground(Color.green);
			panelcentercenter.setBorder(BorderFactory.createRaisedBevelBorder());
			panelcentercenter.add(scrolldetails);
			scrolldetails.setPreferredSize(new Dimension(995,700));
			
		}
		
	
	

}
