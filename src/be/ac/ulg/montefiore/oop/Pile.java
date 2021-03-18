package be.ac.ulg.montefiore.oop;

public class Pile {
	protected int nbCard;
	protected Card firstCard;//tas[0]
	protected Card lastCard;//tas[nb-1]
	protected Card tas[];
	
	//Méthodes
	
	public void addCardToTas(Card pArray[], int pNbCard){
		Card tmpTas[]=new Card[nbCard+pNbCard];
		for(int i=0; i<nbCard; i++)
			tmpTas[i]=tas[i];
		for(int i=0; i<pNbCard; i++)
			tmpTas[nbCard+i]=pArray[i];
		nbCard=nbCard+pNbCard;
		tas=tmpTas;
		lastCard=tas[nbCard-1];
		firstCard=tas[0];
	}
	
	public void removeCardToTas(int pNbCard){
		Card tmpTas[]=new Card[nbCard-pNbCard];
		for(int i=0; i<nbCard-pNbCard; i++)
			tmpTas[i]=tas[i];
		nbCard=nbCard-pNbCard;
		tas=tmpTas;
		if(nbCard!=0){
			lastCard=tas[nbCard-1];
			firstCard=tas[0];
		}
	}
	
	
	
	//GETTERS
	
	public Card getFirstCard(){
		return firstCard;
	}
	public Card getCard(int index){
		return tas[index];
	}
	public int getNbCard(){
		return nbCard;
	}
	
	public Card getLastCard(){
		return lastCard;
	}
	
	public int[] getTasToGraphic(){
		int vector[]=new int[nbCard];
		for(int i=0; i<nbCard; i++){
			vector[i]=tas[i].getValueToGraphic();
		}
		return vector;
	}
	
	public Card[] getTas(){
		return tas;
	}
	
	// Constructeurs
	
	public Pile(Pile source, int pNbCard){
		nbCard=pNbCard;
		if(pNbCard!=0){
			firstCard=source.getFirstCard();
			lastCard=source.getLastCard();
			for(int i=0;i<nbCard; i++){
				tas[i]=source.getCard(i);
			}
		}
	}
	
	public Pile(Card pArray[], int pNbCard){
		nbCard=pNbCard;
		Card Letas[]=new Card[nbCard];
		for(int i=0; i<nbCard; i++)
			Letas[i]=pArray[i];
		lastCard=Letas[nbCard-1];
		firstCard=Letas[0];
		tas=Letas;
	}

	public Pile() {
		nbCard=0;
	}
}

