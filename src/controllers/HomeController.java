package controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController implements Initializable{
	
	@FXML public Button btncheck;
	@FXML public Button btnValidate;
	@FXML public TextField txtName;
	@FXML public TextField txtCode;
	@FXML public TextField lblClose;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}
	@FXML public void close(ActionEvent event){
		
		try{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML public void validate(ActionEvent event){ 
		
		
		Connection myConn = dbConnector.dbConnector();
		
		try{
			
			String query = "select * from branches where name = ? and branch_code = ?";
			
			PreparedStatement pst 	= (PreparedStatement) myConn.prepareStatement(query);
							
			pst.setString(1, txtName.getText());
			pst.setString(2, txtCode.getText());
			
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
				
				 Calendar rightNow = Calendar.getInstance();
				    int y = rightNow.get(Calendar.YEAR);
				    int m = rightNow.get(Calendar.MONTH) + 1;
				    int day = rightNow.get(Calendar.DAY_OF_MONTH);
				 String date = Integer.toString(y)+ " " + Integer.toString(m) + " " + Integer.toString(day);
				 
				 // to logs
				 UUID idOne = UUID.randomUUID();
			     String str=""+idOne;        
			     int uid=str.hashCode();
			     String filterStr=""+uid;
			     str=filterStr.replaceAll("-", "");
			        
				 String logs = "insert into poslogs (id, date, branch) values (?,?,?)";
				 PreparedStatement statement = (PreparedStatement) myConn.prepareStatement(logs);
				 statement.setString(1, str);
				 statement.setString(2, date);
				 statement.setString(3, txtName.getText());
				 statement.execute();
				 statement.close();
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
