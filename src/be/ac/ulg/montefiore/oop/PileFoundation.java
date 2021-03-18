package be.ac.ulg.montefiore.oop;

public class PileFoundation extends Pile{
	
	//Méthodes
	
	public void removeCard(int pNbCardToAdd){
		removeCardToTas(pNbCardToAdd);
	}
	
	public boolean addCard(Pile pPileToAdd, int pNbCardToAdd){
		if(nbCard==0 && pPileToAdd.getLastCard().getNumber()==0){
			addCardToTas(pPileToAdd.getTas(), pNbCardToAdd);
			return true;
		}
		else if(nbCard!=0 && pPileToAdd.getFirstCard().followsNumber(lastCard) && lastCard.sameColor(pPileToAdd.getFirstCard())){
			addCardToTas(pPileToAdd.getTas(),pNbCardToAdd);
			return true;
		}
		else
			return false;
	}
	
	public int[] getToGraphic(){
		int vector[]=new int[nbCard];
		if(nbCard>0){
		int vector2[]=new int[1];
		vector2[0]=lastCard.getValueToGraphic();
		return vector2;
		}
		return vector;
	}
	
	
	//Constructeur
	
	public PileFoundation(){
		super();
	}
}


