package controller;

import model.Team;
import model.Participant;
import model.Event;
import java.io.IOException;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;


public class TournamentSoftware {

	private Map<String, Event> allEvents;
	private Map<String, Participant> allParticipants;
	private Set<Participant> mismatchedPartnersList;
	private Set<Participant> needPartnersList;
	public TournamentSoftware(String file){
		allEvents = new TreeMap<String, Event>();
		mismatchedPartnersList = new LinkedHashSet<Participant>();
		needPartnersList = new LinkedHashSet<Participant>();
		try{
			allParticipants = ReadEntriesFromTextFile.readEntriesFromTextFile(file);
		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			constructEvents();
			fillEvents();
			constructDraws();

		}
	}

	public void constructEvents(){
		//Men's Singles
		allEvents.put("AMS", new Event("AMS"));
		allEvents.put("BMS", new Event("BMS"));
		allEvents.put("CMS", new Event("CMS"));
		allEvents.put("DMS", new Event("DMS"));

		//Women's Singles
		allEvents.put("AWS", new Event("AWS"));
		allEvents.put("BWS", new Event("BWS"));
		allEvents.put("CWS", new Event("CWS"));
		allEvents.put("DWS", new Event("DWS"));

		//Men's Doubles
		allEvents.put("AMD", new Event("AMD"));
		allEvents.put("BMD", new Event("BMD"));
		allEvents.put("CMD", new Event("CMD"));
		allEvents.put("DMD", new Event("DMD"));

		//Women's Doubles
		allEvents.put("AWD", new Event("AWD"));
		allEvents.put("BWD", new Event("BWD"));
		allEvents.put("CWD", new Event("CWD"));
		allEvents.put("DWD", new Event("DWD"));

		//Mixed Doubles
		allEvents.put("AMX", new Event("AMX"));
		allEvents.put("BMX", new Event("BMX"));
		allEvents.put("CMX", new Event("CMX"));
		allEvents.put("DMX", new Event("DMX"));
	}
	public void fillEvents(){
		
		for (Participant p : allParticipants.values()){
			
			
			for(String event : p.getEventsAsArray()){
				Event e = allEvents.get(event);
				if (e == null) //some people may not have events. (e.g. waitlisted)
					continue;
				
				String eventName = e.getName();
				char eventType = eventName.charAt(2);
				
				//Add correct CompetingEntity depending on whether the event is doubles or singles.
				if (eventType == 'S')
					e.addEntry(p);
				else{
					//if eventType is doubles, use getDoublesPartner(). Otherwise, eventType is mixed doubles.
				
					Participant partner = allParticipants.get((eventType == 'D') ? p.getDoublesPartner().toLowerCase() : p.getMixedPartner().toLowerCase());
					String listedPartner = (eventType == 'D')? p.getDoublesPartner() : p.getMixedPartner();
					/*PROBLEM: the allPartcipants.get() method requires that the key match EXACTLY. For participants entering
					 * their partner's names, they may misspell (or put extra spaces, etc.), making the hashTable
					 * fail to find the partner. 
					 * SOLUTOIN IDEAS: implement fuzzy search algorithms. (look into Lucene?). Could also write a 
					 * rough fuzzy search algorithm.
					 * 
					 * 
					 * 
					 * 
					 */
					if (partner == null){
						//Is this design sound? Or should I throw exceptions here?
						//System.out.println("Partner " + listedPartner + " not found for " + p + " for eventType " + eventType); 
						if (listedPartner.equals(""))
							needPartnersList.add(p);
						else{
							mismatchedPartnersList.add(p);
							
							//System.out.println("Closest Match: " + findClosestMatch(listedPartner));
						}
					}
					else
						e.addEntry(new Team(p, partner));
				}
			}
		}
//                for (Event e : allEvents.values())
//                    System.out.println(e.getNumEntries());
	}
	/*public String findClosestMatch(String target_name){
		for (Participant p: allParticipants.values()){
			if ()
		}
		
	}*/
	public void combineEvents(Event a, Event b){
            
        }
	public void constructDraws(){
		for (Event e : allEvents.values())
			e.constructDraw();
	}
	public void displayEvent(String eventName){
		System.out.println(allEvents.get(eventName));
	}
	public void displayAllEvents(){
		for (Event e : allEvents.values())
			System.out.println(e);
	}
	public void displayNeedList(){
		System.out.println("-------------Need list (" + needPartnersList.size() + ")--------------");
		for (Participant p : needPartnersList)
			System.out.println(p);
	}
	public void displayMismatchedList(){
		System.out.println("-------------Mismatched list (" + mismatchedPartnersList.size() + ")--------------");
		for (Participant p : mismatchedPartnersList)
			System.out.println(p);
	}
	public void displayDraw(String eventName){
		allEvents.get(eventName).displayDraw();
	}
	public void displayAllDraws(){
		for (String eventName : allEvents.keySet()){
			System.out.println(eventName);
			displayDraw(eventName);
		}
			
	}
        public Event getEvent(String eventName){
            return allEvents.get(eventName);
        }
        public Set<String> getAllEventNames(){
            return allEvents.keySet();
        }
        public Collection<Participant> getAllParticipants(){
            return allParticipants.values();
        }
        public void update(String eventName){
            allEvents.get(eventName).testUpdate();
            
        }
        public void setParticipantJList(JList jList){
            DefaultListModel listModel = new DefaultListModel();
            for (Participant p : allParticipants.values()){
                listModel.addElement(p);
            }
            jList = new JList(listModel);
        }
}
