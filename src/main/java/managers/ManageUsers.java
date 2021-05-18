package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public void addUser(String name, String mail, String pwd, String date, String gender) {
		String query = "INSERT INTO users (usr,mail,pwd, gender, date) VALUES (?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,name);
			statement.setString(2,mail);
			statement.setString(3,pwd);
			statement.setString(4,gender);
			statement.setString(5,date);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) &&
	           hasValue(user.getPwd2()) ) &&
	    		hasValue(user.getGender());
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	//Search users in db
	public boolean getUserDB(String user) {
		String query = "SELECT usr FROM users WHERE usr LIKE ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, "%" + user + "%");
			ResultSet rs = statement.executeQuery();
			
			System.out.print(statement);
			while(rs.next()) {
				String name = rs.getString("usr");
				if (user.equals(name)) {
					statement.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// TODO: add other methods 
	//CRUD Operations, update already done
}
