package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class global {

	private static String userName;
	
	public static String getUserName(){
		return userName;
	}
	
	public static void setUserName(String UserName){
		userName=UserName;
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
