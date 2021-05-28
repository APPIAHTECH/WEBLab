package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import managers.ManageUsers;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	ManageUsers manager;
	
	 // digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    
    private static final String FRONT_END_PASSWORD_PATTERN  = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
    
    private static final Pattern pattern = Pattern.compile(FRONT_END_PASSWORD_PATTERN);
    
	private String user = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	
	//Date
	private String date = "";
	private String gender = "male";
	private String error_msg = "";
	private boolean[] error  = {false,false,false,false};
	
	public User() {
		manager = new ManageUsers();
	}
	
	public String getDate() { return this.date;}
	public String getGender() { return this.gender;}
	public String gettErrorMsg() { return this.error_msg;}
	
	public void setDate(String date) { this.date = date;}
	public void setGender(String gender) { this.gender = gender;}
	public void setErrorMsg(String err) { this.error_msg = err;}
	
	
    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		//error[0] = true;
		
		if(!this.userInDB(user)) this.user = user;
		else 
		{
			this.setErrorMsg("This user name has been already registered");
			error[0] = true;
		}
			
		//System.out.println(user);
	}
	
	public boolean userInDB(String user)
	{
		//Check if user is the DB
		return manager.checkUserDB(user);
	}
	
	public boolean userMailInDB(String mail)
	{
		//Check if email is the DB
		return manager.checkEmailDB(mail);
	}
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			
			//Check if email in db
			if(!this.userMailInDB(mail)) this.mail = mail;
			else 
			{
				this.setErrorMsg("This email has been already registered");
				error[1] = true;
			}
			
		} else {
			
			this.setErrorMsg("This user email incorect");
			error[1]=true;
		}
		
	}
	
	public String getPwd1() {
		return this.pwd1;
	}
	
	/***
	 * Secure Password requirements
	 * Password must contain at least one digit [0-9].
	 * Password must contain at least one lowercase Latin character [a-z].
	 * Password must contain at least one uppercase Latin character [A-Z].
	 * Password must contain at least one special character like ! @ # & ( ).
	 * Password must contain a length of at least 8 characters and a maximum of 20 characters.
	 * @param pwd1
	 */
	public void setPwd1(String pwd1) {
		/* TODO check restriction with pattern */
		
		if(User.isValid(pwd1)) this.pwd1 = pwd1;
		else 
		{
			this.setErrorMsg("First Password doesn't match requirements");
			this.error[2] = true;
		}
		//System.out.println(pwd1);
	}
	
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		/* TODO check restriction with pattern and check if pwd1=pwd2*/
		if (User.isValid(pwd2) && this.getPwd1().equals(pwd2)) this.pwd2 = pwd2;
		else 
		{
			this.setErrorMsg( "Second Password doesn't match requirements");
			this.error[3] = true;
		}
			
		//System.out.println(pwd2);
	}
	
	public boolean[] getError() {
		return error;
	}
	
	public void setError(boolean[] error) {
		this.error = error;
	}
	
	@Override
    public String toString() {
        return String.format( 
        		"Name: " + this.getUser()+
        		" Mail:  " + this.getMail() +
        		" Pass: " +this.getPwd1()
        		+ " Date: " + this.getDate() 
        		+ " Gender: " + this.getGender() + "\n"
        		);
    }
		
}
