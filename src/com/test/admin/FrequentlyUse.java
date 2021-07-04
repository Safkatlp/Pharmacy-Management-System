package com.test.admin;

import javax.swing.JOptionPane;

public class FrequentlyUse {
	public static boolean confirmation(String caption) {
		int a = JOptionPane.showConfirmDialog(null,caption,"Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
		if(a==JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	public static boolean insertData(String query) {
		try {
			DbConnection.connect();
			DbConnection.sta.executeUpdate(query);
			//DbConnection.con.close();
			return true;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public static boolean deleteData(String query) {
		try {
			DbConnection.connect();
			DbConnection.sta.executeUpdate(query);
			//DbConnection.con.close();
			return true;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
}
