package be.ac.ulg.montefiore.oop;

public class History {
	private int nbMove;
	private Move lastMove;
	private Move array[];
	
	//Constructeur
	
	public History(){
		nbMove=0;
	}
	
	//Méthodes
	
	public void addMove(int pTypeMove, int pNbCard, int pFromPile, int pToPile){
		Move tmpArray[]=new Move[nbMove+1];
		Move newMove=new Move(pTypeMove, pNbCard, pFromPile, pToPile);
		for(int i=0; i<nbMove; i++){
			tmpArray[i]=array[i];
		}
		tmpArray[nbMove]=newMove;
		nbMove++;
		array=tmpArray;
		lastMove=array[nbMove-1];
	}
	
	public void removeMove(){
		nbMove--;
		Move tmpArray[]=new Move[nbMove];
		for(int i=0; i<nbMove; i++){
			tmpArray[i]=array[i];
		}
		array=tmpArray;
		if(nbMove!=0)
			lastMove=array[nbMove-1];
	}
	
	//GETTERS
	
	public Move getLastMove(){
		return lastMove;
	}
	
	public int getNbMove(){
		return nbMove;
	}
}
