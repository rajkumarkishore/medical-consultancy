package mcs.model;

public enum ItemType {

	NEW_REGISTRATION("NEW REGISTRATION"), RENEW_REGISTRATION(
			"RENEW REGISTRATION"), CONSULTANCY("Consulting"), SERVICE(
			"SERVICE");

	private String type;

	ItemType(String type) {
		this.type = type;
	}

	public String toString() {
		return type;
	}

}


