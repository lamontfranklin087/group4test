package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.Menu;

public class TextBasedIO implements Observer {
	
	/* Width of the text screen, in characters. */
	public static final int SCREEN_WIDTH = 65;
	
	
	/* Number of extra returns to "clear" the screen, in lines. */
	public static final int CLEAR_SCREEN_HEIGHT = 15;

	@Override
	public void update(Observable theObservedObject, Object arg1) {
		Menu temp = (Menu) theObservedObject;
		clearScreen();
		printHeader(Menu.PROGRAM_NAME);
		printMenuName(temp.getMenuName());
		printMenuGreeting(temp.getMenuGreeting());
		printMenuOptions(temp.getMenuOptions());
	}

	private void clearScreen() {
		for(int i = 0; i < CLEAR_SCREEN_HEIGHT; i++) {
			System.out.println();
		}
	}

	/**
	 * This method simple prints out the header, taking into account the length
	 * of the name of this Program.
	 * 
	 * @param temp  the menu item to be printed
	 */
	private void printHeader(String theProgramName) {
		int programNameSize = theProgramName.length();
		
		System.out.println();
		for(int i = 0; i < (SCREEN_WIDTH - programNameSize) / 2; i++) {
			System.out.print("-");
		}
		System.out.print(theProgramName);
		for(int i = (SCREEN_WIDTH - programNameSize) / 2; i < SCREEN_WIDTH; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private void printMenuName(String theMenuName) {
		int programNameSize = theMenuName.length();
		for(int i = 0; i < (SCREEN_WIDTH - programNameSize) / 2; i++) {
			System.out.print(" ");
		}
		System.out.print(theMenuName);
		for(int i = (SCREEN_WIDTH - programNameSize) / 2; i < SCREEN_WIDTH; i++) {
			System.out.print(" ");
		}
		System.out.println();
	}

	/**
	 * Prints the "greeting" or top text in the menu.
	 * 
	 * @param temp  the menu item to be printed
	 */
	private void printMenuGreeting(StringBuilder theMenuGreeting) {
		//gets and prints out the stringbuilder that represents the greeting
		System.out.println(theMenuGreeting.toString());
		//gives a double space before menu options
		System.out.println();
	}

	private void printMenuOptions(ArrayList<String> theMenuOptions) {
		if(theMenuOptions.size() > 0) {
			for(int i = 0; i < theMenuOptions.size(); i++) {
				System.out.println(i + ")  " + theMenuOptions.get(i));
			}
			//gives a double space before input prompt
			System.out.println();
			System.out.print("Selection: ");
		}
		else {
			//gives a double space before input prompt
			System.out.println();
			System.out.print("[Press Return To Continue]");
		}
	}
}
