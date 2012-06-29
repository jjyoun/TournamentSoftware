package model;

public class BNode {

    public BNode leftBNode,  rightBNode; // the nodes
    public Match match;
    
    
    public BNode(Match match ) {//constructor
        this.match= match;
    }

    public void show() {
        //calls the show method of the Match
        System.out.println(match);
    }
}