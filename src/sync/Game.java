/*  Java Program: 
	Modified by:	Steven Calvert
	Class:	
	Date:	
	Description: 

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
						ArrayList<Integer> playerBets = players.get(i).getBets();
						if (players.get(i).inThisRound()) {
							for (int j = 0; i < playerBets.size(); i++) {
								houseMoney += playerBets.get(j);
								players.get(i).payment();
								houseMoney -= players.get(i).wonThisRound(playerBets.get(j));
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
	
}
