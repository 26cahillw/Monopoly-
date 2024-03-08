import java.util.Scanner;
import java.util.ArrayList;

public class MonopolyGameSimulation {
	
	public static void main (String [] args) {
		System.out.println("If you would like to play Player vs. Player, type 0");
		System.out.println("If you would like to play Player vs. Computer, type 9"); // these two are perfectly setup
		System.out.println("If you would like to simulate a game, type 8"); // this does not fully work
		System.out.println("If you would like to play a modified version of the game, type 7"); // this I have not begun coding
		Scanner kboard = new Scanner(System.in);
		int input = kboard.nextInt();
		if (input == 9) {
			playComputer();	
		}
		if (input == 0) {
			playerVsPlayer();
		}
		if (input == 8) {
			simulateGame();
		}
		if (input == 7) {
			modifiedGame();
		}
		
	}
		public static void modifiedGame() {
			System.out.println("Stick with me, you are going to have to enter a lot of information.");
			System.out.println("If you want to change property information, type 9");
			System.out.println("If you want to change _ type 8");
		}
		public static void simulateGame() {
			System.out.println("Stick with me, you are going to have to enter a lot of infomation.");
			System.out.println("If you would simply like to enter a series of players, and simulate a game, press 0");
			System.out.println("If you would like to simualte the rest of an IRL Game, press 9");
			Scanner kboard = new Scanner(System.in);
			int input = kboard.nextInt();
			if (input == 0) {
				System.out.println("How many total players will be playing the game?");
				int numPlayers = kboard.nextInt();
				ArrayList<MonopolyPlayer> Players = new ArrayList<MonopolyPlayer>();
				for (int i = 0; i < numPlayers; i++) {
					createPlayer(Players, i);
				}
				
				boolean gameRun = true;
				Board game = new Board(randomizePlayers(Players, numPlayers));
				
				ArrayList<MonopolyPlayer> myPlayers = Board.getPlayers();
				
				while (gameRun) {
					for (int i = 0; i < myPlayers.size(); i++) {
						MonopolyPlayer current = myPlayers.get(i);
						if (myPlayers.size() == 1) {
							System.out.println("The game is over! " + current.getName() + " is the winner!");
						}
						System.out.println("It is " + current.getName() + "'s" + " turn");
						ComputerPlay(current);
						
					}
				}
				
			}
			if (input == 9) {
				System.out.println("How many total players were playing the game?");
				int numPlayers = kboard.nextInt();
				ArrayList<MonopolyPlayer> Players = new ArrayList<MonopolyPlayer>();
				for (int i = 0; i < numPlayers; i++) {
					loadPlayer();
				}
				
				boolean gameRun = true;
				Board game = new Board(randomizePlayers(Players, numPlayers));
				
				ArrayList<MonopolyPlayer> myPlayers = Board.getPlayers();
				
				
				
			}
			
			
			// each player, their positions, their properties / ownerships, and the houses on these properties, any mortgages, their balances
			// then just need to update the board properly
			// then just loop through each player with the computer play method.
		}
		
