package model;

public enum UserType {
	
	URBAN_PARKS_STAFF(1),
	
	MANAGER(2),
	
	VOLUNTEER(3);
	
	private int val;
	
	UserType(int val) {
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
}

