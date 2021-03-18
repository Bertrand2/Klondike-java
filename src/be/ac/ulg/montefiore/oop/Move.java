package be.ac.ulg.montefiore.oop;

public class Move {
	/*
	 * Mouvement possible:
	 * 0 stock to waste
	 * 1 waste to stock
	 * 2 waste to tableau
	 * 3 waste to foundation
	 * 4 tableau to tableau
	 * 5 tableau to foundation
	 * 6 foundation to tableau
	 * En fonction du type de mouvement, le mouvement inverse sera effectué via la méthode "undoMove" dans la classe "Game"
	 */
	private int typeMove;
	private int nbCard;
	private int fromPile;
	private int toPile;
	private boolean hidden;
	
	//Constructeurs
	
	public Move(){	
	}
	
	public Move(int pTypeMove, int pNbCard, int pFromPile, int pToPile){
		typeMove=pTypeMove;
		nbCard=pNbCard;
		fromPile=pFromPile;
		toPile=pToPile;
		hidden=false;
	}
	
	//SETTER
	
	public void setHidden(){
		hidden=true;
	}
	
	//GETTERS
	
	public int getTypeMove(){
		return typeMove;
	}
	public boolean getHidden(){
		return hidden;
	}
	
	public int getNbCard(){
		return nbCard;
	}
	
	public int getFromPile(){
		return fromPile;
	}
	
	public int getToPile(){
		return toPile;
	}
	
	
}
