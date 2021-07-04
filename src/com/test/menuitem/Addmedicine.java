 package com.test.menuitem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.test.admin.DbConnection;
import com.test.admin.FrequentlyUse;
import com.test.admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class Addmedicine extends JPanel{
	Font customfont = new Font("serif",Font.PLAIN,25);
	Font bordercustomfont = new Font("serif",Font.BOLD,30);
	JLabel lbl = new JLabel("Medicine Info");
	
	JPanel panelcenter = new JPanel();
	JPanel panelsouth = new JPanel();
	JPanel panelsouthcenter = new JPanel();
	JPanel panelsouthsouth = new JPanel();
	
	JLabel lblmedicineid = new JLabel("ID");
	JLabel lblmedicinename = new JLabel("Name");
	JLabel lblmedicinetype = new JLabel("Type");
	JLabel lblmedicinemfgdate = new JLabel("Mfg Date");
	JLabel lblmedicineexpdate = new JLabel("Exp Date");
	JLabel lblmedicineqty = new JLabel("Quantity");
	JLabel lblmedicineunit = new JLabel("Unit");
	JLabel lblmedicineprice = new JLabel("Price(per unit)");
	//JLabel lblperunit = new JLabel("(per unit)");
	
	JLabel lblsupplierid = new JLabel("ID");
	
	
	
	JTextField txtmedicineid = new JTextField(20);
	JTextField txtmedicineqty = new JTextField(20);
	JTextField txtmedicineunit = new JTextField(20);
	JTextField txtmedicineprice = new JTextField(20);
	
	JDateChooser mfgdate = new JDateChooser();
	JDateChooser expdate = new JDateChooser();
	
	SuggestText medicinenamesuggest = new SuggestText();
	SuggestText medtypesuggest = new SuggestText();
	SuggestText supplieridsuggest = new SuggestText();
	
	
	JLabel lblback = new JLabel(new ImageIcon("images/a.jpg"));
	JButton btnsave = new JButton("save",new ImageIcon("images/save.png"));
	JButton btnreset = new JButton("reset",new ImageIcon("images/reset.png"));
	
	
	public Addmedicine() {
	    //setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1500,930));
		//add(lblback);
		setLayout(new BorderLayout());
		
		cmp();
		btnAction();
	}
	public void AutoId() {
		try {
			DbConnection.connect();
			String query = "select ifnull(max(cast(substring(MedicineID,locate('-',MedicineID)+1,"
					+ "length(MedicineID)-locate('-',MedicineID))as UNSIGNED)),0)+1 id from tbmedicinestock";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			while(rs.next()) {
				txtmedicineid.setText("Med-"+rs.getString("id"));
			}
			
			DbConnection.con.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private String getinsertQuery() {
		String query = "insert into tbmedicinestock(medicineid,medicinename,medicinetype,mfgdate,expdate,qty,unit,price,supplierid) values('"+txtmedicineid.getText().trim()+"','"
				+medicinenamesuggest.txtSuggest.getText().trim()+"','"+medtypesuggest.txtSuggest.getText().trim()+"','"
				+new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.getDate()).trim()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(expdate.getDate()).trim()+"',"
				+ "'"+txtmedicineqty.getText().trim()+"','"+txtmedicineunit.getText().trim()+"',"
				+ "'"+txtmedicineprice.getText().trim()+"','"+supplieridsuggest.txtSuggest.getText().trim()+"')";
		return query;
	}
	
	private void TextClear() {
		txtmedicineid.setText("");
		medicinenamesuggest.txtSuggest.setText("");
		txtmedicineprice.setText("");
		txtmedicineqty.setText("");
		txtmedicineunit.setText("");
		
		mfgdate.setDate(new Date());
		expdate.setDate(new Date());
		medtypesuggest.txtSuggest.setText("");
		
		
	}
	
	private void btnAction() {
		btnsave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(FrequentlyUse.confirmation("Want to Save?")) {
					if(FrequentlyUse.insertData(getinsertQuery())) {
						
						JOptionPane.showMessageDialog(null,"Medicine Saved Successfully");
					}
					
				}
				TextClear();
				AutoId();
				medicinename();
				
				
				
				/*try {
					DbConnection.connect();
					String query = "insert into tbmedicinestock values('"+txtmedicineid.getText().trim()+"','"
							+txtmedicinename.getText()+"','"+suggest.txtSuggest.getText()+"','"
							+new SimpleDateFormat("yyyy-MM-dd").format(mfgdate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(expdate.getDate())+"',"
							+ "'"+txtmedicineqty.getText()+"','"+txtmedicineunit.getText()+"',"
							+ "'"+txtmedicineprice.getText()+"','"+txtsuppliername.getText()+"')";
					String query2 = "insert into tbsupplierinfo values('"+txtsupplierid.getText()+"','"
							+txtsuppliername.getText()+"','"+txtsuppliermobile.getText()+"','"+txtsupplieraddress.getText()+"')";
					
					int count = DbConnection.sta.executeUpdate(query);
					int count2 = DbConnection.sta.executeUpdate(query2);
					JOptionPane.showMessageDialog(null,"Added Succesfully");
					DbConnection.con.close();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}*/
				
				
			}
		});
			btnreset.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
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
				(Color.BLACK, 3, true), "Medicine Details", 2, 3,bordercustomfont ));
		panelcenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		panelcenter.add(lblmedicineid,c);
		lblmedicineid.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 0;
		panelcenter.add(txtmedicineid,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		panelcenter.add(lblmedicinename,c);
		lblmedicinename.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 1;
		panelcenter.add(medicinenamesuggest.cmbSuggest,c);
		medicinename();
		
		
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicinetype,c);
		lblmedicinetype.setFont(customfont);
		
		
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(medtypesuggest.cmbSuggest,c);
		medicinetype();
		
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicinemfgdate,c);
		lblmedicinemfgdate.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(mfgdate,c);
		mfgdate.setDateFormatString("dd-MM-yyyy");
		
		
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicineexpdate,c);
		lblmedicineexpdate.setFont(customfont);
		
		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(expdate,c);
		expdate.setDateFormatString("dd-MM-yyyy");
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicineqty,c);
		lblmedicineqty.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(txtmedicineqty,c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(5,55,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicineunit,c);
		lblmedicineunit.setFont(customfont);
		
		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(txtmedicineunit,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblmedicineprice,c);
		lblmedicineprice.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(txtmedicineprice,c);
		
		/*c.gridx = 4;
		c.gridy = 4;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelcenter.add(lblperunit);*/
		
		
		
	}
	
	public void medicinetype() {
		medtypesuggest.v.clear();
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
		medtypesuggest.v.add("Saline");
		medtypesuggest.v.add("Tape");
	}
	public void medicinename() {
		try {
			DbConnection.connect();
			String query = "select distinct MedicineName from tbmedicinestock";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			medicinenamesuggest.v.clear();
			while(rs.next()) {
				medicinenamesuggest.v.add(rs.getString("MedicineName"));
			}
			
			DbConnection.con.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}

	private void panelsouthwork() {
		panelsouth.setLayout(new BorderLayout());
		panelsouth.add(panelsouthcenter,BorderLayout.CENTER);
		panelsouthcenterwork();
		panelsouth.add(panelsouthsouth,BorderLayout.SOUTH);
		panelsouthsouthwork();
		
		
	}

	private void panelsouthcenterwork() {

		panelsouthcenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
				(Color.BLACK, 3, true), "Supplied By", 2, 3,bordercustomfont ));
		panelsouthcenter.setPreferredSize(new Dimension(0,370));
		panelsouthcenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouthcenter.add(lblsupplierid,c);
		lblsupplierid.setFont(customfont);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panelsouthcenter.add(supplieridsuggest.cmbSuggest,c);
		supplierid();
		
	}
	public void supplierid() {
		try {
			DbConnection.connect();
			String query = "select distinct supplierid from tbsupplierinfo";
			ResultSet rs = DbConnection.sta.executeQuery(query);
			supplieridsuggest.v.clear();
			while(rs.next()) {
				supplieridsuggest.v.add(rs.getString("supplierid"));
			}
			
			DbConnection.con.close();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	private void panelsouthsouthwork() {
		panelsouthsouth.setBorder(BorderFactory.createRaisedBevelBorder());
		panelsouthsouth.setPreferredSize(new Dimension(0,100));
		FlowLayout flow = new FlowLayout();
		panelsouthsouth.setLayout(flow);
		flow.setHgap(15);
		
		panelsouthsouth.add(btnreset);
		btnreset.setBackground(Color.red);
		btnreset.setForeground(Color.WHITE);
		btnreset.setFont(new Font("serif",Font.PLAIN,30));
		
		panelsouthsouth.add(btnsave);
		btnsave.setBackground(Color.red);
		btnsave.setForeground(Color.WHITE);
		btnsave.setFont(new Font("serif",Font.PLAIN,30));
		
		
	}
	

}
