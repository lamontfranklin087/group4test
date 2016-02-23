package model;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Login menu, read and write from/to text files.
 * @author Ihar Lavor
 * @author Lamont Franklin
 * @version 02/06/2016
 * 
 * revision Lamont Franklin 2/10/2016 added duty level functionality
 * revision Ihar Lavor 2/12/2016 
 * revision Ihar Lavor 2/22/2016 
 */
public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	private Menu menuAccessor;
	private Scanner scan;


	public ParksProgram(Menu theMenu) {
		menuAccessor = theMenu;
		
		readSerialFile();
		checkForPastJobs();		
		
		User currentUser;
		do {
			currentUser = login();
			if (currentUser != null) {
				run(currentUser, allJobs, allUsers);
			}
		} while (currentUser != null);
		writeSerialFile();
	}

	@SuppressWarnings("rawtypes")
	private void run(User currentUser, Collection<Job> allJobs, Collection<User> allUsers) {				
		ArrayList<String> methodsList = currentUser.getMethodList();
		int menuSelection;
		do {
			menuSelection = 0;
			printData(currentUser, currentUser.getMainMenu(), null);
			
			menuSelection = getNumber();
			if (menuSelection > 0 && menuSelection < currentUser.getMainMenu().size()) {	
				
				Class[] param1 = new Class[1];	
				param1[0] = Collection.class;
				java.lang.reflect.Method method = null;
				
				try {
					String methodName = methodsList.get(menuSelection - 1);	
					method = currentUser.getClass().getMethod(methodName, param1);
				} catch (SecurityException e) {					
				} catch (NoSuchMethodException e) {	}
				
				try {
					StringBuilder temp;
					if (method.getName().equalsIgnoreCase("searchVolunteer")) {
						temp = (StringBuilder) method.invoke(currentUser, allUsers);
					} else {
						temp = (StringBuilder) method.invoke(currentUser, allJobs);
					}
					printData(currentUser, null, temp);	
					scan.nextLine();
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {}
			}			
		} while (menuSelection != currentUser.getMainMenu().size());
	}
	
	private void printData(User currentUser, ArrayList<String> menuList, StringBuilder temp) {		
		String menuName = "You are logged in as...";
		
		StringBuilder middleText = new StringBuilder();
		middleText.append("\t\t    " + currentUser.getSimpleName() + ", " + currentUser.getFirstName()
		+ " " + currentUser.getLastName());		
		
		if (menuList == null) {
			menuAccessor.updateMenu(menuName, middleText, null, temp);
		} else {
			menuAccessor.updateMenu(menuName, middleText, menuList, null);
		}
	}

	/**This class logs the user in and returns the resulting user information.
	 * 
	 * @return 
	 */
	@SuppressWarnings("resource")
	private User login() {				
		ArrayList<String> menuList = new ArrayList<String>();
		menuList.add("Login");
		menuList.add("Terminate program");
		StringBuilder middleText = new StringBuilder("");
		String menuName = "Login page";
		
		menuAccessor.updateMenu(menuName, middleText, menuList, null);

		if (getNumber() == 1) {		   
			Scanner keyboard = new Scanner(System.in);
			
			middleText.append("\n\nEnter your email address: ");
			menuAccessor.updateMenu(menuName, middleText, new ArrayList<String>(), null);
			
			String email = keyboard.nextLine();
			
			middleText.append(email);
			middleText.append("\nEnter your password:");
			menuAccessor.updateMenu(menuName, middleText, new ArrayList<String>(), null);
			
			String password = keyboard.nextLine();

			Iterator<User> itr = allUsers.iterator();
			
			while (itr.hasNext()) {
				User temp = itr.next();
				if (temp.getEmail().equals(email)
						&& temp.getPassword().equals(password)) {
					return temp;
				}				
			}
		}
		return null;
	}

	private void readSerialFile() {
		try {
			allUsers = SerialStartup.serialReadUsers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allUsers = new LinkedList<User>();
		}
		try {
			allJobs = SerialStartup.serialReadJobs();						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allJobs = new LinkedList<Job>();
		}
	}
	
	private void writeSerialFile() {
		if (allUsers != null) {
			try {
				SerialStartup.serialWriteUsers(allUsers);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}
		if (allJobs != null) {
			try {
				SerialStartup.serialWriteJobs(allJobs);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}
	}
	
	/**
	 * Check for past jobs and remove them from list before user see them.
	 */
	private void checkForPastJobs() {
		Calendar currentDate = new GregorianCalendar();		
		int curYear = currentDate.get(Calendar.YEAR);
		
		Calendar jobDate;
		int jobYear;
		boolean ifDeleted = false;
		
		if (allJobs != null && allJobs.size() > 0) {
			Iterator<Job> itr = allJobs.iterator();
			Job temp = null;
			while (itr.hasNext()) {				
				if (!ifDeleted) {
					temp = itr.next();					
				}
				jobDate = temp.getDate();
				jobYear = jobDate.get(Calendar.YEAR);	
								
				int jobDayOfYear = jobDate.get(Calendar.DAY_OF_YEAR);
				int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
				
				if (curYear < jobYear) {
					jobDayOfYear = jobDayOfYear + 365;
				}		
								
				if ((jobDayOfYear - curDayOfYear) <= 0) {				
					allJobs.remove(temp);
					itr = allJobs.iterator();
					temp = itr.next();
					ifDeleted = true;
				} else {
					ifDeleted = false;
				}
			}
		}	
	}

	/**
	 * Header for all menus
	 * @param theUser
	 */
	public static void menuHeader(User theUser){
		System.out.println();
		System.out.println("-------------Urban Parks Collective!------------");
		System.out.println("You are logged in as...");
		System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
		+ " " + theUser.getLastName());
		System.out.println();
	}

	
	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	private int getNumber() {	
		//get next menu selection
		int result = 0;
		scan = new Scanner(System.in);

		try {	        	
			String temp = scan.nextLine();
			if (Integer.parseInt(temp) >= 0) {
				result = Integer.parseInt(temp);				
			}
		} catch(NumberFormatException ne) {	}				
		return result;
	}
}