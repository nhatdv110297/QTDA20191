package entity;

public class Organization extends Entity {
	
	private String headquarters;
	
	public Organization(String identifier, String name, String describe, String headquarters) {
		super(identifier, name, describe);
		
	}

	public Organization(String identifier, String name, String describe) {
		
		// TODO Auto-generated constructor stub
	}


	 Organization() {
		// TODO Auto-generated constructor stub
	}

	public String getHeadquarters() {
		return headquarters;
	}

	 void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

}
