
public class Utility {
	
	private int position;
	private String title;
	private MonopolyPlayer owner;
	private int mortgageValue;
	private boolean ownershipStatus;
	private boolean mortgageStatus;
	
	public Utility(int myPosition, String myTitle, MonopolyPlayer myOwner, int MyMortgageValue, boolean myOwnership, boolean myMortgageStatus) {
		position = myPosition;
		title = myTitle;
		owner = myOwner;
		mortgageValue = MyMortgageValue;
		ownershipStatus = myOwnership;
		mortgageStatus = myMortgageStatus;
	}
	public String getTitle() {
		return title;
	}
	
	public MonopolyPlayer getOwner() {
		return owner;	
	}
	
	public int getMortgageValue() {
		return mortgageValue;
	}
	public boolean getOwnership() {
		return ownershipStatus;
	}
	
	public void changeOwner(MonopolyPlayer myOwner) {
		owner = myOwner;
	}
	
	public boolean getMortgageStatus() {
		return mortgageStatus;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void addOwner(MonopolyPlayer newOwner) {
		owner = newOwner;
	}
	public int getPrice() {
		return 150;
	}
	public void changeOwnershipStatus() {
		if (ownershipStatus) {
			ownershipStatus = false;
		}
		else {
			ownershipStatus = true;
		}
	}
	
	// utitlies charge 4 times the dice amount rolled!
	// if two utilites are owned by one person, 
	// and that person is not the person landing on the square, then charge 10 * diceroll

}
