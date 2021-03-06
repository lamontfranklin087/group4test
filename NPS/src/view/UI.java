package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Manager;
import model.UrbanParksStaff;
import model.User;
import model.Volunteer;
/**
 * @author Ihar Lavor
 * @version February/27/2016
 */
public class UI {
	private Scanner keyboard;
	/** Number of extra returns to "clear" the screen, in lines. */
	public static final int CLEAR_SCREEN_HEIGHT = 15;
	
	private int staffMenuOffset = 5;
	private int volunteerMenuOffset = 8;
	
	/**
	 * This class logs the user in and returns the resulting user information.
	 * @param userInterface.
	 * @return user object corresponding to provided email and password.
	 */	
	public ArrayList<String> loginScreen(UI userInterface) {
		userInterface.clearScreen();
		userInterface.menuHeader(null);
		
		ArrayList<String> menuList = new ArrayList<String>();		
		userInterface.printText("                 Login page\n");
				
		menuList.add("Login");
		menuList.add("Terminate program");	
		
		int userSelection = userInterface.printMenuOptions(menuList);		
		
		if (userSelection == 1) {
			userInterface.clearScreen();
			userInterface.printMenuOptions(null);
			userInterface.menuHeader(null);
			userInterface.printText("Enter your email address: ");	
			ArrayList<String> userData = new ArrayList<String>();
			userData.add(keyboard.nextLine());
			
			userInterface.printText("Enter your password: ");						
			userData.add(keyboard.nextLine());
			return userData;
		}
		return null;
	}
	/**
	 * Print individual user menu and receive an input from user.
	 * @param currentUser is currently logged in user.
	 * @return a menu option user selected.
	 */
	public int printMenu(User currentUser) {
		clearScreen();
		menuHeader(currentUser);
		int menuSelection = 0;
		if (currentUser instanceof Manager) {
			ArrayList<String>  menuText = new ArrayList<String>();
			menuText.add("Submit a new job");
			menuText.add("Delete a job");
			menuText.add("Edit the details of a job");
			menuText.add("View a summary of all upcoming jobs");
			menuText.add("View the Volunteers for a job");
			menuText.add("Exit");	
			menuSelection = printMenuOptions(menuText);
			if (menuSelection == 6) {
				menuSelection = 0; //10 means EXIT
			}
		} else if (currentUser instanceof Volunteer) {
			ArrayList<String>  menuText = new ArrayList<String>();
			menuText.add("View the jobs I am signed up for");
			menuText.add("View a summary of all upcoming jobs");
			menuText.add("View details of a selected upcoming job");
			menuText.add("Volunteer for a job");		
			menuText.add("Exit");	
			menuSelection = printMenuOptions(menuText);
			if (menuSelection == 5) {
				menuSelection = 0; //10 means EXIT
			} else {
				menuSelection = menuSelection + volunteerMenuOffset;
			}
		} else if (currentUser instanceof UrbanParksStaff) {
			ArrayList<String>  menuText = new ArrayList<String>();
			menuText.add("View a summary of all upcoming jobs");
			menuText.add("View details of a selected upcoming job");
			menuText.add("Search volunteers by last name");
			menuText.add("Exit");	
			menuSelection = printMenuOptions(menuText);
			if (menuSelection == 4) {
				menuSelection = 0; //10 means EXIT
			} else {
				menuSelection = menuSelection + staffMenuOffset;
			}
		} 		
		return menuSelection;
	}
	/**
	 * Print edit menu for manager and receive an input from him/her.
	 * @param currentUser is currently logged in user.
	 * @return a menu option user selected.
	 */
	public int printEditMenu(User currentUser) {
		clearScreen();
		menuHeader(currentUser);
		int menuSelection = 0;
		ArrayList<String>  menuText = new ArrayList<String>();
		menuText.add("Change Job's Date");
		menuText.add("Change Job's Location");
		menuText.add("Change Job's Duration");
		menuText.add("Change Job's Slots");
		menuText.add("Change Job's Description");
		menuText.add("Change Job's Start Time");
		menuText.add("Exit");
		menuSelection = printMenuOptions(menuText);		
		return menuSelection;
	}	
	
	/**
	 * Prints the menu options passed in via theMenuOptions, or a simple 
	 * return statement is there are no options to print.
	 * 
	 * @param theMenuOptions  the list of options in string form
	 * @return option user selected.
	 */
	public int printMenuOptions(ArrayList<String> theMenuOptions) {
		int selection = 0;
		if(theMenuOptions != null && theMenuOptions.size() > 0) {
			int i = 0;
			for(i = 0; i < theMenuOptions.size(); i++) {
				System.out.println((i + 1) + ")  " + theMenuOptions.get(i));
			}			
			System.out.println();
			System.out.print("Selection: ");
			selection = getNumber(i);
		}
		return selection;
	}
		
	/**
	 * Header for all menus.
	 * @param theUser is user object.
	 */
	public void menuHeader(User theUser){		
		System.out.println();
		System.out.println("-------------Urban Parks Collective!------------");
		if (theUser != null) {
			System.out.println("You are logged in as...");
			System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
			+ " " + theUser.getLastName());			
		}
		System.out.println();
	}
	/**
	 * Print any other text.
	 * @param string is any text user want to print.
	 */
	public void printText(String string) {
		System.out.println(string);
	}
	
	/**
	 * Parse string to integer.
	 * @param availableMenuOptions maximum number of menu option.
	 * @return an integer number from 1 to ...
	 */
	protected int getNumber(int availableMenuOptions) {
		int result = -1;
		keyboard = new Scanner(System.in);
		
		while(true){
	        try {	        	
	        	String temp = keyboard.nextLine();
	        	if (Integer.parseInt(temp) >= 0 && Integer.parseInt(temp) <= availableMenuOptions) {
	        		result = Integer.parseInt(temp);
	        		break;
	        	}
	        } catch(NumberFormatException ne) {
	            System.out.println("That's not a valid number.");	            
	        }	
		}
		return result;
	}
	
	/**
	 * Clear screen simply prints the required number of lines to clear the test
	 * space resulting in an apparent screen clear.
	 */
	public void clearScreen() {
		for(int i = 0; i < CLEAR_SCREEN_HEIGHT; i++) {
			System.out.println();
		}
	}
	/**
	 * Receive an input from user.
	 * @return received input from user.
	 */
	public String getNextLine() {
		return keyboard.nextLine();
	}
	
	/**
	 * Wait until user press enter.
	 */
	public void waitForReturnKey() {
		System.out.println();
		System.out.print("[Press Return To Continue]");
		keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
}
