package com.test.menuitem;

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

public class Addstuff extends JPanel{
	JLabel lblstuffid = new JLabel("ID");
	JLabel lblstuffname = new JLabel("Name");
	JLabel lblstuffage = new JLabel("Age");
	JLabel lblstuffmobile = new JLabel("Mobile No");
	JLabel lblstuffaddress = new JLabel("Address");
	JLabel lblstuffsalary = new JLabel("Salary");
	
	JTextField txtstuffid = new JTextField(20);
	JTextField txtstuffname = new JTextField(20);
	JTextField txtstuffage = new JTextField(20);
	JTextField txtstuffmobile = new JTextField(20);
	JTextField txtstuffaddress = new JTextField(20);
	JTextField txtstuffsalary = new JTextField(20);
	
	JPanel panelcenter = new JPanel();
	JPanel panelsouth = new JPanel();
	
	JButton btnsave = new JButton("save",new ImageIcon("images/save.png"));
	JButton btnreset = new JButton("reset",new ImageIcon("images/reset.png"));
	
	Font customfont = new Font("serif",Font.PLAIN,25);
	Font bordercustomfont = new Font("serif",Font.BOLD,30);
	
	public Addstuff() {
		//setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1500,930));
		setLayout(new BorderLayout());
		cmp();
		btnAction();
		
	}
	public void AutoId() {
		try {
			DbConnection.connect();
			
			String query = "select ifnull(max(cast(substring(StuffID,locate('-',StuffID)+1," + 
					"length(StuffID)-locate('-',StuffID))as UNSIGNED)),0)+1 id from tbstuffinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtstuffid.setText("Stuff-"+rs.getString("id"));
			}
			DbConnection.con.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private String getinsertQuery() {
		String query2 = "insert into tbstuffinfo(stuffid,stuffname,stuffage,stuffmobileno,stuffaddress,stuffsalary) values('"+txtstuffid.getText().trim()+"','"
				+txtstuffname.getText().trim()+"','"+txtstuffage.getText().trim()+"',"
						+ "'"+txtstuffmobile.getText().trim()+"','"+txtstuffaddress.getText().trim()+"',"
								+ "'"+txtstuffsalary.getText().trim()+"')";
		return query2;
	}
private void TextClear() {
		
		txtstuffid.setText("");
		txtstuffname.setText("");
		txtstuffage.setText("");
		txtstuffmobile.setText("");
		txtstuffaddress.setText("");
		txtstuffsalary.setText("");
		
		
	}

	private void btnAction() {
		btnsave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(FrequentlyUse.confirmation("Want to Save?")) {
					if(FrequentlyUse.insertData(getinsertQuery())) {
						
						JOptionPane.showMessageDialog(null,"Stuff Saved Successfully");
					}
					
				}
				
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
				(Color.BLACK, 3, true), "Stuff Details", 2, 3,bordercustomfont ));
		
		panelcenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		panelcenter.add(lblstuffid,c);
		lblstuffid.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 0;
		panelcenter.add(txtstuffid,c);
		
		c.gridx = 0;
		c.gridy = 1;
		panelcenter.add(lblstuffname,c);
		lblstuffname.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 1;
		panelcenter.add(txtstuffname,c);
		
		c.gridx = 0;
		c.gridy = 2;
		panelcenter.add(lblstuffage,c);
		lblstuffage.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 2;
		panelcenter.add(txtstuffage,c);
		
		c.gridx = 0;
		c.gridy = 3;
		panelcenter.add(lblstuffmobile,c);
		lblstuffmobile.setFont(customfont);

		c.gridx = 1;
		c.gridy = 3;
		panelcenter.add(txtstuffmobile,c);
		
		c.gridx = 0;
		c.gridy = 4;
		panelcenter.add(lblstuffaddress,c);
		lblstuffaddress.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 4;
		panelcenter.add(txtstuffaddress,c);
		
		c.gridx = 0;
		c.gridy = 5;
		panelcenter.add(lblstuffsalary,c);
		lblstuffsalary.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 5;
		panelcenter.add(txtstuffsalary,c);
		
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
