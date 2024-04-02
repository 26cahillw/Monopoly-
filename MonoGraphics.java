import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class MonoGraphics {
	
	private static JFrame frame;
	
	public static void main (String[] args) {
		
		frame = new JFrame("Monopoly Game");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// what I want to do here is rather than each thing being a wholly new frame, just make a different panel
		
		System.out.println("This progam has run!");
		MonopolyPlayer player = new MonopolyPlayer("Computer");
		displayYNButton("Computer", player);
			
	}
	
	public static void displayYNButton(String task, MonopolyPlayer player) {
		
		String shout = "Would you like to " + task;
		
		JFrame frame = new JFrame();
		frame.setSize(800, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// graphics.drawString(shout, 400, 600); // may need adjustment
		
		JButton buttonYes = new JButton("Yes");
		buttonYes.setSize(10, 10);
		buttonYes.setLocation(500, 600);
		// should color/ style the buttons here
		
		JButton buttonNo = new JButton("No");
		buttonNo.setSize(10, 10);
		buttonNo.setLocation(500, 800);
		// should style/ color the buttons here

		frame.add(buttonNo);
		frame.add(buttonYes);
		// buttonYes.addActionListener(new YesListener());
		//buttonNo.addActionListener(new NoListener());
		
		
		frame.setVisible(true);
	}
	
	public static void displayWinnerOrLoser(boolean Winner, MonopolyPlayer player) {
		
		String shout = "";
		
		if (Winner) {
			shout = player.getName() + " has won the game!";
		}
		else {
			shout = player.getName() + " has lost the game!";
		}
		
		JFrame frame = new JFrame();
		frame.setSize(800, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setVisible(true);
		
		
		graphics.drawString(shout, 400, 600); // this starts at the point, so will need to be offset accordingly
		
		// display game "stats"
		
		String playerName = player.getName();
		ArrayList<Property> properties = player.getProperties();
		ArrayList<Railroad> railroads = player.getRailroads();
		ArrayList<Utility> utilities = player.getUtilities();
		int finalBalance = player.getBalance();
		
		// figure out how to display these stats
		// display character icon
		// Graphics.drawImage();
		// need various images for the game characters/ pieces and such
		
	}
	
	public static void displayToJail(MonopolyPlayer player) {
		
		JFrame frame = new JFrame();
		frame.setSize(800, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String playerName = player.getName();
		String shout = playerName + ", you have been sent to jail!";
		
		graphics.drawString(shout, 400, 600); // this starts at the point, so will need to be offset accordingly
		
		// style this somehow
		// add the character image perhaps
		
		frame.setVisible(true);
		
	}
	
	public static void displayInJail(MonopolyPlayer player) {
		
		if (player.getJailStatus()) { // just confirming this to make sure it isn't called improperly
			
			JFrame frame = new JFrame();
			frame.setSize(800, 1200);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JButton Roll = new JButton("Roll");
			Roll.addActionListener(new Roll());
			Roll.setLocation(1200, 400);
			
			JButton Pay = new JButton("Pay");
			Pay.addActionListener(new Pay());
			Roll.setLocation(1200, 600);
			
			JButton Play = new JButton("Play");
			Play.addActionListener(new Play());	
			Roll.setLocation(1200, 800);
			
			frame.add(Play);
			frame.add(Roll);
			frame.add(Pay);
		}		
	}
	
	public static void displayBoard(MonopolyPlayer player) {
		
		int pos = player.getPosition();
		
		if (pos > 10) {
			// rotateboard();
			if (pos > 20) {
				if (pos > 30) {
					
				}
			}
		}
		else {
			
		}
		//frame.setrotation();
		// thinking that it may be convinient to rotate the board for the perspective of the player to be better
		
		JButton Mortgage = new JButton("Mortgage");
		Mortgage.addActionListener(new MortgageListener());
		Mortgage.setLocation(1200, 400);
		
		JButton Trade = new JButton("Trade");
		Trade.addActionListener(new Trade());
		Mortgage.setLocation(1200, 600);
		
		JButton Menu = new JButton("Menu");
		Menu.addActionListener(new Menu());	
		Mortgage.setLocation(1200, 800);
		
		// need to figure out all of these elements below ***
	}
	
		class MortgageListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.out.println("The player has clicked Mortgage!");
			}
		}
	
	class YesListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Yes!");
		}
		
	}
	
	class NoListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked No!");
		}
		
	}
	
	class Roll implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Roll!");
		}
	}
	
	class Pay implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Pay!");
		}
	}
	
	class Play implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Play!");
		}
	}
	
	
	class Trade implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Trade!");
		}
	}
	
	class Menu implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("The player has clicked Menu!");
		}
	}
}
