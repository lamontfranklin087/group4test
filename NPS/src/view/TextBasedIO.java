package view;

import java.util.Observable;
import java.util.Observer;
import model.Menu;

public class TextBasedIO implements Observer {

	@Override
	public void update(Observable theObservedObject, Object arg1) {
		Menu temp = (Menu) theObservedObject;
		System.out.println("--------------------" + Menu.PROGRAM_NAME + "--------------------\n");
		System.out.println(temp.getMenuName());
	}
}
