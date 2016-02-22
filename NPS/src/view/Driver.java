package view;

import java.text.ParseException;
import java.util.ArrayList;

import model.Job;
import model.Menu;
import model.ParksProgram;

/**
 * Initiating Main for Parks Program
 * @author David, Ian, Ihar, Lamont
 */
public class Driver {

	public static void main(String[] args) throws ParseException {
		Menu menu = new Menu();
		TextBasedIO textIO = new TextBasedIO();
		menu.addObserver(textIO);
		
	}

}
