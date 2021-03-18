package be.ac.ulg.montefiore.oop;

public class Stock{
	private Card pile[];
	private int nbCard;
	
	//GETTERS
	
	public Card[] getPile(){
		return pile;
	}
	public int getNbCard(){
		return nbCard;
	}
	
	//Constructeurs
	
	public Stock(){
	}
	
	public Stock(Card[] pStock, int pNbCard){
		pile=pStock;
		nbCard=pNbCard;
	}
	
	//Méthodes
	
	public void removeCard(int pNbCardToRemove){
	Card tmpStock[]=new Card[nbCard-pNbCardToRemove];
	for(int i=0; i<nbCard-pNbCardToRemove;i++)
		tmpStock[i]=pile[i];
	pile=tmpStock;
	nbCard=nbCard-pNbCardToRemove;
	}
	
	public int[] getTasToGraphic(){
		int vector[]=new int[nbCard];
		for(int i=0; i<nbCard; i++){
			vector[i]=pile[i].getValueToGraphic();
		}
		return vector;
	}
	
	public void removePile(int pNbCard){
		Card tmpPile[]=new Card[nbCard-pNbCard];
		for(int i=0; i<nbCard-pNbCard; i++)
			tmpPile[i]=pile[i];
		pile=tmpPile;
		nbCard=nbCard-pNbCard;
	}
	
	public void addPile(Card[] pCard, int pNbCard){
		Card tmpPile[]=new Card[nbCard+pNbCard];
		for(int i=0; i<nbCard; i++)
			tmpPile[i]=pile[i];
		for(int i=0; i<pNbCard; i++)
			tmpPile[nbCard+i]=pCard[i];
		pile=tmpPile;
		nbCard=nbCard+pNbCard;
	}
}
