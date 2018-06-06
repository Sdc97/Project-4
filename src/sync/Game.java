/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: Game.java
	Date: 6/5/2018
	Description: Contain the game files which havearray list and queue for
	players. Calculate the housewinning and obtain data from player

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Game {
	private String name;
	private int minBet;
	private int maxBet;
	private int houseMoney;
	private int startingHouseMoney;
	private int roundCount = 0;
	public int transCount = 1;
	public ArrayList<ArrayList<Transactions>> transaction = new ArrayList<ArrayList<Transactions>>();
	public ArrayList<String> roundResults = new ArrayList<String>();
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private static Queue<Player> playerQ = new LinkedList<Player>();
	public int currentPlayers = 0;
	public int playerNumberCount = 0;
	private Scanner scan = new Scanner(System.in);

	public Game(String name, int min, int max, int house) {
		this.name = name;
		minBet = min;
		maxBet = max;
		houseMoney = house;
		startingHouseMoney = houseMoney;
	}

	public static void addToQueue(Player p) {
		playerQ.add(p);
	}

	public void gameMenu() {
		int choice = 0;
		System.out.println("\nWelcome to the game: " + name);
		System.out.println("Minimum bet: " + minBet);
		System.out.println("Maximum bet: " + maxBet);
		System.out.println("Current Players: " + currentPlayers);
		do {
			try {
				try {
				System.out.print("\nGame Menu\n");
				System.out.println("1. Add a player to the game");
				System.out.println("2. Play one round");
				System.out.println("3. Game status");
				System.out.println("4. Return to the main menu");
				System.out.print("\nOption -->");
				choice = scan.nextInt();
				} catch (InputMismatchException exception) {
					System.out.println("\nInvalid input received.\n");
					scan = new Scanner(System.in);
				}

				switch (choice) {
				case 1:
					try {
						int loops;
						System.out.println("\nThere are currently " + playerQ.size() + " players in line.");
						System.out.print("How many players would you like to add?  --> ");
						loops = scan.nextInt();
						for (int i = 0; i < loops; i++) {
							addPlayer();
						}
					} catch (InputMismatchException exception) {
						System.out.println("\nInvalid input received. Please use indicated options.\n");
						scan = new Scanner(System.in);
					}
					break;
				case 2:
					//Run through each player menu individually
					roundCount++;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals("Player")) { // Purely aesthetics to allow player names if VIP
							System.out.println("\nPlayer " + players.get(i).getPlayerNumber());
							System.out.println("Players balance: " + players.get(i).getMoney());
						} else {
							System.out.println("\nPlayer " + players.get(i).getPlayerNumber() + " " + players.get(i).getName());
							System.out.println(players.get(i).getName() + "'s balance: " + players.get(i).getMoney());
						}
						players.get(i).makeBet(scan, minBet, maxBet);
						//remove players that decide to quit
						if (!players.get(i).isPlaying()) {
							System.out.println("Goodbye " + players.get(i).getName() + ", have a great day!");
							removePlayer(players.get(i));
							i -= 1;
						}
					}

					Wheel.spin();
					roundResults.add(Wheel.getResult());
					//Pay players that decided to play this round if they won.
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).inThisRound()) {
							houseMoney += players.get(i).betTotalThisRound();
							players.get(i).payment();
							houseMoney -= players.get(i).wonThisRound();
						}
					}
					transaction.add(new ArrayList<Transactions>());
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).inThisRound()) {
							ArrayList<Integer> bets = players.get(i).getBets();
							ArrayList<String> betTypes = players.get(i).getBetTypes();
							ArrayList<Integer> roundWinnings = players.get(i).getRoundWinnings();
							for (int j = 0; j < bets.size(); j++) {
								transaction.get(roundCount - 1).add(new Transactions(transCount, players.get(i).getPlayerNumber(), bets.get(j),
										betTypes.get(j), 
										roundWinnings.get(j)));
								transCount++;
							}
						}
					}
					break;
				case 3:
					System.out.println("Game: " + name);
					System.out.println("Initital game balance: " + startingHouseMoney);
					System.out.println("Current game balance: " + houseMoney);
					System.out.println("Current players in " + name);
					for (int i = 0; i < players.size(); i++) {
						System.out.print("Player " + players.get(i).getPlayerNumber() + " ");
						System.out.println(players.get(i).toString());
					}
					break; // TODO add transaction history and game information.
				case 4:
					break;
				default:
					System.out.println("Please enter a valid selection.");
				}
			} 
			catch (NoSuchElementException exception) {
				System.out.println("The queue is empty.");
				scan = new Scanner(System.in);
			}  
		} while (choice != 4);
	}

	public void addPlayer() {
		playerNumberCount++;
		players.add(playerQ.remove());
		players.get(players.size()-1).setPlayerNumber(playerNumberCount);
		if (players.get(players.size()-1).getName().equals("Player")) { // Recently added player
			System.out.println("A player was added to the game.");
		} else {
			System.out.println(players.get(players.size()-1).getName() + " was added to the game.");
		}
		currentPlayers++;
	}

	public void removePlayer(Player p) {
		if (!p.getName().equals("Player")) {
			p.addMoney(p.bonus());
			System.out.println("\n" + p.getName() + " got 5% back in credit for being a rewards member!");
			if(p.isSuper()) {
				p.addMoney(p.superBonus());
				System.out.println(p.getName() + " also got $" + p.superBonus() + " back for being a platinum rewards member!");
			}
			System.out.println("Total earned in rewards this session for " + p.getName() + ": " + (p.bonus()+ p.superBonus()));
		}
		players.remove(p);
		currentPlayers--;
	}
	
	public void removeAllPlayers() {
		for(int i = 0; i < players.size(); i++) {
			removePlayer(players.get(i));
		}
	}

	public String getVersion() {
		return name;
	}
	
	public void getReport(FileWriter fileOut)
	{
		PrintWriter printWriter = new PrintWriter(fileOut);
		System.out.println("Game: " + name);
		printWriter.println("Game: " + name);
		System.out.println("Initital game balance: " + startingHouseMoney);
		printWriter.println("Initital game balance: " + startingHouseMoney);
		System.out.println("Ending game balance: " + houseMoney);
		printWriter.println("Ending game balance: " + houseMoney);
		System.out.println("Winning/Losing amount: " + (houseMoney - startingHouseMoney));
		printWriter.println("Winning/Losing amount: " + (houseMoney - startingHouseMoney));
		for(int i = 0; i < roundCount; i++)
		{
			System.out.println("\nRound " + (i+1) + "(" + roundResults.get(i) + ")" );
			printWriter.println("\nRound " + (i+1) + "(" + roundResults.get(i) + ")" );
			System.out.println("Trans\t" + "Player\t\t" + "BetAmount\t" + "Bet Type\t" + "Pay");
			printWriter.printf("%-10s %-10s %-10s %-10s %-10s%n", "Trans", "Player", "BetAmount", "BetType", "Pay");
			for(int j = 0; j < transaction.get(i).size(); j++)
			{
				int transNumber = transaction.get(i).get(j).getTrans();
				int playerNumber = transaction.get(i).get(j).getName();
				int betNumber = transaction.get(i).get(j).getBetAmount();
				String thisBetType = transaction.get(i).get(j).getBetType();
				int thisPay = transaction.get(i).get(j).getPay();
				System.out.println("  " + (transNumber) + "\t  " + playerNumber 
						+ "\t\t   " + betNumber  + "\t\t   " + thisBetType + "\t\t" 
						+ thisPay);
				printWriter.printf("  %-10d %-12d %-8d %-9s %-9d%n", transNumber, playerNumber, betNumber, thisBetType, thisPay);
			}
		}
		printWriter.close();
	}
}
