package model;


public class Participant extends CompetingEntity{
	//Instance variables from parsing.
	private String lastName;
	private String firstName;
	private String gender; //M = male, F = female
	private String email;
	private String events;
	
	
	//Other relevant instance variables.
	private String waitListedEvents;
	
	//***Other parse-able instance variables. Work  on these later.***
	
	private String phone;
	private String club;
	private String groupEntry;
	private String doublesPartner;
	private String mixedPartner;
	private int numEvents;
	private String tShirtSize;
	private boolean tShirt;
	private int fee;
	private boolean paid;
	
	//Unsure whether to keep these inside of Participant.
	private String singlesEvents;
	private String doublesEvents;
	private String mixedEvents;
	
	
	
	
	public Participant(String lastName, String firstName, String gender, String email, String events,
						String doublesPartner, String mixedPartner){
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.email = email;
		
		//Separate out waitlisted events and format.
		this.events = "";
		this.waitListedEvents = "";
		for (String an_event: events.split(" ")){
			if (!an_event.contains("("))
				this.events += an_event + " ";
			else
				this.waitListedEvents += an_event + " ";
		}
		this.events = this.events.trim();
		this.waitListedEvents = this.waitListedEvents.trim();
		
		this.doublesPartner = doublesPartner;
		this.mixedPartner = mixedPartner;
	}
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
	public String getFullName() {
		return firstName + " " + lastName;
	}
        public String getFullName2(){
            return lastName + ", " + firstName;
        }
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public String getGroupEntry() {
		return groupEntry;
	}
	public void setGroupEntry(String groupEntry) {
		this.groupEntry = groupEntry;
	}
	public String getSinglesEvents() {
		return singlesEvents;
	}
	public void setSinglesEvents(String singlesEvents) {
		this.singlesEvents = singlesEvents;
	}
	public String getDoublesEvents() {
		return doublesEvents;
	}
	public void setDoublesEvents(String doublesEvents) {
		this.doublesEvents = doublesEvents;
	}
	public String getDoublesPartner() {
		return doublesPartner;
	}
	public void setDoublesPartner(String doublesPartner) {
		this.doublesPartner = doublesPartner;
	}
	public String getMixedEvents() {
		return mixedEvents;
	}
	public void setMixedEvents(String mixedEvents) {
		this.mixedEvents = mixedEvents;
	}
	public String getMixedPartner() {
		return mixedPartner;
	}
	public void setMixedPartner(String mixedPartner) {
		this.mixedPartner = mixedPartner;
	}
	public int getNumEvents() {
		return numEvents;
	}
	public void setNumEvents(int numEvents) {
		this.numEvents = numEvents;
	}
	public String gettShirtSize() {
		return tShirtSize;
	}
	public void settShirtSize(String tShirtSize) {
		this.tShirtSize = tShirtSize;
	}
	public boolean istShirt() {
		return tShirt;
	}
	public void settShirt(boolean tShirt) {
		this.tShirt = tShirt;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public String getEvents(){
		return events;
	}
	
	public String[] getEventsAsArray(){
		return events.split(" ");
	}
	public String toString(){
		  return getFullName2();
	}
        public String firstInitial(){
            return firstName.substring(0, 1) + ".";
        }
	//Interface methods.
	public String getDisplayName(){
		return lastName + ", " + firstInitial();
                
	}

}
