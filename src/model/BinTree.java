package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinTree {
	BNode theBTRootNode;
        int numElements;
        ArrayList<BNode> nodesAtWhichToInsert;
	public BinTree() // constructor
	{
		theBTRootNode = null;
                numElements = 0;
                nodesAtWhichToInsert = new ArrayList<BNode>();
                
	}

	// ------------------ Addition of the node to the BST-------------------
	protected void insertAB(BNode myNewNode) {
	
            if (theBTRootNode == null){
                theBTRootNode = myNewNode;
                nodesAtWhichToInsert.add(theBTRootNode);
                numElements++;
            }    
            else {
                for (BNode b : nodesAtWhichToInsert){
                    if (b.leftBNode == null){
                        b.leftBNode = myNewNode;
                        numElements++;
                        break;
                    }
                    else if (b.rightBNode == null){
                        b.rightBNode = myNewNode;
                        numElements++;
                        break;
                    }        
                }
            }
           //check to see if we need to reinitialize availableBreadthNodes
           for (int i=2; i<=6; i++){
               
               if (Math.pow(2,i) == numElements+1){
                   ArrayList<BNode> newList = new ArrayList<BNode>();
                   for (BNode b : nodesAtWhichToInsert){
                       newList.add(b.leftBNode);
                       newList.add(b.rightBNode);
                   }
                   nodesAtWhichToInsert = newList;
                   break;
               }
                   
           }
                
	}
	
	

	public void insertBST(Match match) {
		BNode anyClassBTNode = new BNode(match);
		//calls insert above
		insertAB(anyClassBTNode);
	}

	// ------------------ InOrder traversal-------------------
	protected void inorderDisplay(BNode theRootNode) {
		if (theRootNode != null) {
			inorderDisplay(theRootNode.leftBNode);
			theRootNode.show();
			inorderDisplay(theRootNode.rightBNode);
		}
	}
	protected void inorderUpdate(BNode theRootNode) {
		if (theRootNode == null)
			return;
		else{
			inorderUpdate(theRootNode.leftBNode);
			if (theRootNode.leftBNode != null && theRootNode.leftBNode.match.getHasCompleted() == true)
				theRootNode.match.setC1(theRootNode.leftBNode.match.calculateWinner());
			if (theRootNode.rightBNode != null && theRootNode.rightBNode.match.getHasCompleted() == true)
				theRootNode.match.setC2(theRootNode.rightBNode.match.calculateWinner());
			
			inorderUpdate(theRootNode.rightBNode);
		}
	}
        public void testUpdate(){
            for (BNode b : getLeaves())
                b.match.completed();
        }
        public ArrayList<BNode> getLeaves(){
            ArrayList<BNode> leaves = new ArrayList<BNode>();
            for (int i=0; i < nodesAtWhichToInsert.size(); i++){
                BNode currentNode = nodesAtWhichToInsert.get(i);
                BNode lNode = currentNode.leftBNode;
                BNode rNode = currentNode.rightBNode; 
                if (lNode == null && rNode == null)
                    leaves.add(currentNode);
                else{
                    if (lNode != null)
                        leaves.add(lNode);
                    if (rNode != null)
                        leaves.add(rNode);    
                }
                    
            }
            return leaves;
        }
        public void update(){
            inorderUpdate(theBTRootNode);
        }
	protected void gatherMatchesAtDepth(BNode theRootNode, int currentLevel, int desiredDepth, ArrayList<Match> listOfMatches) {
		
		if (theRootNode == null)
			return;
		else if (currentLevel == desiredDepth){
			listOfMatches.add(theRootNode.match);
			return;
		}
		else{
			gatherMatchesAtDepth(theRootNode.leftBNode, currentLevel+1, desiredDepth, listOfMatches);
			gatherMatchesAtDepth(theRootNode.rightBNode, currentLevel+1, desiredDepth, listOfMatches);
		}
	}
	

	//calls the method to do in order
	public void inorderDisplay() {
		inorderDisplay(theBTRootNode);
	}
        public void displayByRound(){
            int i=0;
            ArrayList<Match> matches;
            while ((matches = matchesAtGivenDepth(i)).size() != 0){
                for (Match m : matches)
                    System.out.println(m);
                i++;
            }
        }
	// ----- Search for match number and  returns ref. 
	//              to BNode or null if not found--------
	protected BNode search(BNode theRootNode, int matchNumber) {
		//if the root is null returns null
		if (theRootNode == null) {
			return null;
		} else {
			//checks if they are equal
			if (theRootNode.match.getMatchNumber() == matchNumber) {
				return theRootNode;
				//checks id the key is smaller than the current
				//record  if smaller traverses to the left
			} else if (theRootNode.match.getMatchNumber() < matchNumber) {
				return search(theRootNode.leftBNode, matchNumber);
			} else {
				// if bigger traverses to the left
				return search(theRootNode.rightBNode, matchNumber);
			}
		}
	}

	//returns null if no result else returns 
	//the match object matched with the keyName
	public Match searchBST(int matchNumber) {
		BNode temp = search(theBTRootNode, matchNumber);
		if (temp == null) {
			//noresults found
			return null;
		} else {
			//result found
			return temp.match;
		}
	}
	public ArrayList<Match> matchesAtGivenDepth(int depth){
		ArrayList<Match> listOfMatches = new ArrayList<Match>();
		
		gatherMatchesAtDepth(theBTRootNode, 0, depth, listOfMatches);
		return listOfMatches;
	}
	
	public void populateBinTree(List<Match> theList) {
		//clearing the root as not to append, 
		//if you want to append just remove the below line
		theBTRootNode = null;
		//keeps looping untill reaches the end of the list
		for(int i = 0;i < theList.size();i++){
			BNode temporaryNode = null; 
			insertBST(theList.get(i));

		}
	}
	
	
}