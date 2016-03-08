package model;

import view.UI;
/**
 * @author Ihar Lavor
 * @version February/28/2016
 */
public class MyOwnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 * @param msg any string user want to print as exception message.
	 */
	public MyOwnException(String msg){
	      super(msg);
	   }
	
	@Override
	public void printStackTrace() {
		UI userInterface = new UI();
		userInterface.printText(this.toString());
	}
}
