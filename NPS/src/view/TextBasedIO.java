package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.Menu;

/**
 * This class prints out the test form of a menu item.
 * @author David
 */
public class TextBasedIO implements Observer {
	
	/* Width of the text screen, in characters. */
	public static final int SCREEN_WIDTH = 65;
	
	
	/* Number of extra returns to "clear" the screen, in lines. */
	public static final int CLEAR_SCREEN_HEIGHT = 15;

	/**
	 * Update calls the required Headers and options to be printed.
	 * Update is called whenever the observed class calls notifyObservers.
	 * 
	 * @param theObservedObject is the changed menu that needs to be printed
	 * @param arg1 is an optional (unused here) additional object that can be passed
	 */
	@Override
	public void update(Observable theObservedObject, Object arg1) {
		Menu temp = (Menu) theObservedObject;
		clearScreen();
		printHeader(Menu.PROGRAM_NAME);
		printMenuName(temp.getMenuName());
		printMenuGreeting(temp.getMenuGreeting());
		printMenuOptions(temp.getMenuOptions());
	}

	/**
	 * Clear screen simply prints the required number of lines to clear the test
	 * space resulting in an apparent screen clear.
	 */
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
		int numCharsToPrint = SCREEN_WIDTH - theProgramName.length();
		
		System.out.println();
		for(int i = 0; i < numCharsToPrint / 2; i++) {
			System.out.print("-");
		}
		System.out.print(theProgramName);
		for(int i = numCharsToPrint / 2; i < numCharsToPrint; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	/**
	 * Prints the Menu Name and centers it in the screen.
	 * 
	 * @param theMenuName  string representation of the menu name
	 */
	private void printMenuName(String theMenuName) {
		int numSpacesToPrint = SCREEN_WIDTH - theMenuName.length();
		for(int i = 0; i < numSpacesToPrint / 2; i++) {
			System.out.print(" ");
		}
		System.out.print(theMenuName);
		for(int i = numSpacesToPrint / 2; i < numSpacesToPrint; i++) {
			System.out.print(" ");
		}
		System.out.println();
	}

	/**
	 * Prints the "greeting" or top text in the menu. Will cut short lines
	 * that are longer than the SCREEN_WIDTH.
	 * 
	 * @param temp  the list of "greetings" in string form
	 */
	private void printMenuGreeting(ArrayList<String> theMenuGreeting) {
		//gets and prints each string in the list
		for(int i = 0; i < theMenuGreeting.size(); i++) {
			//if a string is too long, we cut it off a little before the screen edge
			if(theMenuGreeting.get(i).length() > SCREEN_WIDTH) {
				String partialLine = theMenuGreeting.get(i).substring(0, (SCREEN_WIDTH - 4));
				System.out.println(partialLine + "... ");
			}
			//if it's short enough we just print it
			else {
				System.out.println(theMenuGreeting.get(i));
			}
		}
		//gives a double space before menu options
		System.out.println();
	}

	/**
	 * Prints the menu options passed in via theMenuOptions, or a simple 
	 * return statement is there are no options to print. It'll cut short
	 * lines that are longer than the SCREEN_WIDTH.
	 * 
	 * @param theMenuOptions  the list of options in string form
	 */
	private void printMenuOptions(ArrayList<String> theMenuOptions) {
		if(theMenuOptions.size() > 0) {
			for(int i = 0; i < theMenuOptions.size(); i++) {
				if(theMenuOptions.get(i).length() > SCREEN_WIDTH - 4) {
					String partialLine = theMenuOptions.get(i).substring(0, (SCREEN_WIDTH - 8));
					System.out.println((i + 1) + ")  " + partialLine + "... ");
				}
				//if it's short enough we just print it
				else {
					System.out.println((i + 1) + ")  " + theMenuOptions.get(i));
				}
				
				
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
