package model;

import view.UI;

public class MyOwnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyOwnException(String msg){
	      super(msg);
	   }
	
	@Override
	public void printStackTrace() {
		UI userInterface = new UI();
		userInterface.printText(this.toString());
	}
}
