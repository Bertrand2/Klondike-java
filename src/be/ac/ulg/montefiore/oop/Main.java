package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.KlondikeView;
import be.ac.ulg.montefiore.oop.graphics.KlondikeSwingView;



public class Main {
	public static void main(String[] args){
		try{
			KlondikeHandler handler = new KlondikeHandler();
			KlondikeSwingView ssv = new KlondikeSwingView(handler);
			//Stock
			ssv.stockEmpty(false);
			//Waste
			ssv.wasteCards(handler.getGame().getWaste().getTasToGraphic());			
			
			//Tableau
			ssv.tableauCards(KlondikeView.TABLEAU_1, handler.getGame().getTableau()[0].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_2, handler.getGame().getTableau()[1].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_3, handler.getGame().getTableau()[2].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_4, handler.getGame().getTableau()[3].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_5, handler.getGame().getTableau()[4].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_6, handler.getGame().getTableau()[5].getTasToGraphic());
			ssv.tableauCards(KlondikeView.TABLEAU_7, handler.getGame().getTableau()[6].getTasToGraphic());
			handler.setView(ssv);
			ssv.refreshWindow();
		}
		catch(Exception e){
			System.err.println("An error occurred");
		}
	}
}