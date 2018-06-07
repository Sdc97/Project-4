/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: MainMenu.java
	Date: 6/5/2018
	Description: Contain the main menu and 
	the list of games for the game class

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MainMenu 
{

	public static void main(String[] args) throws IOException
	{
		String choice,id,name1,name2;
		int playerType,chips;
		ArrayList<Game> allGames = new ArrayList<Game>();
		Scanner scan = new Scanner(System.in);
		Scanner inFile = new Scanner(new File("players-1.txt"));
		Scanner games = new Scanner(new File("games-1.txt"));
		System.out.println("Initialize Games. Please wait...");
		String name;
		int minBet, maxBet, houseMoney, totalGames;
		String currentGameList;
		while (games.hasNextLine()) {
			name = games.next();
			totalGames = games.nextInt();
			
			for (int i = 0; i < totalGames; i++) {
				String version = name + (i+1);
				games.nextLine();
				minBet = games.nextInt();
				maxBet = games.nextInt();
				houseMoney = games.nextInt();
				allGames.add(new Game(version, minBet, maxBet, houseMoney));
			}
			if(games.hasNextLine()) {
				games.nextLine();
			}
		}
		System.out.println("All games are ready.");
		currentGameList = "Available games: ";
		for(int i = 0; i < allGames.size(); i++) {
			currentGameList += allGames.get(i).getVersion() + ", ";
		}
		System.out.println(currentGameList.substring(0, currentGameList.length()-2));
		System.out.println("Welcome to the roulette table");
		System.out.println("\nMain Menu\n1. Select a game\n"
				+ "2. Add a new player to the list\n3. Quit");
		System.out.print("\nOption --> ");
		choice = scan.next();
		
		while (!choice.equals("3")) {
			try {
				if (choice.equals("1")) {
					String versionChoice;
					System.out.print("Select a game --> ");
					versionChoice = scan.next();
					for (int i = 0; i < allGames.size(); i++) {
						if (allGames.get(i).getVersion().equals(versionChoice)) {
							allGames.get(i).gameMenu();
						}
					}

				} else if (choice.equals("2")) {
					int loops;
					System.out.print("How many players would you like to add?  --> ");
					loops = scan.nextInt();
					for (int i = 0; i < loops; i++) {
						if (inFile.hasNextLine()) {
							playerType = Integer.parseInt(inFile.next());
							chips = Integer.parseInt(inFile.next());

							if (playerType == 0) {
								Player p = new Player(chips);
								Game.addToQueue(p);
								System.out.println("A player was added to the line to play.");
							} else if (playerType == 1) {
								id = inFile.next();
								name1 = inFile.next();
								name1 += " " + inFile.next();
								Player p = new VIP(name1, id, chips);
								Game.addToQueue(p);
								System.out.println(name1 + " was added to the line to play.");
							} else if (playerType == 2) {
								id = inFile.next();
								name1 = inFile.next();
								name1 += " " + inFile.next();
								Player p = new Super(name1, id, chips);
								Game.addToQueue(p);
								System.out.println(name1 + " was added to the line to play.");
							}

						} else {
							System.out.println("The line is empty! There are no more players waiting to play.");
						}
					}
				} else
					System.out.println("Please enter 1, 2, or 3.");

				System.out.println("\nMain Menu\n1. Select a game\n" + "2. Add a new player to the list\n3. Quit");
				System.out.print("\nOption --> ");
				choice = scan.next();
			} catch (InputMismatchException exception) {
				System.out.println("\nInvalid input received. Please use indicated options.\n");
				scan = new Scanner(System.in);
			} 
		}
		
		for(int i = 0; i < allGames.size(); i++) {
			allGames.get(i).removeAllPlayers();
			FileWriter fileOut = new FileWriter(new File(allGames.get(i).getVersion() + "_Game_Log.txt"));
			allGames.get(i).getReport(fileOut);
			fileOut.close();
			System.out.println();
		}
	}

}
