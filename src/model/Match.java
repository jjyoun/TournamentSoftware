package model;


public class Match implements Comparable{
	
	private static int numMatches = 0;
	
	private int matchNumber; //design choice. We may want to make this a String or a float/double.
	private CompetingEntity c1, c2;
	private boolean isConsolation;
	private boolean hasCompleted = false;
	private int[][] score = new int[2][3];
	public Match(CompetingEntity c1, CompetingEntity c2, boolean isConsolation){
		numMatches++;
		matchNumber = numMatches;
		this.c1 = c1;
		this.c2 = c2;
		this.isConsolation = isConsolation;
	}
	public String toString(){
		return c1 + " vs " + c2 + " status: " + ((hasCompleted == true)? "completed" : "unfinished");
	}
	public int getMatchNumber(){
		return matchNumber;
	}
	public int compareTo(Object o) {
		Match m = (Match) o;
		if (matchNumber == m.getMatchNumber())
			return 0;
		else if (matchNumber > m.getMatchNumber())
			return 1;
		else
			return -1;
	}
	public CompetingEntity calculateWinner(){
		//typically done through calculating score, but we'll just randomize for testing.
            if (hasCompleted == true){
		int random = (int)(Math.random()*2);
		if (random == 0)
			return c1;
		return c2;
            }
            else
                return null;
	}
        public CompetingEntity getC1(){
		return c1;
	}
	public CompetingEntity getC2(){
		return c2;
	}
	public void setC1(CompetingEntity c1){
		this.c1 = c1;
	}
	public void setC2(CompetingEntity c2){
		this.c2 = c2;
	}
	public void completed(){
		//TO-DO: input score in some manner, then change hasCompleted to true.
		hasCompleted = true;
	}
	public boolean getHasCompleted(){
		return hasCompleted;
	}
}
