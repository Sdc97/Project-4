package sync;

import java.util.*;
public class MainMenu 
{

	public static void main(String[] args) 
	{
		String choice,id,name1,name2;
		int playerType,chips; 
		Scanner scan = new Scanner(System.in);
		Scanner inFile = new Scanner("players.txt");
		System.out.println("Initialize Games. Please wait...");
		System.out.println("All games are ready.");
		//System.out.println("Available games: ");

		System.out.println("Main Menu\n1. Select a game\n"
				+ "2. Add a new player to the list\n3. Quit");
		choice = scan.next();
		while(choice != "3")
		{
			if(choice=="1")
			{
				//stephens class
			}
			else if(choice =="2")
			{
				while(inFile.hasNextLine())
				{
					playerType = Integer.parseInt(inFile.next());
					chips = Integer.parseInt(inFile.next());
					id = inFile.next();
					name1 = inFile.next();
					name1+=inFile.next();
					
					if(playerType==0)
					{
						Player p = new Player(chips);
						Game.addToQueue(p);
					}
					else if(playerType == 1)
					{
						Player p = new VIP(name1,id,chips);
						Game.addToQueue(p);
					}
					else if(playerType == 2)
					{
						Player p = new Super(name1,id,chips);
						Game.addToQueue(p);
					}
				}
			}
			else
				System.out.println("Please enter 1, 2, or 3.");
			
			System.out.println("Main Menu\n1. Select a game\n"
					+ "2. Add a new player to the list\n3. Quit");
			choice = scan.next();
			
		}
	}

}
