package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("player_add_page.fxml"));
			Scene scene = new Scene(page,600,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	

	public static Connection getConnection(){
		System.out.println("Establishing JDBC connection .......");
		
		//making connection to oracle
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Check The Location Of JDBC?");
			e.printStackTrace();
		}
		System.out.println("JDBC Connected");
		
		Connection connection=null;
		
		try {
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "C##BMG", "bmg");
		} catch (SQLException e) {
			System.out.println("Check The Connection");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Working Fine Returning Connection");
			return connection;
		}else{
			System.out.println("Connection not fine");
		}
		return connection;
	}	//----------------------------------> getConnection ENDS, return connection
	
}
