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

import java.util.ArrayList;
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
	private int roundCount;
	public int transCount = 1;

	ArrayList<Player> players = new ArrayList<Player>();
	private static Queue<Player> playerQ = new LinkedList<Player>();
	public int currentPlayers = 0;
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
		do {
			try {
				System.out.print("\nGame Menu\n");
				System.out.println("1. Add a player to the game");
				System.out.println("2. Play one round");
				System.out.println("3. Game status");
				System.out.println("4. Return to the main menu");
				System.out.print("\nOption -->");
				choice = scan.nextInt();

				switch (choice) {
				case 1:
					int loops;
					System.out.println("\nThere are currently " + playerQ.size() + " players in line.");
					System.out.print("How many players would you like to add?  --> ");
					loops = scan.nextInt();
					for (int i = 0; i < loops; i++) {
						addPlayer();
					}
					break;
				case 2:
					//Run through each player menu individually
					roundCount++;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals("Player")) { // Purely aesthetics to allow player names if VIP
							System.out.println("\nPlayer " + (i + 1));
							System.out.println("Players balance: " + players.get(i).getMoney());
						} else {
							System.out.println("\nPlayer " + (i + 1) + " " + players.get(i).getName());
							System.out.println(players.get(i).getName() + "'s balance: " + players.get(i).getMoney());
						}
						players.get(i).makeBet(scan, minBet, maxBet);
						//remove players that decide to quit
						if (!players.get(i).isPlaying()) {
							System.out.println("Goodbye " + players.get(i).getName() + ", have a great day!");
							removePlayer(players.get(i));
						}
					}

					Wheel.spin();
					//Pay players that decided to play this round if they won.
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).inThisRound()) {
							houseMoney += players.get(i).betTotalThisRound();
							//System.out.println(houseMoney);
							players.get(i).payment();
							houseMoney -= players.get(i).wonThisRound();
							//System.out.println(houseMoney);
						}
					}
					break;
				case 3:
					System.out.println("Game: " + name);
					System.out.println("Initital game balance: " + startingHouseMoney);
					System.out.println("Current game balance: " + houseMoney);
					System.out.println("Current players in " + name);
					for (int i = 0; i < players.size(); i++) {
						System.out.print("Player " + (i+1) + " ");
						System.out.println(players.get(i).toString());
					}
					break; // TODO add transaction history and game information.
				case 4:
					break;
				default:
					System.out.println("Please enter a valid selection.");
				}
			} catch (NoSuchElementException exception) {
				System.out.println("The queue is empty.");
			}
		} while (choice != 4);
	}

	public void addPlayer() {
		players.add(playerQ.remove());
		if (players.get(players.size()-1).getName().equals("Player")) { // Recently added player
			System.out.println("A player was added to the game.");
		} else {
			System.out.println(players.get(players.size()-1).getName() + " was added to the game.");
		}
		currentPlayers++;
	}

	public void removePlayer(Player p) {
		players.remove(p);
		currentPlayers--;
	}

	public String getVersion() {
		return name;
	}
	public void getReport() //no idea how we were supposed to use the transactions
	{
		System.out.println("Game: " + name);
		System.out.println("Initital game balance: " + startingHouseMoney);
		System.out.println("Ending game balance: " + houseMoney);
		System.out.println("Winning/Losing amount: " + (houseMoney - startingHouseMoney));
		for(int i = 0; i<roundCount;i++)
		{
			System.out.println("Round " + (i+1) /*Wheel result?*/ );
			System.out.println("Trans\t" + "Player\t" + "BetAmount\t" + "Bet Type\t" + "Pay");
			for(int j = 0; j<players.size(); j++)
			{
				System.out.println((transCount) + "\t" + players.get(j).getName() 
						+ "\t" + players.get(j).getbetTotal()  + "\t" + /*Bettype?*/ "\t" 
						+ players.get(j).getWinning());
				transCount++; //public keep count of trans though we were supposed to use Transactions right?
			}
		}
	}
}
