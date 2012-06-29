package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class Event {
	private String name;
	private Set<CompetingEntity> setOfEntries;
	private BinTree draw;
	private ArrayList<Match> startingMatches;
	
	
	public Event(String name){
		this.name = name;
		setOfEntries = new LinkedHashSet<CompetingEntity>();
		draw = new BinTree();
	}
	
	public void addEntry(CompetingEntity ce){
		setOfEntries.add(ce);
	}
	public String toString(){
		String flightInfo = "";
		flightInfo += "Event: " + name + "\n Entries(" + setOfEntries.size() + "):";
		for (CompetingEntity ce : setOfEntries){
			flightInfo += "\n" + ce.getDisplayName();
		}
		return flightInfo;
	}
	public String getName(){
		return name;
	}
	
	//make starting double-elimination matches.
	private void constructStartingMatches(){
		
		int numEntries = setOfEntries.size();
		int numStartingMatches = (int)(Math.ceil(numEntries/2.));
		startingMatches = new ArrayList<Match>();
		
		//Pseudo-randomly construct matches.
		CompetingEntity[] listOfEntries = setOfEntries.toArray(new CompetingEntity[numEntries]);
		
		CompetingEntity c = null;
		for (int i=0; i<numEntries; i++){
			int randomIndex = (int)(Math.random()*(numEntries-i)) + i;
			
			//swap entities at listOfEntries[i] and listOfEntries[randomIndex]
			CompetingEntity temp = listOfEntries[i];
			listOfEntries[i] = listOfEntries[randomIndex];
			listOfEntries[randomIndex] = temp;
			if (c==null)
				c=listOfEntries[i];
			else{
				startingMatches.add(0, new Match(c, listOfEntries[i], false));
				c = null;
			}
		}
		if (numEntries%2 == 1 && c!=null){
			startingMatches.add(new Match(c, null, false));
		}
		
		
		/*For numEntries, we need numEntries-1 number of matches to determine a winner (not counting
		 *consolation matches). We have created, so far, numStartingMatches matches.
		 *So we need to create numEntries - 1 - Math.ceil(numEntries/2) more matches.
		 *
		 */
		int remainingMatches = (numEntries - 1) - numStartingMatches;
		for (int i=0; i<remainingMatches; i++){
			startingMatches.add(new Match(null, null, false));
		}
	}
	public void constructDraw(){
		constructStartingMatches();
		draw.populateBinTree(startingMatches);
		//System.out.println("Event: " + name + " -----TREE STRUCTURE----");
		//draw.displayByRound();
	}
	
	public void displayDraw(){
		draw.displayByRound();
	}
        public ArrayList<Match> getStartingMatches(){
            return startingMatches;
        }
        public Set<CompetingEntity> getSetOfEntries(){
            return setOfEntries;
        }
        public int getNumEntries(){
            return setOfEntries.size();
        }
        public void testUpdate(){
            draw.testUpdate();
            displayDraw();
            draw.update();
            displayDraw();
            draw.update();
            displayDraw();
            draw.update();
            displayDraw();    
        }
	
}
