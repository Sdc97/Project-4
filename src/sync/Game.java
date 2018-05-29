/*  Java Program: 
	Modified by:	Steven Calvert
	Class:	
	Date:	
	Description: 

	I certify that the code below is modified by me.

	Exception(s): N/A

*/
package sync;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
	private String name;
	Player[] players = new Player[5];
	private static Queue<Player> playerQ = new LinkedList<Player>();
	public int currentPlayers;
	private Scanner scan = new Scanner(System.in);
	
	public Game (String name) {
		this.name = name;
	}
	
	public static void addToQueue(Player p) {
		playerQ.add(p);
	}
	
	public void gameMenu() {
		String choice;
		System.out.print("\nGame Menu\n");
		System.out.println("1. Add a player to the game");
		System.out.println("2. Play one round");
		System.out.println("3. Game status");
		System.out.println("4. Return to the main menu");
		
	}
	
}
