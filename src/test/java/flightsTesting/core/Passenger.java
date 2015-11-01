package flightsTesting.core;

public class Passenger {
	private String lastName;
	private String firstName;
	private PassengerTitle title;
	private PassengerType type;
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public PassengerTitle getTitle() {
		return title;
	}

	public void setTitle(PassengerTitle title) {
		this.title = title;
	}

	public PassengerType getType() {
		return type;
	}

	public void setType(PassengerType type) {
		this.type = type;
	}
}
