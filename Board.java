import java.util.ArrayList;

public class Board {
	
	private static ArrayList<Property> Properties;
	private static ArrayList<Corners> Corners;
	private static ArrayList<Utility> Utilities;
	private static ArrayList<Railroad> Railroads;
	private static String[] BoardProperties;
	
	private static ArrayList<MonopolyPlayer> Players; // only value that is going to change at all
	// idk if this is supposed to be static or not. but it is now
	// could add the order that the players play in here, but idk if that is fully necessary
	
	
	public Board (ArrayList<MonopolyPlayer> myPlayers) {
		
		Properties = loadProperties();
		Corners = loadCorners();
		Utilities = loadUtilities();
		Players = myPlayers;
		Railroads = loadRailroads();
		BoardProperties = loadNormalProperties();
		
	}
	
	public static ArrayList<Property> getProperties() {
		return Properties;
	}
	public static ArrayList<Corners> getCorners() {
		return Corners;
	}
	public static ArrayList<Utility> getUtilities() {
		return Utilities;
	}
	public static ArrayList<MonopolyPlayer> getPlayers() {
		return Players;
	}
	public static ArrayList<Railroad> getRailroads() {
		return Railroads;
	}
	
	public void addPlayer(MonopolyPlayer myPlayer) {
		Players.add(myPlayer);
	}
	public static void removePlayer(MonopolyPlayer myPlayer) {
		Players.remove(myPlayer);
	}
	public static String[] loadNormalProperties() {
		String[] Board = {null, "Baltic Avenue", null, "Mediterranean Avenue", null, null, "Oriental Avenue", null, "Vermont Avenue", "Connecticut Avenue", null, "St.Charles Place", null, "States Avenue", "Virginia Avenue", null, "St.James Place", null, "Tennessee Avenue", "New York Avenue", null, "Kentucky Avenue", null, "Indiana Avenue", "Illinois Avenue", null, "Atlantic Avenue", "Ventor Avenue", null, "Marvin Gardens", null, "Pacific Avenue", "North Carolina Avenue", null, "Pennsylvania Avenue", null, null, "Park Place", null, "Boardwalk"};
		return Board;
	}
	public static String[] getBoardProperties() {
		return BoardProperties;
	}
	