		public static void playerVsPlayer() {
			System.out.println("How many total players will be playing the game?");
			Scanner kboard = new Scanner(System.in);
			int numPlayers = kboard.nextInt();
			ArrayList<MonopolyPlayer> Players = new ArrayList<MonopolyPlayer>();
			for (int i = 0; i < numPlayers; i++) {
				createPlayer(Players, i);
			}
			
			boolean gameRun = true;
			Board game = new Board(randomizePlayers(Players, numPlayers));
			
			ArrayList<MonopolyPlayer> myPlayers = Board.getPlayers();
			
			while (gameRun) {
				for (int i = 0; i < myPlayers.size(); i++) {
					MonopolyPlayer current = myPlayers.get(i);
					if (myPlayers.size() == 1) {
						System.out.println("The game is over! " + current.getName() + " is the winner!");
					}
					System.out.println("It is " + current.getName() + "'s" + " turn");
					playerTurn(current);
					
				}
			}
		}
			
		
		public static void playComputer() {
			System.out.println("How many Computer Players would you like to play against?");
			//System.out.println("Enter the value 1 if you would like to exit the game");
			boolean gameRun = true;
			Scanner kboard = new Scanner(System.in);
			int numPlayers = kboard.nextInt();
			
			ArrayList<MonopolyPlayer> Players = generatePlayers(numPlayers);
			
			Board game = new Board(Players);
			
			ArrayList<MonopolyPlayer> MyPlayers = Board.getPlayers();
			
			//for (int i = 0; i < Players.size(); i++) {
			//	System.out.println(Players.get(i).getName());
			//}
			
			while (gameRun) {
					for (int i = 0; i < MyPlayers.size(); i++) {
						if (MyPlayers.size() == 1) {
							System.out.print("The Game is over!" + MyPlayers.get(1).getName() + "has won!");
						}
						MonopolyPlayer current = MyPlayers.get(i);
						if (current.getName().equals("You")) {
							playerTurn(current);
						}
						else {
							ComputerPlay(current);
						}
					}
			}
		}	
		public static void playerTurn(MonopolyPlayer myPlayer) {
			
			boolean PlayerTurn = true;
			
			PlayerTurn = checkJail(myPlayer);
			
			if (myPlayer.getBalance() < 0) {
				System.out.println(myPlayer.getName() + " HAS BEEN ELIMINATED FROM THE GAME");
				PlayerTurn = false;
				Board.removePlayer(myPlayer);
			}
			
			while (PlayerTurn) {
				
				System.out.println("Type 0, to roll!");
				int roll = 0;
				Scanner kboard4 = new Scanner(System.in);
				
				if (kboard4.nextInt() == 0) {
					roll = roll(myPlayer);
					myPlayer.changePosition(true, roll);
				}
				
				int position = myPlayer.getPosition();
				
				if (position % 10 == 0) {
					System.out.println("You are currently on the property " + CornersLookUp(position).getTitle());
					
					if (position == 20) {
						Corners current = CornersLookUp(position);
						current.payPlayerBalance(myPlayer);	
					}
					if (position == 30) {
						System.out.println("You have been sent to jail!");
						position = 10;
						jail(myPlayer);
					}
					if (position == 10) {
						System.out.println("You are occupying the square: Just Visiting Jail");
					}
					
				}
				if (position % 10 == 5) {
					System.out.println("You are currently on the property " + RailroadLookUp(position).getTitle());
					Railroad current = RailroadLookUp(position);
					boolean ownership = current.getOwnership();
					
					if (ownership) {
						int numberOfRailRoads = numRailroads(current.getOwner());
						if (numberOfRailRoads == 1) {
							myPlayer.changeBalance(false, 25);
							current.getOwner().changeBalance(true,  25);
						}
						if (numberOfRailRoads == 2) {
							myPlayer.changeBalance(false, 50);
							current.getOwner().changeBalance(true,  50);
						}
						if (numberOfRailRoads == 3) {
							myPlayer.changeBalance(false, 100);
							current.getOwner().changeBalance(true,  100);
						}
						if (numberOfRailRoads == 4) {
							myPlayer.changeBalance(false, 200);
							current.getOwner().changeBalance(true,  200);
						}
						
					}
					
					if (ownership != true) {
						System.out.println("Would you like to buy the Railroad: " + current.getTitle() + " for $200");
						Scanner kboard = new Scanner(System.in);
						if (kboard.nextLine().equals("yes") || kboard.nextLine().equals("Yes")) {
							if (myPlayer.getBalance() > 200) {
								myPlayer.changeBalance(false, 200);
								current.addOwner(myPlayer);
								current.changeOwnershipStatus();
							}
							else {
								System.out.println("Unfortunately, you do not have enough money to  purchase the property:" + current.getTitle());
							}
						}
						
					}
				}
				
				if (position == 12 || position == 28) {
					
					System.out.println("You are currently on the property " + UtilityLookUp(position).getTitle());
					
					Utility current = UtilityLookUp(position);
					boolean ownership = current.getOwnership();
					
					if (ownership) {
						
						int numberOfUtilites = numUtilities(current.getOwner());
						
						if (numberOfUtilites == 1) {
							myPlayer.changeBalance(false, roll * 4);
							current.getOwner().changeBalance(true, roll * 4);
						}
						
						if (numberOfUtilites == 1) {
							myPlayer.changeBalance(false, roll * 10);
							current.getOwner().changeBalance(true, roll * 10);
						}
						
					}
					
					else {
						
						System.out.println("Would you like to buy " + current.getTitle());
						Scanner kboard = new Scanner(System.in);
						
						if (kboard.nextLine().equals("yes") || kboard.nextLine().equals("Yes")) {
							if (myPlayer.getBalance() > current.getPrice()) {
								myPlayer.changeBalance(false, 150);
								current.addOwner(myPlayer);
								current.changeOwnershipStatus();
							}
							else {
								System.out.println("Unfortunatley you do not have enough money to purchase the property:" + current.getTitle());
							}
							
						}
						
					}
					
				}
				if (position == 2 || position == 17 || position == 33) {
					System.out.println("You are currently on the property " + "CommunityChest");
					String result = CommunityChest(myPlayer);
					System.out.print("You pulled the card: ");
					System.out.print(result);
					impactOfCommChest(result, myPlayer);
					System.out.println(" \" ");
				}
				if (position == 7 || position == 22 || position == 36) {
					System.out.println("You are currently on the property " + "Chance");
					String result2 = Chance(myPlayer);
					System.out.println(result2);
					impactOfChance(result2, myPlayer);		
				}
				if (position == 4) {
					System.out.println("You are currently on the property " + "Income Tax");
					myPlayer.changeBalance(false,  200);
					
				}
				if (position == 38) {
					System.out.println("You are currently on the property " + "Luxury Tax");
					myPlayer.changeBalance(false, 100);
					
				}
				if (PosInProperty(position)) {
					Property current = PropertyLookUp(position);
					MonopolyPlayer owner = current.getOwner();
					System.out.println("You are currently on the property " + current.getTitle());
					if (current.getOwnership() == true) {
						int Houses = current.getNumOfHouses();
						int rent = 0;
						if (Houses == 0) {
							rent = current.getRent();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}
						if (Houses == 1) {
							rent = current.getHouse1();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}
						if (Houses == 2) {
							rent = current.getHouse2();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}
						if (Houses == 3) {
							rent = current.getHouse3();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}
						if (Houses == 4) {
							rent = current.getHouse4();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}
						if (Houses == 5) {
							rent = current.getHouse5();
							myPlayer.changeBalance(false, rent);
							owner.changeBalance(true, rent);
						}	
						System.out.println("Due to the fact that you landed on thier property, you payed " + current.getOwner().getName() + " $ " + rent);
					}
					if (current.getOwnership() == false) {
						Scanner kboard = new Scanner(System.in);
						System.out.println("Your current balance is:" + myPlayer.getBalance());
						System.out.println("Would you like to buy " + current.getTitle() + " for " + current.getPrice() + " ?" );
						if (kboard.nextLine().equals("yes") || kboard.nextLine().equals("Yes")) {
							if (myPlayer.getBalance() < current.getPrice()) {
								System.out.print("Sorry, but you do not have enough money to purchase this property.");
							}
							current.changeOwnership();
							myPlayer.changeBalance(false, current.getPrice());
							current.addOwner(myPlayer);
							myPlayer.addProperties(current);
						}			
					}
					
				}
				if (BuildingPermitCheck(myPlayer)) {
					System.out.println("Would you like to build anyhouses?");
					Scanner kboard = new Scanner(System.in);
					String input2 = kboard.nextLine();
					if (input2.equals("yes") || input2.equals("Yes")) {
						System.out.println("Which property do you wish to build houses on?");
						printAllowedHouses(myPlayer);
						String Property = kboard.nextLine();
						int Pos = findPropertyPos(Property);
						Property current = PropertyLookUp(Pos);
						if (current.getOwner() == myPlayer) {
							System.out.println("How many properties do you wish to add to the property: " + current.getTitle());
							int houseNum = kboard.nextInt();
							int cost = (current.getHousePrice() * houseNum);
							if (myPlayer.getBalance() >= cost ) {
								myPlayer.changeBalance(false, cost);
								myPlayer.addProperties(current);
								
							}
							if (myPlayer.getBalance() < cost) {
								System.out.println("I am sorry but you are unable to buy that amount of houses");
								System.out.println("Your budget is: " + myPlayer.getBalance());
							}
						}
						System.out.println("You do not actually own the property: " +  current.getTitle());
					}

				}
				System.out.println("Your current balance is: " + myPlayer.getBalance());
				System.out.print("Your currently own: ");
				printOwnedSpaces(myPlayer);
				System.out.println("");
				System.out.println("Type 9, to end your turn");
				Scanner kboard3 = new Scanner(System.in);
				int end = kboard3.nextInt();
				if (end == 9) {
					PlayerTurn = false;
				}
				
			}
			
		}
		public static void ComputerPlay(MonopolyPlayer myBot) {
			//System.out.println(myPlayer.getName());
			//System.out.println(myPlayer.getProperties());
			//System.out.println(myPlayer.getPosition());
			//System.out.println(myPlayer.getBalance());
			boolean play = true;
			
			play = checkJail(myBot);
			
			if (myBot.getBalance() < 0) {
				play = false;
				Board.removePlayer(myBot);
			}
			
			if (play) {
			
			String name = myBot.getName();
			int roll = roll(myBot);
			myBot.changePosition(true, roll);
			int position = myBot.getPosition();
			
		
			if (position % 10 == 0) {
				System.out.println(name +  " is currently occupying the square: " + CornersLookUp(position).getTitle());
				if (position == 20) {
					CornersLookUp(position).payPlayerBalance(myBot);
					System.out.println(name + " has inherited money from landing on the Free Parking Square!");
				}
				if (position == 30) {
					System.out.println(name + " has been sent to jail");
					myBot.changePosition(false, 20);
				}
				if (position == 10) {
					jail(myBot);
				}
			}
			if (position % 10 == 5) {
				System.out.println(name + " is currently occupying the square: " + RailroadLookUp(position).getTitle());
				if (myBot.getBalance() > 500) {
					Railroad current = RailroadLookUp(position);
					System.out.println(name + " has bought the " + current.getTitle());
					current.addOwner(myBot);
					current.changeOwnershipStatus();
					myBot.changeBalance(false, 200);
				}
			}
			if (position == 12 || position == 28) {
				System.out.println(name + " is currently occupying the square "  + UtilityLookUp(position).getTitle());
				if (myBot.getBalance() > 500) {
					Utility current = UtilityLookUp(position);
					System.out.println(name + " has bought the " + current.getTitle());
					current.addOwner(myBot);
					current.changeOwnershipStatus();
					myBot.changeBalance(false, 150);
				}
			}
			if (position == 2 || position == 17 || position == 33) {
				System.out.println(name + " is currently occupying the square " + "CommunityChest");
				String result = CommunityChest(myBot);
				System.out.println(name + " recieved the card: " + result);
				impactOfCommChest(result, myBot);
			}
			if (position == 7 || position == 22 || position == 36) {
				System.out.println(name + " is currently occupying the square " + "Chance");
				String result2 = Chance(myBot);
				System.out.println(name + " recieved the card: " + result2);
				impactOfChance(result2, myBot);		
			}
			if (position == 4) {
				System.out.println(name + " is currently occupying the square " + "Income Tax");
				myBot.changeBalance(false,  200);
				
			}
			if (position == 38) {
				System.out.println(name + " is currently occupying the square " + "Luxury Tax");
				myBot.changeBalance(false, 100);
				
			}
			if (PosInProperty(position)) {
				Property current = PropertyLookUp(position);
				System.out.println(name + " is currently occupying the square " + current.getTitle());
				if (current.getOwnership() == true) {
					int Houses = current.getNumOfHouses();
					int rent = 0;
					if (Houses == 0) {
						rent = current.getRent();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getRent());
					}
					if (Houses == 1) {
						rent = current.getHouse1();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getHouse1());	
					}
					if (Houses == 2) {
						rent = current.getHouse2();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getHouse2());	
					}
					if (Houses == 3) {
						rent = current.getHouse3();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getHouse3());	
					}
					if (Houses == 4) {
						rent = current.getHouse4();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getHouse4());	
					}
					if (Houses == 5) {
						rent = current.getHouse5();
						myBot.changeBalance(false, rent);
						current.getOwner().changeBalance(true,  current.getHouse5());	
					}
					System.out.println(myBot.getName() + " landed on a property owned by: " + current.getOwner().getName() + " and payed them: " + rent);
				}
				if (current.getOwnership() == false) {
					Scanner kboard = new Scanner(System.in);
					if (myBot.getBalance() > current.getPrice()) {
						current.changeOwnership(); // needs to add player name into the property ***
						myBot.changeBalance(false, current.getPrice());
						current.addOwner(myBot);
						System.out.println(myBot.getName() + " bought the property " + current.getTitle());
					}
				}
				boolean buildersPermit = BuildingPermitCheck(myBot);
				//findLogicalBuilds(myBot);
				
			}	
			
			}
			
		System.out.println(myBot.getBalance());
		System.out.print("Computer currently owns:");
		printOwnedSpaces(myBot);
		System.out.println("");
		
		
			
		}	
		
		public static int roll(MonopolyPlayer myPlayer) {
			int priorPosition = myPlayer.getPosition();
			int dice1 = (int) (Math.random() * 6) + 1;
			int dice2 = (int) (Math.random() * 6) + 1;
			int roll = dice1 + dice2;
		
			System.out.println(myPlayer.getName() + " rolled a " + roll);
			int currentPos;
			currentPos = priorPosition;
			currentPos += roll;
			if (currentPos > 39) {
				myPlayer.changePosition(false, 40);
				myPlayer.changeBalance(true, 200);
			}
			return roll;
		}
		public static boolean PosInProperty(int myInt) {
			int[] PropertyNums = {1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
			int pos = myInt;
			for (int i = 0; i < PropertyNums.length; i++) {
				if (PropertyNums[i] == pos) {
					return true;
				}
			}
			return false;
			
		}
		public void Income () {		
		}
		public static void buildHouse(MonopolyPlayer myPlayer, Property myProperty, int myInt) {
			// int houseLimit = 35; // need to write implementation for this, but idk how
			// int hotelLimit = 15; // same thing
			myProperty.addHouse(myInt);	
			myPlayer.changeBalance(false, myProperty.getPrice());
			
		}
		
		public static String Chance(MonopolyPlayer myPlayer) {
			ArrayList<String> setOfChanceCards = new ArrayList<String>(); // all cards allowed in game in preset order
			ArrayList<String> deckOfChanceCards = new ArrayList<String>(); // cards in randomly generated order. So that it is realistic.
			setOfChanceCards = addChanceCards(setOfChanceCards);
			deckOfChanceCards = setChanceCards(setOfChanceCards, deckOfChanceCards);
			if (deckOfChanceCards.size() == 0) {
				deckOfChanceCards = setChanceCards(setOfChanceCards, deckOfChanceCards);
			}
			String CardPulled = deckOfChanceCards.remove(0);
			//impactOfChance(CardPulled, myPlayer);
			return CardPulled;
			
		}
		public static ArrayList<String> setChanceCards(ArrayList<String> myCards, ArrayList<String> myDeckOfCards) {
			
			for (int i = 0; i < 16; i++) {
			  	int size = myCards.size();
			 	int RandomOfSet = (int) (Math.random() * size - 1);
				myDeckOfCards.add(myCards.remove(RandomOfSet));
			}
			return myDeckOfCards;
			
		}
		public static ArrayList<String> addChanceCards(ArrayList<String> myCards) {
			myCards.add("Your Building Loan Matures. Collect 150.");
			myCards.add("Speeding Fine $15.");
			myCards.add("Bank pays you dividend of $50.");
			myCards.add("Go back three spaces.");
			myCards.add("Advance to the nearest railroad. If UNOWNED, you may buy it from the Bank. If OWNED, pay owner twice the rental to which they are otherwise entitiled");
			myCards.add("Go to Jail. Go directly to Jail, do not pass \"GO\", do not collect 200.");
			myCards.add("You have been elected chairman of the board. Pay each player $50");
			myCards.add("Advance to nearest utility. If UNOWNED, you may but it from the bank. If OWNED, throw dice and pay owner a total ten times amount thrown");
			myCards.add("Advance to \"GO\" (Collect $200) ");
			myCards.add("Advance to Illinois Avenue. If you pass \"GO \" collect $200.");
			myCards.add("Make general repairs on all your property: for each house pay $25, for each hotel pay $100.");
			myCards.add("Take a trip to Reading Railroad. If you pass \"GO\" Collect $200");
			myCards.add("Get out of jail free. This card may be kept until needed or traded");
			myCards.add("Advance to St. Charles Place. If you pass \"GO \" Collect $200.");
			myCards.add("Advance to the nearest Railroad. If UNOWNED, you may buy it from the Bank. If OWNED, pay the owner twice the rental to which they are otherwise entitled");
			myCards.add("Advance to Boardwalk");
			return myCards;
		}
		
		public static String CommunityChest(MonopolyPlayer myPlayer) {
			ArrayList<String> setOfCards = new ArrayList<String>(); // all cards allowed in game in preset order
			ArrayList<String> deckOfCards = new ArrayList<String>(); // cards in randomly generated order. So that it is realistic.
			setOfCards = addCommunityChestCards(setOfCards);
			deckOfCards = setCommunityChestCards(setOfCards, deckOfCards);
			if (deckOfCards.size() == 0) {
				deckOfCards = setCommunityChestCards(setOfCards, deckOfCards);
			}
			String cardPulled = deckOfCards.remove(0);
			return cardPulled;
			
		}
		public static ArrayList<String> setCommunityChestCards(ArrayList<String> myCards, ArrayList<String> myDeckOfCards) {
			
			for (int i = 0; i < 16; i++) {
			  	int size = myCards.size();
			 	int RandomOfSet = (int) (Math.random() * size - 1);
				myDeckOfCards.add(myCards.remove(RandomOfSet));
			}
			return myDeckOfCards;
			
		}
		public static ArrayList<String> addCommunityChestCards(ArrayList<String> myCards) {
			myCards.add("Doctor's Fees. Pay $50."); // done
			myCards.add("Get out of Jail Free. This card may be kept until needed or traded."); // this idk how i am going to implement - grants access to a method call? use a boolean to check if usable...
			myCards.add("Pay Hospital Fees of $100"); // done
			myCards.add("Pay School Fees of $50"); // done
			myCards.add("Holiday Fund Matures. Receive $100"); // done 
			myCards.add("You are assessed for street repairs: $40 per house, $115 per hotel"); // i know how to implement but code is not yet created under property class
			myCards.add("It is your birthday. Collect $10 from every player"); // difficult to implement gonna need to create more methods somewhere to achieve
			myCards.add("You have won second prize in a beauty contest. Collect $10."); // done
			myCards.add("Advance to GO. (Collect 200)"); // done but be careful about
			myCards.add("Bank error in your favor. Collect $200."); // done
			myCards.add("From sale of stock you get $50"); // done
			myCards.add("Life insurane matures. Collect $100."); // done
			myCards.add("Recieve $25 consultacy fee."); // done
			myCards.add("You inherit $100."); // done
			myCards.add("Go to Jail. Go directly to Jail, do not pass \"Go\", do not collect $200"); // done * make sure that there is no collect $200
			myCards.add("Income tax refund. Collect $20."); // done
			return myCards;
		}
		// myCards.add("Your Building Loan Matures. Collect 150."); done
		// myCards.add("Speeding Fine $15."); done
		//myCards.add("Bank pays you dividend of $50."); done
		//myCards.add("Go back three spaces."); done
		//myCards.add("Advance to the nearest railroad. If UNOWNED, you may buy it from the Bank. If OWNED, pay owner twice the rental to which they are otherwise entitiled"); rlly not sure how to implememt this
		//myCards.add("Go to Jail. Go directly to Jail, do not pass \"GO\", do not collect 200."); // done *
		//myCards.add("You have been elected chairman of the board. Pay each player $50"); // not fully sure how to implement this one
		//myCards.add("Advance to nearest utility. If UNOWNED, you may but it from the bank. If OWNED, throw dice and pay owner a total ten times amount thrown"); // not fully sure here either
		//myCards.add("Advance to \"GO\" (Collect $200) "); // done
		//myCards.add("Advance to Illinois Avenue. If you pass \"GO \" collect $200."); // done
		//myCards.add("Make general repairs on all your property: for each house pay $25, for each hotel pay $100."); // ik how to implement, but not completed yet
		//myCards.add("Take a trip to Reading Railroad. If you pass \"GO\" Collect $200"); done
		//myCards.add("Get out of jail free. This card may be kept until needed or traded"); // have an idea on how to implement
		//myCards.add("Advance to St. Charles Place. If you pass \"GO \" Collect $200."); // done
		//myCards.add("Advance to the nearest Railroad. If UNOWNED, you may buy it from the Bank. If OWNED, pay the owner twice the rental to which they are otherwise entitled"); // yeah idk how i am going to implement the second part
		//myCards.add("Advance to Boardwalk");
		public static void impactOfChance(String myCard, MonopolyPlayer myPlayer) {
			Corners current = CornersLookUp(20);
			if (myCard.equals("Your Building Loan Matures. Collect 150.")) {
				myPlayer.changeBalance(true, 150);
			}
			if (myCard.equals("Speeding Fine $15.")) {
				myPlayer.changeBalance(false, 15);
			}
			if (myCard.equals("Bank pays you dividend of $50.")) {
				myPlayer.changeBalance(true, 50);
			}
			if (myCard.equals("Go back three spaces.")) {
				myPlayer.changePosition(false, 3);
			}
			if (myCard.equals("Go to Jail. Go directly to Jail, do not pass \"GO\", do not collect 200.")) {
				myPlayer.newPosition(10);
				jail(myPlayer);
				//checkProperty(1);
			}
			if (myCard.equals("Advance to \"GO\" (Collect $200) ")) {
				myPlayer.newPosition(0);
				myPlayer.changeBalance(true, 200);
			}
			if (myCard.equals("Advance to Illinois Avenue. If you pass \"GO \" collect $200.")) {
				int current2 = myPlayer.getPosition();
				if (current2 > 24) {
					myPlayer.changeBalance(true, 200);
					checkProperty(1);
				}	
				myPlayer.newPosition(24);
			}
			if (myCard.equals("Take a trip to Reading Railroad. If you pass \"GO\" Collect $200")) {
				int current2 = myPlayer.getPosition();
				if (current2 > 5) {
					myPlayer.changeBalance(true, 200);
					checkProperty(1);
				}
				myPlayer.newPosition(5);
			}
			if (myCard.equals("Advance to St. Charles Place. If you pass \"GO \" Collect $200.")) {
				int current2 = myPlayer.getPosition();
				if (current2 > 11) {
					myPlayer.changeBalance(true, 200);
					checkProperty(1);
				}
				myPlayer.newPosition(11);
			}
			if (myCard.equals("Advance to Boardwalk")) {
				myPlayer.newPosition(39);
				checkProperty(1);
			}
			// still need to complete the rest of the cards
			
		}
		
		public static void impactOfCommChest(String myCard, MonopolyPlayer myPlayer) {
			Corners current = CornersLookUp(20);
			if (myCard.equals("\"Doctor's Fees. Pay $50.\"")) {
				myPlayer.changeBalance(false, 50);
				current.addToBankTotal(50);
				
			}
			if (myCard.equals("Pay Hospital Fees of $100")) {
				myPlayer.changeBalance(false, 100);
				current.addToBankTotal(100); 
			}
			if (myCard.equals("Pay School Fees of $50")) {
				myPlayer.changeBalance(false, 50);
				current.addToBankTotal(50);
			}
			if (myCard.equals("Holiday Fund Matures. Receive $100")) {
				myPlayer.changeBalance(true, 100);
			}
			if(myCard.equals("You have won second prize in a beauty contest. Collect $10.")) {
				myPlayer.changeBalance(true, 10);
			}
			if (myCard.equals("Advance to GO. (Collect 200)")) {
				myPlayer.changeBalance(true, 200);
				myPlayer.newPosition(0);
			}
			if (myCard.equals("Bank error in your favor. Collect $200.")) {
				myPlayer.changeBalance(true, 200);
			}
			if (myCard.equals("From sale of stock you get $50")) {
				myPlayer.changeBalance(true, 50);
			}
			if (myCard.equals("Life insurane matures. Collect $100.")) {
				myPlayer.changeBalance(true, 100);
			}
			if (myCard.equals("Recieve $25 consultacy fee.")) {
				myPlayer.changeBalance(true, 25);
			}
			if (myCard.equals("You inherit $100.")) {
				myPlayer.changeBalance(true, 100);
			}
			if (myCard.equals("Go to Jail. Go directly to Jail, do not pass \"Go\", do not collect $200")) {
				myPlayer.newPosition(10);
				jail(myPlayer);
			}
			if (myCard.equals("Income tax refund. Collect $20.")) {
				myPlayer.changeBalance(true, 20);
			}
			if (myCard.equals("Get out of Jail Free. This card may be kept until needed or traded.")) {
				myPlayer.addOutOfJailCard();
			}
			if (myCard.equals("It is your birthday. Collect $10 from every player")) {
				ArrayList<MonopolyPlayer> Players = new ArrayList<MonopolyPlayer>();
				Players = Board.getPlayers();
				for (int i = 0; i < Players.size(); i++) {
					MonopolyPlayer current2 = Players.get(i);
					if (!current2.getName().equals(myPlayer.getName())) {
						current2.changeBalance(false, 10);
						myPlayer.changeBalance(true, 10);
					}
				}
			}
			if (myCard.equals("You are assessed for street repairs: $40 per house, $115 per hotel")) {
				ArrayList<Property> Properties = myPlayer.getProperties();
				for (int i = 0; i < Properties.size(); i++) {
					Property current2 = Properties.get(i);
					int num = current2.getNumOfHouses();
					if (num == 5) {
						myPlayer.changeBalance(false, 115);
						current.addToBankTotal(100);
					}
					if (num == 4) {
						myPlayer.changeBalance(false, 160); // I did not know that 4 houses was more expensive than one hotel. maybe change that. 
						current.addToBankTotal(160);
					}
					if (num == 3) {
						myPlayer.changeBalance(false,  120);
						current.addToBankTotal(120);
					}
					if (num == 2) {
						myPlayer.changeBalance(false, 80);
						current.addToBankTotal(80);
					}
					if (num == 1) {
						myPlayer.changeBalance(false, 40);
						current.addToBankTotal(40);
					}
							
				}
					
			}
			
				
				
		}
		public static void checkProperty(int myInt) { // only for if the player lands on the property due to a chance card.
			// need to write this class
					
		}
		public static int numRailroads(MonopolyPlayer myPlayer) {
			ArrayList<Railroad> current = myPlayer.getRailroads();
			return current.size();
			
		}
		
		public static int numUtilities(MonopolyPlayer myPlayer) {
			ArrayList<Utility> current = myPlayer.getUtilities();
			return current.size();
		}
		public static boolean BuildingPermitCheck(MonopolyPlayer myPlayer) {
			ArrayList<Property> current = myPlayer.getProperties();
			if (current.size() > 2) {
				return false;
			}	
			for (int i = 0; i < current.size(); i++) {
				String clr = current.get(i).getColor();
				for (int j = i + 1; j < current.size(); j++) {
					String clr2 = current.get(i).getColor();
					if (clr.equals(clr2) && clr.equals("brown")) {
						return true;
					}
					if (clr.equals(clr2) && clr.equals("blue")) {
						return true;
					}
					if (clr.equals(clr2)) {
						for (int k = j + 1; k < current.size(); k++) {
							String clr3 = current.get(k).getColor();
							if (clr.equals(clr2) && clr2.equals(clr3)) {
								myPlayer.addBuildingColor(clr);
								return true;
							}
						}
					}
				}
			}
			return false;	
		}		
		
		public static ArrayList<MonopolyPlayer> generatePlayers(int myInt) {
			
			ArrayList<MonopolyPlayer> RawPlayers = new ArrayList<MonopolyPlayer>();
			MonopolyPlayer user = new MonopolyPlayer("You");
			RawPlayers.add(user);
			
			if (myInt > 0) {
				MonopolyPlayer Computer1 = new MonopolyPlayer("Computer 1");
				RawPlayers.add(Computer1);
				if (myInt > 1) {
					MonopolyPlayer Computer2 = new MonopolyPlayer("Computer 2");
					RawPlayers.add(Computer2);
					if (myInt > 2) {
						MonopolyPlayer Computer3 = new MonopolyPlayer("Computer 3");
						RawPlayers.add(Computer3);
						if (myInt > 3) {
							MonopolyPlayer Computer4 = new MonopolyPlayer("Computer 4");
							RawPlayers.add(Computer4);
							if (myInt > 4) {
								MonopolyPlayer Computer5 = new MonopolyPlayer("Computer 5");
								RawPlayers.add(Computer5);
								if (myInt > 5) {
									MonopolyPlayer Computer6 = new MonopolyPlayer("Computer 6");
									RawPlayers.add(Computer6);
									if (myInt > 6) {
										MonopolyPlayer Computer7 = new MonopolyPlayer("Computer 7");
										RawPlayers.add(Computer7);
										if (myInt > 7) {
											MonopolyPlayer Computer8 = new MonopolyPlayer("Computer 8");
											RawPlayers.add(Computer8);
											if (myInt > 8) {
												MonopolyPlayer Computer9 = new MonopolyPlayer("Computer 9");
												RawPlayers.add(Computer9);
												if (myInt > 9) {
													MonopolyPlayer Computer10 = new MonopolyPlayer("Computer 10");
													RawPlayers.add(Computer10);
												}	
											}	
										}	
									}	
								}	
							}	
						}	
					}		
				}
			}
			
			ArrayList<MonopolyPlayer> Players = new ArrayList<MonopolyPlayer>();
			
			while (RawPlayers.size() > 0) {
				int random = (int) (Math.random() * RawPlayers.size());
				Players.add(RawPlayers.remove(random));		
			}
			
			return Players;
			
		}

		public static Property PropertyLookUp(int myInt) {
			
			ArrayList<Property> Properties = Board.getProperties();
			for (int i = 0; i < Properties.size(); i++) {
				if (Properties.get(i).getPosition() == myInt) {
					return Properties.get(i);
				}
			}
			
			return null;
			
		} 
		
		public static Corners CornersLookUp (int myInt) {
			
			ArrayList<Corners> Corners = Board.getCorners();
			
			for (int i = 0; i < Corners.size(); i++) {
				if (Corners.get(i).getPosition() == myInt) {
					return Corners.get(i);
				}
			}
		
			return null;
	
		}
		
		public static Railroad RailroadLookUp (int myInt) {
			
			ArrayList<Railroad> Railroads = Board.getRailroads();
			
			for (int i = 0; i < Railroads.size(); i++) {
				if (Railroads.get(i).getPosition() == myInt) {
					return Railroads.get(i);
				}
				
			}
			
			return null;
			
		}
		public static Utility UtilityLookUp(int myInt) {
			
			ArrayList<Utility> Utilities = Board.getUtilities();
			
			for (int i = 0; i < Utilities.size(); i++) {
				if (Utilities.get(i).getPosition() == myInt) {
					return Utilities.get(i);
				}
			}
			
			return null;
			
		}
		
		public static int findPropertyPos(String myString) {
			String[] board = Board.getBoardProperties();
			for (int i = 0; i < board.length; i++) {
				if(board[i].equals(myString)) {
					return i;
				}
			}
			return -1;
		}
		
		public static void printOwnedSpaces(MonopolyPlayer myPlayer) {
			
			ArrayList<String> OwnedSpaces = findOwnedSpaces(myPlayer);
			
			for (int i = 0; i < OwnedSpaces.size(); i++) {
				if (i == OwnedSpaces.size() - 1) {
					System.out.print(OwnedSpaces.get(i));
				}
				else {
					System.out.print(OwnedSpaces.get(i) + " + ");
				}
				
			}
			
		}
		
		public static ArrayList<String> findOwnedSpaces(MonopolyPlayer myPlayer) {
			
			ArrayList<String> OwnedSquares = new ArrayList<String>();
			
			ArrayList<Property> Properties = Board.getProperties();
			ArrayList<Utility> Utilities = Board.getUtilities();
			ArrayList<Railroad> Railroads = Board.getRailroads();
			
			for (int i = 0; i < Properties.size(); i++) {
				if (Properties.get(i).getOwnership()) {
					if (Properties.get(i).getOwner().equals(myPlayer)) {
						OwnedSquares.add(Properties.get(i).getTitle());
					}
				}
				
			}
			
			for (int i = 0; i < Utilities.size(); i++) {
				if (Utilities.get(i).getOwnership()) {
					if (Utilities.get(i).getOwner().equals(myPlayer)) {
						OwnedSquares.add(Utilities.get(i).getTitle());
					}
				}
				
			}
			
			for (int i = 0; i < Railroads.size(); i++) {
				if (Railroads.get(i).getOwnership()) {
					if (Railroads.get(i).getOwner().equals(myPlayer)) {
						OwnedSquares.add(Railroads.get(i).getTitle());
					}
				}	
			}
			
			return OwnedSquares;
		}
		public static void createPlayer(ArrayList<MonopolyPlayer> myPlayers, int myInt) {
			System.out.println("Enter the name of the player " + (myInt + 1) + ":");
			Scanner kboard = new Scanner(System.in);
			String playerName = kboard.nextLine();
			for (int i = 0; i < myPlayers.size(); i++) {
				String name = myPlayers.get(i).getName();
				if (name.equals(playerName)) {
					System.out.println("This exact name has already been entered. \nPlease use an alternate name, or include an initial or last name \nFor your reference, the name that could not be entered was: " + playerName);
					playerName = kboard.nextLine();
				}
			}
			MonopolyPlayer x = new MonopolyPlayer(playerName);
			myPlayers.add(x);
		}
		public static ArrayList<MonopolyPlayer> randomizePlayers(ArrayList<MonopolyPlayer> Players, int num) {
			
			ArrayList<MonopolyPlayer> myPlayers = new ArrayList<MonopolyPlayer>();
			
			while (myPlayers.size() != num) {
				int size = Players.size();
				int random = (int) (Math.random() * size);
				myPlayers.add(Players.remove(random));
			}
			
			return myPlayers;
		}
		
		public static MonopolyPlayer loadPlayer() {
			Scanner kboard = new Scanner(System.in);
			System.out.println("What is the name of the player?");
			String name = kboard.nextLine();
			System.out.println("How much money does this player have?");
			int balance = kboard.nextInt();
			
			int position = grabPosition();
			
			System.out.println("How many Get Out of Jail Free cards does this player own?");
			int cards = kboard.nextInt();
			ArrayList<Property> properties = new ArrayList<Property>();
			System.out.println("Enter their properties one by one");
			System.out.println("Type 0, when you have finished.");
			while (kboard.nextInt() != 0) {
				String Property = kboard.nextLine();
				properties.add(PropertyLookUp(findPropertyPos(Property)));	
			}
			ArrayList<Railroad> railroads = new ArrayList<Railroad>();
			System.out.println("Enter their railroads one by one");
			System.out.println("Type 0, when you have finished.");
			while (kboard.nextInt() != 0) {
				String Railroad = kboard.nextLine();
				railroads.add(RailroadLookUp(findRailRoadPos(Railroad)));	
			}
			ArrayList<Utility> utilities = new ArrayList<Utility>();
			System.out.println("Enter their utilities one by one");
			System.out.println("Type 0, when you have finished.");
			while (kboard.nextInt() != 0) {
				String Railroad = kboard.nextLine();
				railroads.add(RailroadLookUp(findRailRoadPos(Railroad)));	
			}
			
			
			
			
			
			
			// String myName, int myBalance, ArrayList<Property> myProperties, ArrayList<Railroad> myRailroads, ArrayList<Utility> myUtilities, int myPosition, int myJailCard
			MonopolyPlayer a = new MonopolyPlayer(name, balance, properties, railroads, utilities, position, cards);
			return a;
		}
		
		public static int grabPosition() {
			System.out.println("What square is the player currently occupying?");
			Scanner kboard = new Scanner(System.in);
			String input = kboard.nextLine();
			int pos = -1;
			
			String[] setBoard = Board.loadNormalProperties();
			
			System.out.println(input);
			//System.out.println(setBoard[10]);
			//System.out.println(setBoard[15]);
			//System.out.println(setBoard[21]);
			
			for (int i = 0; i < setBoard.length; i++) {
				String current = setBoard[i + 1];
				
				System.out.println(current);
				
				// error is due to the fact that current is null. so it cannot be .equalsed
				
				System.out.println(input);
				
				if (current.equals(input)) {
					pos = i;
				}
			}
			
			
			return pos;
			
		}
		
		public static int findRailRoadPos(String railroad) {
			ArrayList<Railroad> Railroads = Board.loadRailroads();
			for (int i = 0; i < Railroads.size(); i++) {
				String current = Railroads.get(i).getTitle();
				if (current.equals(railroad)) {
					return Railroads.get(i).getPosition();
				}
			}
			return -1;	
			
		}
		
		public static int findUtiliyPos(String utility) {
			ArrayList<Utility> Utilities = Board.loadUtilities();
			for (int i = 0; i < Utilities.size(); i++) {
				String current = Utilities.get(i).getTitle();
				if (current.equals(utility)) {
					return Utilities.get(i).getPosition();
				}
			}
			return -1;	
			
		}
		
		public static void jail(MonopolyPlayer player) {
			player.changeJailStatus();
			
		}
		
		public static boolean checkJail(MonopolyPlayer myPlayer) {
			
			while (myPlayer.getJailStatus()) {
				
				System.out.println("Unfortunately, you are in jail. Fortunatley, you have options to get out!");
				System.out.println("1. You may roll the dice, and if you roll doubles, you will be set free");
				System.out.println("2. You may play a Get Out of Jail Free Card, and you will be set free");
				System.out.println("3. You may pay a fine of $50, and you will be set free");
				System.out.println("Enter your preffered option");
				Scanner kboard = new Scanner(System.in);
				int input = kboard.nextInt();
				
				if (input == 1) {
					System.out.println("Type 0 to roll");
					int roll = kboard.nextInt();
					if (roll == 0) {
						int dice1 = (int) (Math.random() * 6) + 1;
						int dice2 = (int) (Math.random() * 6) + 1;
						if (dice1 == dice2) {
							myPlayer.changePosition(true, (dice1 + dice2));
							kboard.close();
							return true;
						}
					}
					
					
				}
				
				if (input == 2) {
					if (myPlayer.findJailCard()) {
						kboard.close();
						return true;
						
					}
				}
				
				if (input == 3) {
					myPlayer.changeBalance(false, 50);
					kboard.close();
					return true;
				}
				kboard.close();
				return false;	
				
			}
			
			return true;
		}
		public static void printAllowedHouses(MonopolyPlayer myPlayer) {
			
			ArrayList<String> colors = myPlayer.getBuildingPermitColors();
			System.out.println("You can print on the following properties:");
			
			for (int i = 0; i < colors.size(); i++) {
				
				String current = colors.get(i);
				ArrayList<Property> allProperties = Board.loadProperties();
				
				for (int j = 0; j < allProperties.size(); j++) {
					
					Property cursor = allProperties.get(j);
					String color = cursor.getColor();
					
					if (color.equals(current)) {
						System.out.println(cursor.getTitle());
					}				
				}			
			}	
		}
}
