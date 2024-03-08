
public class Railroad {
	
	private int position;
	private String title;
	private boolean ownershipStatus;
	private MonopolyPlayer owner;
	private int mortgageValue;
	private boolean mortgageStatus;
	
	public Railroad(int myPosition, String myTitle, boolean myOwnershipStatus, MonopolyPlayer MyOwner, int myMortgageValue, boolean myMortgageStatus) {
		position = myPosition;
		title = myTitle;
		ownershipStatus = myOwnershipStatus;
		owner = MyOwner;
		mortgageValue = myMortgageValue;
		mortgageStatus = myMortgageStatus;
		
	}
	public int getPosition() {
		return position;
	}
	public boolean getOwnership() {
		return ownershipStatus;
	}
	public MonopolyPlayer getOwner() {
		return owner;
	}
	public String getTitle() {
		return title;
	}
	public boolean bought(MonopolyPlayer newOwner) {
		if(ownershipStatus == false) {
			owner = newOwner;
			ownershipStatus = true;
			return true;
		}
		return false;
	}
	public void addOwner(MonopolyPlayer myPlayer) {
		owner = myPlayer;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public boolean mortgageStatus() {
		return mortgageStatus;
	}
	public void changeOwnershipStatus() {
		if (ownershipStatus) {
			ownershipStatus = false;
		}
		else {
			ownershipStatus = true;
		}
	}
	

}
