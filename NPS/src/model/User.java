package model;

public class User {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String userType;
	public User(String anEmail, String aPassword, String aFName, 
			    String aLName, String anUserType) {		
		email = anEmail;
		password = aPassword;
		firstName = aFName;
		lastName = aLName;
		userType = anUserType;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	/**
	   * Gets the user's last name.
	   * This allows for displaying of the user's last name.
	   * @param None.
	 * @return 
	   * @return lastName The user's last name.
	   */
	protected String getLastName() {
		return lastName;
	}
	
	/**
	   * Gets the user's first name.
	   * This allows for displaying of the user's first name.
	   * @param None.
	 * @return 
	   * @return firstName The user's first name.
	   */
	protected String getFirstName() {
		return firstName;
	}
	public String getUserType(){
		return userType;
	}
//    void viewSumAllJobs();
//    void viewJobDetails();
//    void printVolunteers();
//	String getSimpleName();
}