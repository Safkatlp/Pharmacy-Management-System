package com.test.main;
import java.sql.*;
import javax.swing.UIManager;

import javax.swing.JOptionPane;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;

import com.test.admin.DbConnection;
import com.test.admin.LoginFrame;


public class MainClass {
	
	public static void main(String args[]) {
		
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("fully qualified name of look and feel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());////////////////prior1
			//UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());///////prior 2
			//UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
			
			LoginFrame login = new LoginFrame();
			DbConnection.connect();
			
			
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		

	}
}
