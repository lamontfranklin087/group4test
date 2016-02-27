package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.User;

public class UI {
	private Scanner keyboard;
	/* Number of extra returns to "clear" the screen, in lines. */
	public static final int CLEAR_SCREEN_HEIGHT = 15;
	
	
//	public void printData(User currentUser, ArrayList<String> menuList, StringBuilder temp) {		
//		String menuName = "You are logged in as...";
//		
//		StringBuilder middleText = new StringBuilder();
//		middleText.append("\t\t    " + currentUser.getSimpleName() + ", " + currentUser.getFirstName()
//		+ " " + currentUser.getLastName());		
//		
//		if (menuList == null) {
//			//menuAccessor.updateMenu(menuName, middleText, null, temp);
//		} else {
//			//menuAccessor.updateMenu(menuName, middleText, menuList, null);
//		}
//	}
	
	/**
	 * Prints the menu options passed in via theMenuOptions, or a simple 
	 * return statement is there are no options to print.
	 * 
	 * @param theMenuOptions  the list of options in string form
	 * @return 
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
	 * Header for all menus
	 * @param theUser
	 */
	public void menuHeader(User theUser){		
		System.out.println();
		System.out.println("-------------Urban Parks Collective!------------");
		if (theUser != null) {
			System.out.println("You are logged in as...");
			System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
			+ " " + theUser.getLastName());
			System.out.println();
		}
	}
	
	public void printText(String string) {
		System.out.println(string);
	}
	
	/**
	 * Parse string to integer.
	 * @param availableMenuOptions 
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
	            System.out.println("That's not a write number.");	            
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
	
	public void waitForReturnKey() {
		System.out.println();
		System.out.print("[Press Return To Continue]");
		keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
}
