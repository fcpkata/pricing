package model;

public enum City {
	
	Chennai("Domestic"), Mumbai("Domestic"), Kolkata("Domestic"), Delhi("Domestic"), 
	Bengaluru("Domestic"), Hyderabad("Domestic"), Singapore("International");

	public final String type;

	City(String type) {
		this.type = type;
	}
	
	public boolean isInternational() {
		return type == "International";
	}

}
