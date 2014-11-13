package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.sun.crypto.provider.RSACipher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PlayerAddPage implements Initializable{

	//write code here
	
	@FXML
	private Button updateInfo;
	@FXML
	private TextField teamName;
	private String TeamName;
	@FXML
	private TextField nationality;
	private String Nationality;
	@FXML
	private TextField firstName;
	private String FirstName;
	@FXML
	private TextField lastName;
	private String LastName;
	@FXML
	private DatePicker datePicker;
	
	private String DateOfBirth;	
	@FXML
	private TextField position;
	private String Position;
	@FXML
	private TextField jerseyNo;
	private int JerseyNo;
	@FXML
	private TextField attacking;
	private int Attacking;
	@FXML
	private TextField defending;
	private int Defending;
	@FXML
	private TextField goalKeeping;
	private int GoalKeeping;
	@FXML
	private TextField shortPassing;
	private int ShortPassing;
	@FXML
	private TextField longPassing;
	private int LongPassing;
	@FXML
	private TextField crossing;
	private int Crossing;
	@FXML
	private TextField speed;
	private int Speed;
	@FXML
	private TextField stamina;
	private int Stamina;
	@FXML
	private TextField tackling;
	private int Tackling;
	@FXML
	private TextField power;
	private int Power;
	@FXML
	private TextField accuracy;
	private int Accuracy;
	@FXML
	private TextField luck;
	private int Luck;
	
	
	public void UpdateInfoClicked(javafx.event.ActionEvent action) {

		
		String userId="wajeeh1";
		global.setUserName(userId);
		//get values from controls
		TeamName=teamName.getText();
		Nationality=nationality.getText();
		FirstName=firstName.getText();
		LastName=lastName.getText();
		
		
		
		// Get all the values from the fields
		DateOfBirth=datePicker.getValue().toString().trim();
		Position=position.getText().trim();
		JerseyNo=Integer.parseUnsignedInt(jerseyNo.getText().trim());
		Attacking=Integer.parseUnsignedInt(attacking.getText().trim());
		Defending=Integer.parseUnsignedInt(defending.getText().trim());
		GoalKeeping=Integer.parseUnsignedInt(goalKeeping.getText().trim());
		ShortPassing=Integer.parseUnsignedInt(shortPassing.getText().trim());
		LongPassing=Integer.parseUnsignedInt(longPassing.getText().trim());
		Crossing=Integer.parseUnsignedInt(crossing.getText().trim());
		Speed=Integer.parseUnsignedInt(speed.getText().trim());
		Stamina=Integer.parseUnsignedInt(stamina.getText().trim());
		Tackling=Integer.parseUnsignedInt(tackling.getText().trim());
		Power=Integer.parseUnsignedInt(power.getText().trim());
		Accuracy=Integer.parseUnsignedInt(accuracy.getText().trim());
		Luck=Integer.parseUnsignedInt(luck.getText().trim());
		
		
		
		//check ranges of every field if true are returned then do the
		if(checkRange(JerseyNo)&&checkRange(Attacking)&&checkRange(Defending)&&checkRange(GoalKeeping)){
			if(checkRange(ShortPassing)&&checkRange(LongPassing)&&checkRange(Crossing)&&checkRange(Speed)){
				if(checkRange(Stamina)&&checkRange(Tackling)&&checkRange(Power)&&checkRange(Accuracy)){
					if(checkRange(Luck)){
						
						
						//make query to get all the players of a team of a particular user
						
						String checkQuery= "select * from FBM_PLAYER_TABLE_STATS where"+
						" team_id= (select TEAM_ID from FBM_TABLE_TEAM_ID where ='"+ global.getUserName() +"') AND JERSEY_NO=" +
								JerseyNo + " AND USER_ID='" + global.getUserName() + "'";
								

						System.out.println(checkQuery);
						
						
					
						
						
						//If ends where all values have been checked
					}
				}
			}
		}
		
		
		
		// team name lia hai yahan text field se
		System.out.println(teamName.getText().trim());
		
		//text field se value li hui show ho gi yahan
		System.out.println(TeamName + " " + Nationality + " " + FirstName + " " + LastName + " " + DateOfBirth + " " + 
		Position + " " + JerseyNo + " " + Attacking + " " + Defending + " " + GoalKeeping + " " + ShortPassing + " " + 
		LongPassing + " " + Crossing + " " + Speed + " " + Stamina + " " + Tackling + " " + Power + " " + 
		Accuracy + " " + Luck);
		
		
		// Kisi user kay di huwi team k total player count karne k liye query hai ye
		String query= "select PLAYER_ID from FBM_PLAYER_TABLE_STATS where TEAM_ID=(select TEAM_ID from FBM_TABLE_TEAM_ID" + 
		" where TEAM_NAME='" + TeamName.toUpperCase() + "' ) AND USER_ID='" + global.getUserName() + "'";
		
		System.out.println(query);
		
		
		// java ka oracle database se connection lia hai yahan
		Connection conn=global.getConnection();
		
		//is variable me total count karna hai
		int totalCount=0;
		
		
		Statement st;
		try {
			
			//Statement se SQL query execute hota hai
			st = conn.createStatement();
			//result set me query execute karwatay hai
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				totalCount++;
			}
			rs.close();
			// If Total Count is less than 30 we do our work
			if(totalCount<30){
				
				
				System.out.println("Count is less than 30, do the job here");
				
				
				// is query se dekhna hai k player exist karta hai ya nahi
				// check if player exist for current user with user_id jersey_no team_id also if exist then update else insert
				String checkQuery= "select * from FBM_PLAYER_TABLE_STATS where team_id=(select TEAM_ID from FBM_TABLE_TEAM_ID "+
				"where TEAM_NAME='" + TeamName.toUpperCase() + "') AND JERSEY_NO=" +
				JerseyNo + " AND USER_ID='" + global.getUserName() + "'";
				System.out.println(checkQuery);
				
				
				// result set me result lena hai
				ResultSet rs1=conn.createStatement().executeQuery(checkQuery);
				
				//Iss query se team id mil jaye gi
				String teamQuery="select TEAM_ID from FBM_TABLE_TEAM_ID where TEAM_NAME='"+
				TeamName.toUpperCase()+"'";
				
				//is me result store karwaye gay
				ResultSet rs2=conn.createStatement().executeQuery(teamQuery);
				String obtTeamId=null;
				
				// obtTeamId me team_id store karwaye gay
				while(rs2.next()){
					obtTeamId=rs2.getString("TEAM_ID").toUpperCase();
				}
				
				System.out.println("here I am and Team ID is " + obtTeamId);
				
				
				int playerCount=0;
				
//				if(rs1.isLast()){
//					System.out.println("last result");
//				}
				
				
				//Check if player exist
				while(rs1.next()){
					playerCount++;
				}
				
				System.out.println(playerCount);
				
				String updateQuery =null;
				
				//insert player here
				if(playerCount<1){
					
					/*
	INSERT INTO "C##BMG"."FBM_PLAYER_TABLE_STATS" (PLAYER_ID, TEAM_ID, ATTACKING, 
	DEFENDING, GOAL_KEEPING, SHORT_PASSING, LONG_PASSING, CROSSING, SPEED, STAMINA, 
	TACKLING, POWER, ACCURACY, LUCK, FIRST_NAME, LAST_NAME, USER_ID, NATIONALITY, JERSEY_NO, 
	DATE_OF_BIRTH, POSITION) VALUES ('ESP-11', 'ESP', '79', '80', '50', '76', '85', '80', '90', 
	'80', '79', '85', '90', '5', 'Bojan', 'Kirkic', 'wajeeh1', 'Spanish', '11', TO_DATE('22-MAR-92', 
	'DD-MON-RR'), 'CAM')

					*/


					
					// agar player exist nahi karta jersey number match honay k bad to insert karwa day gay
					System.out.println("In insert method");
					updateQuery="insert into FBM_PLAYER_TABLE_STATS (PLAYER_ID, TEAM_ID, ATTACKING, DEFENDING, GOAL_KEEPING" +
						", SHORT_PASSING, LONG_PASSING, CROSSING, SPEED, STAMINA, TACKLING, POWER, ACCURACY, LUCK, FIRST_NAME, LAST_NAME, " 
						+"USER_ID, NATIONALITY, JERSEY_NO, DATE_OF_BIRTH, POSITION) values ('" + obtTeamId + "-"+ JerseyNo +"',"
						+ "'"+obtTeamId+"', '" + Attacking + "', '"
						+ Defending + "', '"	+ GoalKeeping + "', '" +ShortPassing + "', '"	+ LongPassing + "', '" + Crossing + 
						"', '" + Speed + "', '" + Stamina + "', '" + Tackling + "', '" + Power + "', '" + Accuracy +
						"', '" + Luck + "', '" +FirstName+"', '"+LastName+"' , '"+global.getUserName()+ "', '" + Nationality +"', '"+JerseyNo+"',"+ 
						"TO_DATE('" + DateOfBirth + "', 'RRRR-MM-DD')" + ", '" + Position + "')";

					System.out.println(updateQuery);
					
				}else{
					
					
				//update player here
					
				//sequence
				//player_id, team_id, attacking, defending, goal_keeping, short_passing,
				//long_passing, crossing, speed, stamina, tackling, power, accuracy, luck
				//first_name, last_name, user_id, nationality, jersey_no, date_of_birth
				//position
					
				System.out.println("In update method");
				
				
				
				//Updating player according to his jersey number and user ID
				
				//agar player exist karta hai to uss jersey number walay player ko update kar dain gay
				updateQuery="update FBM_PLAYER_TABLE_STATS set ATTACKING='" + Attacking + "', " + "DEFENDING='" +
				Defending+"', " + "GOAL_KEEPING='" +GoalKeeping+ "', " + "SHORT_PASSING='" + ShortPassing + "', " +
				"LONG_PASSING='" + LongPassing + "' " + ",CROSSING='" + Crossing + "', SPEED='" + Speed + "', " +
				"STAMINA='" + Stamina +"', TACKLING='" + Tackling + "', POWER='" + Power + "', " + "ACCURACY='" +
				Accuracy + "', " + "LUCK='" + Luck + "', " + "FIRST_NAME='" + FirstName + "', " + "LAST_NAME='" + LastName + "'"
				 + " where JERSEY_NO='" + JerseyNo + "' AND " +  "USER_ID='" + global.getUserName() + "' AND" + " TEAM_ID='"
				 + obtTeamId + "'";
				
				System.out.println(updateQuery);
			
				}
				
				System.out.println(updateQuery);
				conn.createStatement().executeUpdate(updateQuery);
				
				
				//ResultSet rs2=executeStatement(updateQuery);
				
				//---------------->>>>>>>>>>> Player Inserted Or Updated Accordingly
			
			}else{
				st.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}	//----------------------------------------> Button Click Ends Here
	
	/*
	 * INSERT INTO "C##BMG"."FBM_PLAYER_TABLE_STATS" (PLAYER_ID, TEAM_ID, ATTACKING, DEFENDING, 
	 * GOAL_KEEPING, SHORT_PASSING, LONG_PASSING, CROSSING, 
	 * SPEED, STAMINA, TACKLING, POWER, ACCURACY, LUCK, FIRST_NAME, 
	 * LAST_NAME, USER_ID, NATIONALITY, JERSEY_NO, DATE_OF_BIRTH, POSITION) 
	 * VALUES ('ESP-6', 'ESP', '80', '80', '50', '89', '89', 
	 * '86', '80', '85', '80', '86', '90', '7', 'Cesc', 'Fabregas', 
	 * 'wajeeh1', 'Spanish', '6', TO_DATE('22-jun-92', 'DD-MON-RR'), 'CF')

	 */
	
	
	
	
	
	
	private ResultSet executeStatement(String updateQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		datePicker.setUserData(LocalDate.of(1992, 06, 22));
		
	}
	
	public boolean checkRange(int value){
		if(0<value&&value<100){
			return true;
		}else{
			return false;
		}
		
	}


	
}
