package model;


public abstract class CompetingEntity {
	private int seed = 10000; //defaulted to this sentinel value.
	private boolean hasConsolation;
	public int getSeed(){
		return seed;
	}
	
	public boolean hasConsolation(){
		return hasConsolation;
	}
	
	public abstract String getDisplayName();
}

//***Does this make sense?***// 
