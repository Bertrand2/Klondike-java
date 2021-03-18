package be.ac.ulg.montefiore.oop;


public class Card {
	private int number; //nombre affiché sur la carte, as=1, J=11, Q=12 K=13
	private int color; //nombre correspondant à une couleur (0=SPADE 1=HEART 2=CLUB 3=DIAMOND)
	private int value; //nombre constant (0 à 51)
	private boolean hidden; //true si caché
	
	//SETTERS
	
	public void SetNumber(int pValue){
		number=pValue%13;
	}
	
	public void SetColor(int pValue){
		color=pValue/13;
	}
	
	public void SetValue(int pValue){
		value=pValue;
	}
	
	public void SetHidden(boolean pHidden){
		hidden=pHidden;
	}
	
	//GETTERS
	
	public int getNumber(){
		return number;
	}

	public int getColor(){
		return color;
	}
	
	public int getValue(){
			return value;
	}
	
	public int getValueToGraphic(){
		if(hidden)
			return 52;
		else
			return value;
	}
	
	public boolean getHidden(){
		return hidden;
	}
	
	//Constructeurs
	
	public Card(int pValue, boolean pHidden){
		number=pValue%13;
		color=pValue/13;
		value=pValue;
		hidden=pHidden;
	}
	
	public Card(){
	}
	
	//méthodes
	
	public void flip(){
		hidden=!hidden;
	}
	
	public boolean isHidden(){
		return hidden;
	}
	
	public boolean followsNumber(Card c){
		return (c.getNumber()==this.getNumber()-1);
	}
	
	public boolean followsColor(Card c){
		if(color+c.getColor()!=3 && color!=c.getColor())
			return true;
		return false;
	}
	
	public boolean sameColor(Card c){
		if(color==c.getColor())
			return true;
		return false;
	}
}
