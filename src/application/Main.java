package application;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	
	
	public static void main(String[] args) {
		//connection to ms sql 2012 
		Connection myConn = null ;
		myConn=dbConnector.dbConnector();
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		  	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("\\view\\frmHome.fxml"));
		  	primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.setTitle("Branch Validator");
	        primaryStage.setScene(new Scene(root));
	        
	        //display the window on the center of the screen
	        Dimension d= Toolkit.getDefaultToolkit().getScreenSize(); // get screen size
	        primaryStage.show();
	        primaryStage.setX(d.width/2-(primaryStage.getWidth()/2));
	        primaryStage.setY(d.height/2-(primaryStage.getHeight()/2));
	}
	
	
}