	public static ArrayList<Property> loadProperties() {
		ArrayList<Property> Properties = new ArrayList<Property>();
		
		
		Property MediterraneanAve = new Property("Mediterranean Avenue", false, 1, 60, 2, 50, 10, 30, 90, 160, 250, 30, 0, false, null, "brown");
		Property BalticAve = new Property("Baltic Avenue", false, 3, 60, 4, 50, 20, 60, 180, 320, 450, 30, 0, false, null, "brown");
		Property OrientalAve = new Property("Oriental Avenue", false, 6, 100, 6, 50, 30, 90, 270, 400, 550, 50, 0, false, null, "light blue");
		Property VermontAve = new Property("Vermont Avenue", false, 8, 100, 6, 50, 30, 90, 270, 400, 550, 50, 0, false, null, "light blue");
		Property ConnecticutAve = new Property("Connecticut Avenue", false, 9, 120, 8, 50, 40, 100, 300, 450, 600, 60, 0, false, null, "light blue");
		Property StCharlesPlace = new Property("St. Charles Place", false, 11, 140, 10, 100, 50, 150, 450, 625, 750, 70, 0, false, null, "pink");
		Property StatesAve = new Property("States Avenue", false, 13, 140, 10, 100, 50, 150, 450, 625, 750, 70, 0, false, null, "pink");
		Property VirginiaAve = new Property("Virginia Avenue", false, 14, 160, 12, 100, 60, 180, 500, 700, 900, 80, 0, false, null, "pink");
		Property StJamesPlace = new Property("St. James Place", false, 16, 180, 14, 100, 70, 200, 550, 750, 950, 90, 0, false, null, "orange");
		Property TennesseeAve = new Property("Tennessee Avenue", false, 18, 180, 14, 100, 70, 200, 550, 750, 950, 90, 0, false, null, "orange");
		Property NewYorkAve = new Property("New York Avenue", false, 19, 200, 16, 100, 80, 220, 600, 800, 1000, 100, 0, false, null, "orange");
		Property KentuckyAve = new Property("Kentucky Avenue", false, 21, 220, 18, 150, 90, 250, 700, 875, 1050, 110, 0, false, null, "red");
		Property IndianaAve = new Property("Indiana Avenue", false, 23, 220, 18, 150, 90, 250, 700, 875, 1050, 110, 0, false, null, "red");
		Property IllinoisAve = new Property("Illinois Avenue", false, 24, 240, 20, 150, 100, 300, 750, 925, 1110, 120, 0, false, null, "red");
		Property AtlanticAve = new Property("Atlantic Avenue", false, 26, 260, 22, 150, 110, 330, 800, 975, 1150, 130, 0, false, null, "yellow");
		Property VentorAve = new Property("Ventor Avenue", false, 27, 260, 22, 150, 110, 330, 800, 975, 1150, 130, 0, false, null, "yellow");
		Property MarvinGardens = new Property("Marvin Gardens", false, 29, 280, 24, 150, 120, 360, 850, 1025, 1200, 140, 0, false, null, "yellow");
		Property PacificAve = new Property("Pacific Avenue", false, 31, 300, 26, 200, 130, 390, 900, 1110, 1275, 150, 0, false, null, "green");
		Property NorthCarolinaAve = new Property("North Carolina Avenue", false, 32, 300, 26, 200, 130, 390, 900, 1110, 1275, 150, 0, false, null, "green");
		Property PennsylvaniaAve = new Property("Pennsylvania Avenue", false, 34, 320, 28, 300, 150, 450, 1000, 1200, 1400, 160, 0, false, null, "green");
		Property ParkPlace = new Property("Park Place", false, 37, 350, 35, 200, 175, 500, 110, 1300, 1500, 175, 0, false, null, "blue");
		Property Boardwalk = new Property("Boardwalk", false, 39, 400, 50, 200, 200, 600, 1400, 1700, 2000, 200, 0, false, null, "blue");
		
		Properties.add(MediterraneanAve);
		Properties.add(BalticAve);
		Properties.add(OrientalAve);
		Properties.add(VermontAve);
		Properties.add(ConnecticutAve);
		Properties.add(StCharlesPlace);
		Properties.add(StatesAve);
		Properties.add(VirginiaAve);
		Properties.add(StJamesPlace);
		Properties.add(TennesseeAve);
		Properties.add(NewYorkAve);
		Properties.add(KentuckyAve);
		Properties.add(IndianaAve);
		Properties.add(IllinoisAve);
		Properties.add(AtlanticAve);
		Properties.add(VentorAve);
		Properties.add(MarvinGardens);
		Properties.add(PacificAve);
		Properties.add(NorthCarolinaAve);
		Properties.add(PennsylvaniaAve);
		Properties.add(ParkPlace);
		Properties.add(Boardwalk);
		
		return Properties;
		
	} 
	
	public static ArrayList<Corners> loadCorners() { 
		
		ArrayList<Corners> Corners = new ArrayList<Corners>();
		
		Corners Go = new Corners("Go!", 0, 0);
		Corners VisitingJail = new Corners("Just Visiting Jail", 10, 0);
		Corners FreeParking = new Corners ("Free Parking", 20,0);
		Corners GoToJail = new Corners("Go To Jail", 30,0);
		
		Corners.add(Go);
		Corners.add(VisitingJail);
		Corners.add(FreeParking);
		Corners.add(GoToJail);
		
		return Corners;
		
	}
	
	public static ArrayList<Railroad> loadRailroads() {
		
		ArrayList<Railroad> Railroads = new ArrayList<Railroad>();
		
		Railroad Reading = new Railroad(5, "Reading Railroad", false, null, 100, false);
		Railroad Pennsylvania = new Railroad(15, "Pennsylvania Railroad", false, null, 100, false);
		Railroad BNO = new Railroad(25, "B. & O. Railroad", false, null, 100, false);
		Railroad ShortLine = new Railroad(35, "Short Line", false, null, 100, false);
		
		Railroads.add(Reading);
		Railroads.add(Pennsylvania);
		Railroads.add(BNO);
		Railroads.add(ShortLine);
		
		return Railroads;
		
	}
	
	public static ArrayList<Utility> loadUtilities() {
		
		ArrayList<Utility> Utilities = new ArrayList<Utility>();
		
		Utility ElectricCompany = new Utility(12, "Electric Company", null, 75, false, false);
		Utility WaterWorks = new Utility(28, "Water Works", null, 75, false, false);
		
		Utilities.add(ElectricCompany);
		Utilities.add(WaterWorks);
		
		return Utilities;
		
	}

}
