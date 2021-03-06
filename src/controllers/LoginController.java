package controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import application.dbConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController  extends HomeController implements Initializable {
	
	@FXML public Label lblCode;
	@FXML public Button btnLogin;
	@FXML public TextField txtusername;
	@FXML public TextField txtpassword;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblCode.setText(code);
		
	}

	@FXML public void login(ActionEvent event){ 
		
		
		Connection myConn = dbConnector.dbConnector();
		
		try{
			
			String query = "select * from branches where name = ? and branch_code = ?";
			
			PreparedStatement pst 	= (PreparedStatement) myConn.prepareStatement(query);
							
			pst.setString(1, txtusername.getText());
			pst.setString(2, txtpassword.getText());
			
			ResultSet rs = pst.executeQuery();
			
			int count = 0;
			while(rs.next()){
				count = count+1;
				
			}
			if (count == 1){
				JOptionPane.showMessageDialog(null, "Branch name and Code Validated!");
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
				Parent parentrecord = FXMLLoader.load(getClass().getClassLoader().getResource("\\view\\login.fxml"));
				Scene scenerecord = new Scene(parentrecord);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(scenerecord);
				Dimension d= Toolkit.getDefaultToolkit().getScreenSize(); // get screen size
				app_stage.show();
				app_stage.setX(d.width/2-(app_stage.getWidth()/2));
				app_stage.setY(d.height/2-(app_stage.getHeight()/2));
				
		
			}
			else if(count>1){
				JOptionPane.showMessageDialog(null, "Duplicate name and code!");
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Credentials!");
			}
			
			rs.close();
			pst.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	
	
	

}
