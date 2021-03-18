package be.ac.ulg.montefiore.oop;


public class Game {
	private PileTableau tableau[];
	private Card jeuCard[];
	private PileFoundation foundation[];
	private Stock stock;
	private Waste waste;
	private History history;
	
	//GETTERS
	
	public PileTableau[] getTableau(){
		return tableau;
	}
	
	public Card[] getTas(){
		return jeuCard;
	}
	
	public PileFoundation[] getFoundation(){
		return foundation;
	}
	
	public Stock getStock(){
		return stock;
	}
	
	public Waste getWaste(){
		return waste;
	}
	
	public History getHistory(){
		return history;
	}
	
	//Undo Méthodes
	public void UndoStockToWaste(int pNbCard){
		Card tmpPile[]=new Card[pNbCard];
		for(int i=0; i<pNbCard; i++)
			tmpPile[i]=waste.getPile()[waste.getNbCard()-1-i];
		stock.addPile(tmpPile, pNbCard);
		waste.removeCard(pNbCard);
		history.removeMove();
	}
	
	public void UndoWasteToStock(int pNbCard){
		Card tmpPile[]=new Card[pNbCard];
		for(int i=0; i<pNbCard; i++)
			tmpPile[i]=stock.getPile()[stock.getNbCard()-1-i];
		waste.setPile(tmpPile, pNbCard);
		stock.removeCard(pNbCard);
		history.removeMove();
	}
	
	public void undoWasteToTableau(int tab){
		Card tas[]=new Card[1];
		tas[0]=tableau[tab].getLastCard();
		waste.setPile(tas, 1);
		tableau[tab].removeCard(1);
		history.removeMove();
	}
	
	public void undoWasteToFoundation(int found){
		Card tas[]=new Card[1];
		tas[0]=foundation[found].getLastCard();
		waste.setPile(tas, 1);
		foundation[found].removeCard(1);
		history.removeMove();
	}
	
	public void UndoTableauToTableau(int fromTab, int nbCard, int toTab, boolean hidden){
		if(hidden)
			tableau[fromTab].getLastCard().flip();
		Card tmpTableau[]=new Card[tableau[fromTab].getNbCard()+nbCard];
		for(int i=0; i<tableau[fromTab].getNbCard(); i++)
			tmpTableau[i]=tableau[fromTab].getTas()[i];
		for(int i=0; i<nbCard; i++)
			tmpTableau[tableau[fromTab].getNbCard()+i]=tableau[toTab].getTas()[tableau[toTab].getNbCard()-nbCard+i];
		PileTableau newTab=new PileTableau(tmpTableau, tableau[fromTab].getNbCard()+nbCard);
		tableau[fromTab]=newTab;
		tableau[toTab].removeCard(nbCard);
		history.removeMove();
		
	}
	
	public void UndoTableauToFoundation(int tab, int found, boolean hidden){
		if(hidden)
			tableau[tab].getLastCard().flip();
		Card tmpTableau[]=new Card[tableau[tab].getNbCard()+1];
		for(int i=0; i<tableau[tab].getNbCard(); i++)
			tmpTableau[i]=tableau[tab].getTas()[i];
		tmpTableau[tableau[tab].getNbCard()]=foundation[found].getTas()[foundation[found].getNbCard()-1];
		PileTableau newTab=new PileTableau(tmpTableau, tableau[tab].getNbCard()+1);
		tableau[tab]=newTab;
		foundation[found].removeCard(1);
		history.removeMove();
	}
	
	public void UndoFoundationToTableau(int found, int tab){
		Card tas[]=new Card[1];
		tas[0]=tableau[tab].getLastCard();
		Pile tmpPile=new Pile(tas, 1);
		if(foundation[found].addCard(tmpPile, 1))
			tableau[tab].removeCard(1);
		history.removeMove();
	}
	
	//Méthodes
	
	public void clickStock(){
		if(stock.getNbCard()!=0){
		int nbCardToMove=3;
		if(stock.getNbCard()<3)
			nbCardToMove=stock.getNbCard();
		Card tmpWaste[]=new Card[nbCardToMove];
		for(int i=0; i<nbCardToMove;i++)
			tmpWaste[i]=stock.getPile()[stock.getNbCard()-1-i];
		waste.setPile(tmpWaste,nbCardToMove);
		stock.removeCard(nbCardToMove);
		history.addMove(0, nbCardToMove, 0, 0);
		}
		else
			wasteToStock();
	}
	
	public void wasteToStock(){
		Card tmpPile[]=new Card[waste.getNbCard()];
		for(int i=0; i<waste.getNbCard();i++){
			tmpPile[i]=waste.getPile()[waste.getNbCard()-1-i];
		}
		Stock tmpStock=new Stock(tmpPile,waste.getNbCard());
		stock=tmpStock;
		waste.removeCard(waste.getNbCard());
		history.addMove(1, stock.getNbCard(), 0, 0);
	}
	
