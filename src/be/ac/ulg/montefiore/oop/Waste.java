package be.ac.ulg.montefiore.oop;

public class Waste {
	Card pile[];
	int nbCard;
	
	
	
	
	
	//Méthodes
	
	public int[] getTasToGraphic(){
		int nbCardShown=3;
		if (nbCard<3)
			nbCardShown=nbCard;
		int vector[]=new int[nbCardShown];
		for(int i=0; i<nbCardShown; i++){
			vector[nbCardShown-1-i]=pile[nbCard-1-i].getValueToGraphic();
		}
		return vector;
	}
	
	public void setPile(Card[] pCard, int pNbCard){
		Card tmpPile[]=new Card[nbCard+pNbCard];
		for(int i=0; i<nbCard; i++)
			tmpPile[i]=pile[i];
		for(int i=0; i<pNbCard; i++)
			tmpPile[nbCard+i]=pCard[i];
		pile=tmpPile;
			nbCard=nbCard+pNbCard;
	}
	
	public void removeCard(int pNbCardToRemove){
		Card tmpWaste[]=new Card[nbCard-pNbCardToRemove];
		for(int i=0; i<nbCard-pNbCardToRemove;i++)
			tmpWaste[i]=pile[i];
		pile=tmpWaste;
		nbCard=nbCard-pNbCardToRemove;
	}
	
	//GETTERS
	
	public Card[] getPile(){
		return pile;
	}
	public int getNbCard(){
		return nbCard;
	}
	public Card getLastCard(){
		return pile[nbCard-1];
	}
	
	//Constructeurs
	
	public Waste(){
		
	}
	
	public Waste(Card[] pWaste, int pNbCard){
		pile=pWaste;
		nbCard=pNbCard;
	}
}