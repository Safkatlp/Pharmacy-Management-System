package com.test.menuitem;

import com.test.menuitem.Addmedicine;
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

public class Addsupplier extends JPanel{
	JLabel lblsupplierid = new JLabel("ID");
	JLabel lblsuppliername = new JLabel("Name");
	JLabel lblsuppliermobile = new JLabel("Mobile No");
	JLabel lblsupplieraddress = new JLabel("Address");
	
	
	JTextField txtsupplierid = new JTextField(20);
	JTextField txtsuppliername = new JTextField(20);
	JTextField txtsuppliermobile = new JTextField(20);
	JTextField txtsupplieraddress = new JTextField(20);
	
	
	JPanel panelcenter = new JPanel();
	JPanel panelsouth = new JPanel();
	
	JButton btnsave = new JButton("save",new ImageIcon("images/save.png"));
	JButton btnreset = new JButton("reset",new ImageIcon("images/reset.png"));
	
	Font customfont = new Font("serif",Font.PLAIN,25);
	Font bordercustomfont = new Font("serif",Font.BOLD,30);
	
	Addmedicine medicine = new Addmedicine();
	
	public Addsupplier() {
		//setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1500,930));
		setLayout(new BorderLayout());
		cmp();
		btnAction();
		
	}
	public void AutoId() {
		try {
			DbConnection.connect();
			
			String query2 = "select ifnull(max(cast(substring(SupplierID,\r\n" + 
					"locate('-',SupplierID)+1,length(SupplierID)-locate('-',SupplierID))as UNSIGNED)),0)+1 id from tbsupplierinfo";
			ResultSet rs2 = DbConnection.sta.executeQuery(query2);
			while(rs2.next()) {
				txtsupplierid.setText("Sup-"+rs2.getString("id"));
			}
			DbConnection.con.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private String getinsertQuery() {
		String query2 = "insert into tbsupplierinfo(supplierid,suppliername,suppliermobileno,supplieraddress) values('"+txtsupplierid.getText().trim()+"','"
				+txtsuppliername.getText().trim()+"','"+txtsuppliermobile.getText().trim()+"','"+txtsupplieraddress.getText().trim()+"')";
		return query2;
	}

	private void TextClear() {
		
		txtsupplieraddress.setText("");
		txtsupplierid.setText("");
		txtsuppliermobile.setText("");
		txtsuppliername.setText("");
		
		
	}
	
	private void btnAction() {
		btnsave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(FrequentlyUse.confirmation("Want to Save?")) {
					if(FrequentlyUse.insertData(getinsertQuery())) {
						
						JOptionPane.showMessageDialog(null,"Supplier Saved Successfully");
					}
					
				}
				medicine.supplierid();
				TextClear();
				AutoId();
				
			
				
			}
		});
		btnreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				TextClear();
				AutoId();
			
				
			}
		});
	}

	private void cmp() {
		add(panelcenter,BorderLayout.CENTER);
		panelcenterwork();
		add(panelsouth,BorderLayout.SOUTH);
		panelsouthwork();
		
		
	}

	private void panelcenterwork() {
		panelcenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Supplier Details", 2, 3,bordercustomfont ));
		panelcenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		panelcenter.add(lblsupplierid,c);
		lblsupplierid.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 0;
		panelcenter.add(txtsupplierid,c);
		
		c.gridx = 0;
		c.gridy = 1;
		panelcenter.add(lblsuppliername,c);
		lblsuppliername.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 1;
		panelcenter.add(txtsuppliername,c);
		
		
		
		c.gridx = 0;
		c.gridy = 2;
		panelcenter.add(lblsuppliermobile,c);
		lblsuppliermobile.setFont(customfont);

		c.gridx = 1;
		c.gridy = 2;
		panelcenter.add(txtsuppliermobile,c);
		
		c.gridx = 0;
		c.gridy = 3;
		panelcenter.add(lblsupplieraddress,c);
		lblsupplieraddress.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 3;
		panelcenter.add(txtsupplieraddress,c);
		
		
	}
	private void panelsouthwork() {
		panelsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelsouth.setPreferredSize(new Dimension(0,150));
		FlowLayout flow = new FlowLayout();
		panelsouth.setLayout(flow);
		
		flow.setHgap(15);
		
		panelsouth.add(btnreset);
		btnreset.setBackground(Color.red);
		btnreset.setForeground(Color.WHITE);
		btnreset.setFont(new Font("serif",Font.PLAIN,30));
		
		panelsouth.add(btnsave);
		btnsave.setBackground(Color.red);
		btnsave.setForeground(Color.WHITE);
		btnsave.setFont(new Font("serif",Font.PLAIN,30));
	}


}
