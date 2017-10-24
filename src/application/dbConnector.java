package application;
import java.sql.*;
import javax.swing.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class dbConnector {
	Connection conn = null;
	public static Connection dbConnector() {
		
		String host 	= "jdbc:mysql://localhost:3306/cims";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(host, username, password);
			JOptionPane.showMessageDialog(null, "Processing ...");
		    return conn;
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Make sure database is active or contact system administrator for support.");
			return null;
		}
		
		
	}
	
		
	}


