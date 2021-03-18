package be.ac.ulg.montefiore.oop;

public class PileTableau extends Pile{
	
	//Méthodes
	
	public boolean addCard(Pile pPileToAdd, int pNbCardToAdd){
		if(nbCard==0 && pPileToAdd.getFirstCard().getNumber()==12){
			addCardToTas(pPileToAdd.getTas(), pNbCardToAdd);
			return true;
		}
		else if(nbCard!=0 && lastCard.followsNumber(pPileToAdd.getFirstCard()) && lastCard.followsColor(pPileToAdd.getFirstCard())){
			addCardToTas(pPileToAdd.getTas(),pNbCardToAdd);
			return true;
		}
		else
			return false;
	}
	
	public boolean removeCard(int pNbCard){
		removeCardToTas(pNbCard);
		if(lastCard.isHidden()){
			lastCard.flip();
			return true;
		}
		return false;
	}

	//Constructeurs

	public PileTableau(Card[] pArray, int pNbCard){
		super(pArray, pNbCard);
	}
	
	public PileTableau(){
		super();
	}
}
