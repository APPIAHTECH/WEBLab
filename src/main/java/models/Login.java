package models;

import managers.ManageUsers;

public class Login {

	private String user = "";
	private String pass = "";
	private int[] error = {0};
	private boolean[] errors = {false};
	ManageUsers manager;
	public Login()
	{
		manager = new ManageUsers();
		
	}
	
	public boolean validateLogin()
	{
		if( manager.DBLogin(this.getUser(), this.getPass())) return true;
		else
		{
			this.errors[0] = true;
			return false;
		}
		
	}
	public String getUser(){
		return user;
	}
	
	public String getPass(){
		return pass;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public int[] getError() {
		return error;
	}
	
	public boolean[] getErrors() {
		return errors;
	}
	
	
	public boolean isComplete() {
	    return(hasValue(getUser()) && hasValue(getPass()) );
	}
	
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	@Override
    public String toString() {
        return String.format( 
        		"Name: " + this.getUser()+
        		" Pass:  " + this.getPass()+ "\n"
        		);
    }
}