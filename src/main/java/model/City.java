package model;

public enum City {
	
	CHENNAI("Domestic"), MUMBAI("Domestic"), KOLKATA("Domestic"), DELHI("Domestic"), 
	BENGALURU("Domestic"), HYDERABAD("Domestic"), SINGAPORE("International");

	public final String type;

	City(String type) {
		this.type = type;
	}
	
	public boolean isInternational() {
		return type == "International";
	}

}
