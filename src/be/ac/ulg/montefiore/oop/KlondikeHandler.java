package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.KlondikeEventsHandler;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;
import be.ac.ulg.montefiore.oop.graphics.KlondikeView;




public class KlondikeHandler implements KlondikeEventsHandler{
	
	private KlondikeSwingView ssv;
	private Game game;
	
	public Game getGame(){
		return game;
	}
	
	//private KlondikeSwingView view; 
	public void setView(KlondikeSwingView pSsv){
		ssv=pSsv;
	}
	public KlondikeHandler(){
		Game tmpGame=new Game();
		game=tmpGame;
	}
	
	// ...
	
	public void clickStock(){
		game.clickStock();
		refreshWindow();
	}
	
	public void refreshWindow(){
		boolean stockIsEmpty=false;
		if(game.getStock().getNbCard()==0)
			stockIsEmpty=true;
		try
		{
			//Stock
			ssv.stockEmpty(stockIsEmpty);
			//waste
			ssv.wasteCards(game.getWaste().getTasToGraphic());			
			
			//Tableau
			ssv.tableauCards(KlondikeView.TABLEAU_1, game.getTableau()[0].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_2, game.getTableau()[1].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_3, game.getTableau()[2].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_4, game.getTableau()[3].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_5, game.getTableau()[4].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_6, game.getTableau()[5].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_7, game.getTableau()[6].getTasToGraphic());
			//foundation
			ssv.foundationCards(FOUNDATION_1, game.getFoundation()[0].getToGraphic());
			ssv.foundationCards(FOUNDATION_2, game.getFoundation()[1].getToGraphic());
			ssv.foundationCards(FOUNDATION_3, game.getFoundation()[2].getToGraphic());
			ssv.foundationCards(FOUNDATION_4, game.getFoundation()[3].getToGraphic());
			
			ssv.updateMoves(game.getHistory().getNbMove());
			
			ssv.refreshWindow();
		}
		catch (Exception e)
		{
			System.err.println("ERROR TO REFRESH WINDOW");
		}
	}
	
	public void newGame() {
		System.out.println("New Game");
		game=new Game();
		refreshWindow();
	}

	
	public void moveWasteToTableau(int tableau) {
		game.wasteToTableau(tableau);
		refreshWindow();
	}

	
	public void moveWasteToFoundation(int foundation) {
		game.wasteToFoundation(foundation);
		refreshWindow();
	}

	
	public void moveTableauToFoundation(int tableau, int foundation) {
		game.tableauToFoundation(tableau, foundation);
		win();
		refreshWindow();
	}

	
	public void moveTableauToTableau(int tableauSrc, int numCards, int tableauDst) {
		game.tableauToTableau(tableauSrc, numCards, tableauDst);
		win();
		refreshWindow();
	}

	
	public void moveFoundationToTableau(int foundation, int tableau) {
		game.foundationToTableau(foundation, tableau); 
		refreshWindow();
	}

	
	public void undo() {
		System.out.println("Undo");
		game.undoMove();
		refreshWindow();
		
	}
	public void win(){
		if(game.isWin()){
			
			boolean cardInTab=false;
			for(int i=0; i<7; i++){
				if(game.getTableau()[i].getNbCard()!=0)
					cardInTab=true;
			}
			while(cardInTab){
				for(int i=0; i<7; i++){
					if(game.getTableau()[i].getNbCard()!=0){
						for(int j=0; j<4; j++){
							Card tas[]=new Card[1];
							tas[0]=game.getTableau()[i].getLastCard();
							Pile tmpPile=new Pile(tas, 1);
							if(game.getFoundation()[j].addCard(tmpPile,1)){
								game.getTableau()[i].removeCard(1);
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								refreshWindow();
							}
							game.getHistory().addMove(5, 1, i, j);
						}
					}
				}
				cardInTab=false;
				for(int i=0; i<7; i++){
					if(game.getTableau()[i].getNbCard()!=0)
						cardInTab=true;
				}
			}
			ssv.win();
		}
	}


	public String getName() {
		return "Renaud Pire";
	}
}
