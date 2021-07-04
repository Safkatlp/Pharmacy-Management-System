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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

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

public class Stock extends JPanel{
	Font customfont = new Font("serif",Font.PLAIN,25);
	Object row [][] = {};
	String[] column = {"Medicine ID","Medicine Name","Medicine Type",
			"Mfg Date","Exp Date","Qty","Unit","Price(per unit)","SupplierID"};
	DefaultTableModel model = new DefaultTableModel(row,column);
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JLabel lblmedicineid = new JLabel("Medicine ID");
	JTextField txtmedicineid = new JTextField(25);
	JButton btndelete = new JButton("Delete",new ImageIcon("images/delete.png"));
	
	JPanel panelsouth = new JPanel();
	JPanel panelcenter = new JPanel();
	
	
	public Stock() {
		//setBackground(Color.BLACK);
		setPreferredSize(new Dimension(1500,930));
		setLayout(new BorderLayout());
		
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelsouth,BorderLayout.SOUTH);
		panelsouthwork();
		btnAction();
	}
	private void autofill(String id) {
		txtmedicineid.setText(id);
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
				int row = table.getSelectedRow();
				int col = 0;
				String Id = model.getValueAt(row, col).toString();
				autofill(Id);
				
			}
		});
		btndelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FrequentlyUse.confirmation("Want to Delete?")) {
					if(FrequentlyUse.insertData(getinsertQuery())) {
						
						JOptionPane.showMessageDialog(null,"Medicine Deleted Successfully");
					}
					
				}
				tableDataLoad();
				setID();
			}
		});
	}
	private String getinsertQuery() {
		String query = "delete from tbmedicinestock where medicineid='"+txtmedicineid.getText().trim()+"'";
		return query;
	}
	public void setID() {
		txtmedicineid.setText("Med-");
	}
	private void panelcenterwork() {
		panelcenter.add(scroll);
		scroll.setPreferredSize(new Dimension(1440,780));
		
		
	}
	private void panelsouthwork() {
		//panelsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelsouth.setPreferredSize(new Dimension(0,135));
		panelsouth.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		panelsouth.add(lblmedicineid,c);
		lblmedicineid.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 0;
		panelsouth.add(txtmedicineid,c);
		
		c.gridx = 2;
		c.gridy = 0;
		panelsouth.add(btndelete,c);
		btndelete.setBackground(Color.red);
		btndelete.setForeground(Color.WHITE);
		btndelete.setFont(new Font("serif",Font.PLAIN,30));

		
	}
	public void tableDataLoad() {
		for(int a= table.getRowCount()-1;a>=0;a--) {
			model.removeRow(a);
		}
		try {
			DbConnection.connect();
			String query ="select MedicineID,MedicineName,MedicineType,MfgDate,ExpDate,Qty,"
					+ "Unit,Price,SupplierID from tbmedicinestock order by medicinename";
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

}
