package model;

public class Team extends CompetingEntity{
	private Participant p1, p2;
	public Team(Participant p1, Participant p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	public String getDisplayName(){
		return p1.getDisplayName() + " / " + p2.getDisplayName();
	}
	public String toString(){
		return getDisplayName();
	}
	public Participant getP1(){
		return p1;
	}
	public Participant getP2(){
		return p2;
	}
	public int hashCode(){
		if (p1 == null || p2 == null)
			return super.hashCode();
		else
			return p1.hashCode() + p2.hashCode();
	}
	public boolean equals(Object o){
		Team t = (Team) o;
		if (p1.hashCode() == t.getP1().hashCode() && p2.hashCode() == t.getP2().hashCode())
			return true;
		else if (p1.hashCode() == t.getP2().hashCode() && p2.hashCode() == t.getP1().hashCode())
			return true;
		else
			return false;
	}
}