	public void wasteToTableau(int tab){
		Card tas[]=new Card[1];
		tas[0]=waste.getLastCard();
		Pile tmpPile=new Pile(tas, 1);
		if(tableau[tab].addCard(tmpPile,1))
			waste.removeCard(1);
		history.addMove(2, 1, 0, tab);
	}
	
	public void wasteToFoundation(int found){
		Card tas[]=new Card[1];
		tas[0]=waste.getLastCard();
		Pile tmpPile=new Pile(tas, 1);
		if(foundation[found].addCard(tmpPile,1))
			waste.removeCard(1);
		history.addMove(3, 1, 0, found);
	}
	
	public void tableauToFoundation(int tab, int found){
		Card tas[]=new Card[1];
		tas[0]=tableau[tab].getLastCard();
		Pile tmpPile=new Pile(tas, 1);
		if(foundation[found].addCard(tmpPile,1))
			tableau[tab].removeCard(1);
		history.addMove(5, 1, tab, found);
	}
	
	public void tableauToTableau(int fromTab,int nbCard,int toTab){
		Card tas[]=new Card[nbCard];
		for(int i=0; i<nbCard; i++){
			tas[i]=tableau[fromTab].getTas()[tableau[fromTab].getNbCard()-nbCard+i];
		}
		Pile tmpPile=new Pile(tas, nbCard);
		if(tableau[toTab].addCard(tmpPile,nbCard)){
			history.addMove(4, nbCard, fromTab, toTab);
			if(tableau[fromTab].removeCard(nbCard))
				history.getLastMove().setHidden();
		}
	}
	
	public void foundationToTableau(int found, int tab){
		Card tas[]=new Card[1];
		tas[0]=foundation[found].getLastCard();
		Pile tmpPile=new Pile(tas, 1);
		if(tableau[tab].addCard(tmpPile,1)){
			foundation[found].removeCard(1);
		}
		history.addMove(6, 1, found, tab);
	}
	
	public void newGame(){
		//initialisation des variables
		Card tmpJeuCard[]=new Card[52];
		for(int i=0; i<52; i++)
			tmpJeuCard[i]=new Card(i, false);
		jeuCard=tmpJeuCard;
		Card tmpCard;
		int randNb;
		int k=0;
		Card tmpStock[]=new Card[24];
		
		//mélange du jeu
		for(int i=0; i<52; i++){
			randNb=i+(int)(Math.random()*(52-i));
			tmpCard=jeuCard[randNb];
			jeuCard[randNb]=jeuCard[i];
			jeuCard[i]=tmpCard;
		}
		//initialisation tableau

		PileTableau tmpTableau[]=new PileTableau[7];
		tableau=tmpTableau;
		for(int i=0; i<7; i++){
			Card array[]= new Card[i+1];
			for(int j=0; j<=i; j++){
				array[j]=jeuCard[k];
				k=k+1;
				if(j!=i)
					array[j].flip();
			}
			tableau[i]=new PileTableau(array, i+1);
		}
		
		//initialisation stock
		for(int i=0; i<24; i++){
			tmpStock[i]=jeuCard[28+i];
		}
		stock=new Stock(tmpStock, 24);
		
		//initialisation waste
		waste=new Waste();
		
		//initialisation foundation
		PileFoundation tmpFoundation[]=new PileFoundation[4];
		foundation=tmpFoundation;
		for(int i=0; i<4; i++)
			foundation[i]=new PileFoundation();
		//initialisation historique
		History tmpHistory=new History();
		history=tmpHistory;
	}
	
	public boolean isWin(){
		for(int i=0; i<7; i++)
			if(stock.getNbCard()!=0 || waste.getNbCard()!=0 || tableau[i].getNbCard()!=0 && tableau[i].getFirstCard().getHidden())
				return false;
		
		return true;
	}
	
	public void undoMove(){
		if(history.getNbMove()!=0){
			switch(history.getLastMove().getTypeMove()){
				case 0:
					UndoStockToWaste(history.getLastMove().getNbCard());
					break;
				case 1:
					UndoWasteToStock(history.getLastMove().getNbCard());
					break;
				case 2:
					undoWasteToTableau(history.getLastMove().getToPile());
					break;
				case 3:
					undoWasteToFoundation(history.getLastMove().getToPile());
					break;
				case 4:
					UndoTableauToTableau(history.getLastMove().getFromPile(), history.getLastMove().getNbCard(), history.getLastMove().getToPile(), history.getLastMove().getHidden());
					break;
				case 5:
					UndoTableauToFoundation(history.getLastMove().getFromPile(), history.getLastMove().getToPile(), history.getLastMove().getHidden());
					break;
				case 6:
					UndoFoundationToTableau(history.getLastMove().getFromPile(), history.getLastMove().getToPile());
					break;
				default:
					System.out.println("error in move");
			}
		}
	}
	
	//Constructeurs
	
	public Game(){
		newGame();
	}
}
