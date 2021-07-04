package com.test.menuitem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.test.admin.DbConnection;

public class onSellReport extends JPanel{
	Font customfont = new Font("serif",Font.PLAIN,25);
	Font bordercustomfont = new Font("serif",Font.BOLD,30);
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
	
	Object rowuser[][];
	String[] columnuser= {"User ID","User Name","User Mobile",
			"User Address"};
	DefaultTableModel modeluser = new DefaultTableModel(rowuser,columnuser);
	JTable tableuser = new JTable(modeluser);
	JScrollPane scrolluser = new JScrollPane(tableuser,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JPanel panelwest = new JPanel();
	JPanel panelcenter = new JPanel();
	JPanel panelcentercenter = new JPanel();
	JPanel panelcentersouth = new JPanel();
	
	JLabel lblorderstatus = new JLabel("Order Status");
	JTextField txtorderstatus = new JTextField(20);
	
	public onSellReport() {
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
				int col2=0;
				String userid = model.getValueAt(row, col2).toString();
				tableDetailsDataLoad(id);
				tableUserDataLoad(userid);
				
				
			}
		});
		
	}
	public void tableUserDataLoad(String userid) {
		for(int a= tableuser.getRowCount()-1;a>=0;a--) {
			modeluser.removeRow(a);
		}
		try {
			DbConnection.connect();
			DbConnection.con.setAutoCommit(false);
			String query ="select UserID,Username,mobileno,address "
					+ "from tbuserinfo where userId='"+userid+"'";
			ResultSet rs =DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				modeluser.addRow(new Object[] {rs.getString("UserID"),
						rs.getString("Username"),
						rs.getString("mobileno"),
						rs.getString("address")
						});
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
					+ "from tbonsellhistoryinfo where status = 'delivered' order by selldate";
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
			panelcentersouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
					(Color.BLACK, 3, true), "Purchased By", 1, 2,bordercustomfont ));
			panelcentersouth.add(scrolluser);
			scrolluser.setPreferredSize(new Dimension(1000,100));
			
		}

		private void panelcentercenterwork() {
			//panelcentercenter.setBackground(Color.green);
			panelcentercenter.setBorder(BorderFactory.createRaisedBevelBorder());
			panelcentercenter.add(scrolldetails);
			scrolldetails.setPreferredSize(new Dimension(995,700));
			
		}
		
	
	



}
