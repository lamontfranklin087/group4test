package model;

import java.util.ArrayList;
import java.util.Observable;

public class Menu extends Observable {
	
	/* The test representation of the program name. */
	public static final String PROGRAM_NAME = "Urban Parks Collective!";
	
	/* Title of current menu. */
	private String menuName;
	
	/* Any test that needs to be between the menu and options. */
	private StringBuilder menuGreeting;
	
	/* Any test that needs to be between the menu and options. */
	private StringBuilder message;
	
	/* Menu Options in current menu, an empty array has no options. */
	private ArrayList<String> menuOptions;

	/**
	 * Default Constructor for empty menu.
	 */
	public Menu(){
		setMenuName(null);
		setMenuGreeting(new StringBuilder());
		setMenuOptions(new ArrayList<String>());
	}
	
	/**
	 * Basic constructor for creating a menu.
	 * 
	 * @param theMenuName  the name of the current menu
	 * @param theMenuGreeting  any text that should appear between the menu name and option selection
	 * @param theMenuOptions  a list of menu option titles that the user will choose between
	 */
	public Menu(String theMenuName, StringBuilder theMenuGreeting, ArrayList<String> theMenuOptions){
		setMenuName(theMenuName);
		setMenuGreeting(theMenuGreeting);
		setMenuOptions(theMenuOptions);
	}

	/**
	 * A Menu update using a menu object.
	 * 
	 * @param theMenuName  the name of the current menu
	 * @param theMenuGreeting  any text that should appear between the menu name and option selection
	 * @param theMenuOptions  a list of menu option titles that the user will choose between
	 */
	public void updateMenu(Menu theNewMenu) {
		menuName = theNewMenu.getMenuName();
		menuGreeting = theNewMenu.getMenuGreeting();
		menuOptions = theNewMenu.getMenuOptions();
        setChanged();
	    notifyObservers();
	}
	
	/**
	 * A Menu update using individual parameters.
	 * 
	 * @param theMenuName  the name of the current menu
	 * @param theMenuGreeting  any text that should appear between the menu name and option selection
	 * @param theMenuOptions  a list of menu option titles that the user will choose between
	 */
	public void updateMenu(String theMenuName, StringBuilder theMenuGreeting, 
						   ArrayList<String> theMenuOptions, StringBuilder body) {
		menuName = theMenuName;
		menuGreeting = theMenuGreeting;
		menuOptions = theMenuOptions;
		message = body;
        setChanged();
	    notifyObservers();
	}
	
	public StringBuilder getMessage() {
		return message;
	}
	
	public void setMessage(StringBuilder body) {
		message = body;
		setChanged();
	    notifyObservers();
	}
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
        setChanged();
	    notifyObservers();
	}

	public StringBuilder getMenuGreeting() {
		return menuGreeting;
	}

	public void setMenuGreeting(StringBuilder otherText) {
		this.menuGreeting = otherText;
        setChanged();
	    notifyObservers();
	}

	public ArrayList<String> getMenuOptions() {
		return menuOptions;
	}

	public void setMenuOptions(ArrayList<String> menuOptions) {
		this.menuOptions = menuOptions;
        setChanged();
	    notifyObservers();
	}
}
