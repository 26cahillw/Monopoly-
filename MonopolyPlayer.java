import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyPlayer {
	
	private String name;
	private int balance;
	private ArrayList<Property> properties;
	private ArrayList<Railroad> Railroads;
	private ArrayList<Utility> Utilities;
	private int position;
	private int jailCard;
	private boolean jailStatus;
	private ArrayList<String> buildingPermitColors;
	
	public MonopolyPlayer(String myName) {
		name = myName;
		balance = 1500;
		properties = new ArrayList<Property>();
		Railroads = new ArrayList<Railroad>();
		Utilities = new ArrayList<Utility>(); 
		position = 0;	
		jailCard = 0;
		jailStatus = false;
		buildingPermitColors = new ArrayList<String>();
	}
	public MonopolyPlayer(String myName, int myBalance, ArrayList<Property> myProperties, ArrayList<Railroad> myRailroads, ArrayList<Utility> myUtilities, int myPosition, int myJailCard) {
		name = myName;
		balance = myBalance;
		properties = myProperties;
		Railroads = myRailroads;
		Utilities = myUtilities;
		position = myPosition;
		jailCard = myJailCard;
		jailStatus = false;
		buildingPermitColors = new ArrayList<String>();
	}
	public static void main (String[] args) {
		MonopolyPlayer user = new MonopolyPlayer("You");
		MonopolyPlayer Computer = new MonopolyPlayer("Computer 1");
		Computer.changeBalance(false, 200);
		System.out.println(Computer.getBalance());
		System.out.println(user.getBalance());
	}	
	
	public String getName() {
		return name;
	}
	
	public int getBalance () {
		return balance;	
	}
	public ArrayList<Property> getProperties() {
		return properties;
	}
	public void addProperties(Property myProperty) {
		properties.add(myProperty);
		
	}
	public int getPosition () {
		return position;
	}
	public void passGo() {
		balance += 200;
	}
	public void addOutOfJailCard() {
		jailCard++;
	}
	public ArrayList<Railroad> getRailroads() {
		return Railroads;
	}
	public ArrayList<Utility> getUtilities() {
		return Utilities;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void changeBalance(boolean myDirection, int myMagnitude) {
		if (myDirection == true) {
			balance += myMagnitude;
		}
		if (myDirection == false) {
			balance -= myMagnitude;
		}
	}
	public void newPosition(int newPosition) {
		position = newPosition;
	}
	public void changePosition(boolean myDirection, int myMagnitude) {
		if (myDirection == true) {
			position += myMagnitude;
		}
		if (myDirection == false) {
			position -= myMagnitude;
		}
	}
	
	public void changeJailStatus() {
		if (jailStatus) {
			jailStatus = false;
		}
		else {
			jailStatus = true;
		}
	}
	
	public boolean getJailStatus() {
		return jailStatus;
	}
	
	public boolean findJailCard() {
		if (jailCard > 0) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getBuildingPermitColors() {
		return buildingPermitColors;
	}
	
	public void addBuildingColor(String color) {
		buildingPermitColors.add(color);
	}
	
}
