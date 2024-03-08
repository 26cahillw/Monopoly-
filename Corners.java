
public class Corners {
	
	private String title;
	private int position;
	private int balance;
	
	public Corners(String myTitle, int myPosition, int myBalance) {
		title = myTitle;
		position = myPosition;
		balance = myBalance;
	}
	
	public int getPosition() {
		return position;
	}
	public int getBankTotal() {
		return balance;
	}
	public String getTitle() {
		return title;
	}
	public void addToBankTotal(int myInt) {
		balance += myInt;
	}
	public void payPlayerBalance(MonopolyPlayer myPlayer) {
		myPlayer.changeBalance(true, balance);	
		balance = 0;
	}
	

}
