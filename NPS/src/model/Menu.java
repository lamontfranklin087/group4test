package model;

import java.util.ArrayList;
import java.util.Observable;

public class Menu extends Observable {
	
	/* The test representation of the program name. */
	public static final String PROGRAM_NAME = "Urban Parks Collective!";
	
	/* Title of current menu. */
	private String menuName;
	
	/* Any test that needs to be between the menu and options. */
	private StringBuilder otherText;
	
	/* Menu Options in current menu, an empty array has no options. */
	private ArrayList<String> menuOptions;

	/**
	 * Default Constructor for empty menu.
	 */
	public Menu(){
		setMenuName(null);
		setOtherText(new StringBuilder());
		setMenuOptions(new ArrayList<String>());
	}
	
	/**
	 * Basic constructor for creating a menu.
	 * 
	 * @param theMenuName  the name of the current menu
	 * @param theOtherText  any text that should appear between the menu name and option selection
	 * @param theMenuOptions  a list of menu option titles that the user will choose between
	 */
	public Menu(String theMenuName, StringBuilder theOtherText, ArrayList<String> theMenuOptions){
		setMenuName(theMenuName);
		setOtherText(theOtherText);
		setMenuOptions(theMenuOptions);
	}

	/**
	 * Just a test function to reset menu if desired.
	 * 
	 * @param theMenuName  the name of the current menu
	 * @param theOtherText  any text that should appear between the menu name and option selection
	 * @param theMenuOptions  a list of menu option titles that the user will choose between
	 */
	public void setTestMenu(String theMenuName, StringBuilder theOtherText, ArrayList<String> theMenuOptions) {
		setMenuName(theMenuName);
		setOtherText(theOtherText);
		setMenuOptions(theMenuOptions);
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

	public StringBuilder getOtherText() {
		return otherText;
	}

	public void setOtherText(StringBuilder otherText) {
		this.otherText = otherText;
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
