
public class Property {
			
		private String title;
		private boolean ownership;
		private int position;
		private int price;
		private int rent;
		private int housePrice;
		private int house1;
		private int house2;
		private int house3;
		private int house4;
		private int house5;
		private int mortgageValue;
		private boolean mortgage;
		private int numOfHouses;
		private MonopolyPlayer owner;
		private String color;
		// need to add the # of houses on the given property
		// and the # of hotels on the given property (or simply just use 5 houses)
		// so that i can do the damage repairs with the chance cards
		
	public Property () {
		title = "";
		ownership = false;
		position = 0;
		price = 0;
		rent = 0;
		housePrice = 0;
		house1 = 0;
		house2 = 0;
		house3 = 0;
		house4 = 0;
		house5 = 0;
		mortgageValue = 0;
		numOfHouses = 0;
		mortgage = false;
		owner = null;
		color = null;
	}
	
	public Property (String myTitle, boolean myOwnership, int myPosition, int myPrice, int myRent, int myHousePrice, int myHouse1, int myHouse2, int myHouse3, int myHouse4, int myHouse5, int myMortageValue, int myNumOfHouses, boolean myMortgage, MonopolyPlayer myOwner, String myColor) {
		title = myTitle;
		position = myPosition;
		ownership = myOwnership;
		price = myPrice;
		rent = myRent;
		housePrice = myHousePrice;
		house1 = myHouse1;
		house2 = myHouse2;
		house3 = myHouse3;
		house4 = myHouse4;
		house5 = myHouse5;
		mortgageValue = myMortageValue;
		numOfHouses = myNumOfHouses;
		mortgage = myMortgage;
		owner = myOwner;
		color = myColor;
		
	}
	public String getTitle() {
		return title;
	}
	public boolean getOwnership() {
		return ownership;	
	}
	public int getPosition() {
		return position;
	}
	public int getPrice() {
		return price;
	}
	public int getRent() {
		return rent;
	}
	public int getHousePrice() {
		return housePrice;
	}
	public int getHouse1() {
		return house1;
	}
	public int getHouse2() {
		return house2;
	}
	public int getHouse3() {
		return house3;
	}
	public int getHouse4() {
		return house4;
	}
	public int getHouse5() {
		return house5;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public int getNumOfHouses() {
		return numOfHouses;
	}
	public boolean checkIfMortgaged() { 
		return mortgage;
	}
	public MonopolyPlayer getOwner() {
		return owner;
	}
	public String getColor() {
		return color;
	}
	public void addOwner(MonopolyPlayer myPlayer) {
		owner = myPlayer;
	}
	public void addHouse(int myNumOfHousesAdded) {
		numOfHouses += myNumOfHousesAdded;
	}
	public void changeOwnership() {
		if (ownership == false) {
			ownership = true;
		}
		else {
			ownership = false;
		}
	}
	public static void findProperty(int myPosition) {
		String[] Board = {"Go", "Mediterranean Avenue", "Community Chest", "Baltic Avenue", "Income Tax", "Reading Railroad", "Oriental Avenue", "Chance", "Vermont Avenue", "Connecticut Avenue", "Jail", "St.Charles Place", "Electric Company", "States Avenue", "Virginia Avenue", "Pennsylvania Railroad", "St.James Place", "Tennessee Avenue", "New York Avenue", "Free Parking", "Kentucky Avenue", "Chance", "Indiana Avenue", "Illinois Avenue", "B. & O. Railroad", "Atlantic Avenue", "Ventor Avenue", "Water Works", "Marvin Gardens", "Go To Jail", "Pacific Avenue", "North Carolina Avenue", "Community Chest", "Pennsylvania Avenue", "Short Line", "Chance", "Park Place", "Luxury Tax", "Boardwalk"}; // keep in mind that these are 0-39 not 1-40!!!
		int boardPosition = myPosition++;
		System.out.println(Board[boardPosition]);
	}
	public boolean purchaseProperty(MonopolyPlayer myPlayer) {
		if (position == myPlayer.getPosition()) {
			return false;
		}
		if (price > myPlayer.getBalance()) {
			return false;
		}
		if (ownership == true) {
			return false;
		}
		if (ownership == false ) {
			myPlayer.changeBalance(false, price);
			myPlayer.addProperties(MonopolyGameSimulation.PropertyLookUp(myPlayer.getPosition()));
			return true;
		}
		return false;
		
	}
	public static void propertyTester() {
		Property Boardwalk = new Property("Boardwalk", false, 39, 400, 50, 200, 200, 600, 1400, 1700, 2000, 200, 0, false, null, "blue");
		System.out.println(Boardwalk.getTitle());
		System.out.println(Boardwalk.getOwnership());
		System.out.println(Boardwalk.getPosition());
		System.out.println(Boardwalk.getPrice());
		System.out.println(Boardwalk.getRent());
		System.out.println(Boardwalk.getHousePrice());
		System.out.println(Boardwalk.getHouse1());
		System.out.println(Boardwalk.getHouse2());
		System.out.println(Boardwalk.getHouse3());
		System.out.println(Boardwalk.getHouse4());
		System.out.println(Boardwalk.getHouse5());
		System.out.println(Boardwalk.getMortgageValue());
		Boardwalk.changeOwnership();
		System.out.println(Boardwalk.getOwnership());
		findProperty((int)(Math.random() * 39));	
	}
	public static void main (String[] args) {
		propertyTester();
		
	}
		
}
