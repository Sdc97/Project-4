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
	
	ArrayList<Player> players = new ArrayList<Player>();
	private static Queue<Player> playerQ = new LinkedList<Player>();
	public int currentPlayers = 0;
	private Scanner scan = new Scanner(System.in);
	
	public Game (String name, int min, int max, int house) {
		this.name = name;
		minBet = min;
		maxBet = max;
		houseMoney = house;
	}
	
	public static void addToQueue(Player p) {
		playerQ.add(p);
	}
	
	public void gameMenu() {
		int choice;
		try {
			System.out.print("\nGame Menu\n");
			System.out.println("1. Add a player to the game");
			System.out.println("2. Play one round");
			System.out.println("3. Game status");
			System.out.println("4. Return to the main menu");
			choice = scan.nextInt();
		
			switch(choice) {
			case 1: 
				addPlayer();
			}
		}
		catch(NoSuchElementException exception) {
			System.out.println("The queue is empty.");
		}
	}
	
	public void addPlayer() {
		players.add(playerQ.remove());
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
}
