/*  Java Program: 
	Modified by:	Steven Calvert
	Class:	
	Date:	
	Description: 

	I certify that the code below is modified by me.

	Exception(s): N/A

*/
package sync;
import java.util.Scanner;

//************************************************************************
// Class Player represents one roulette player.
//************************************************************************
class Player
{
	private int RELOAD_AMOUNT;
	private int money,bet;
	private int betType;
	private int number;
	private int betCount = 0;
	private int winning;
	private int reloads = 0;
	private int initialmoney;
	private int choice;


	//=====================================================================
	//  The Player constructor sets up  name and initial available money.
	//=====================================================================
	public Player (int initialMoney)
	{
		money = initialMoney;
		initialmoney = money;
	} // constructor Player

	//=====================================================================
	//  Returns this player's current available money.
	//=====================================================================
	public int getMoney()
	{
		return money;
	}  // method getMoney

	//=====================================================================
	//  Prompts the user and reads betting information.
	//=====================================================================
	public void makeBet(Scanner scan)
	{
		int i = 0;
		int j = 0;
		/*if (money <= 0)
		{
			System.out.println("You have lost all of your money!");
			System.out.println("It's okay though because we have automatticaly refill the amount by $50 ");
			money = money + RELOAD_AMOUNT;
			System.out.println("Money available: " +money);
			System.out.println("");
			reloads++;
		}*/


		while(i != 1)
		{
			System.out.println("1.Enter bet");
			System.out.println("2.Reload");
			System.out.println("3.Exit Game");
			choice = scan.nextInt();
			if(choice >= 1 && choice <= 3)
			{
				switch(choice)
				{ 
				case 0:
					while(i != 1)
					{
						System.out.println("Enter your choice of bet: ");
						betType = scan.nextInt();
						if(betType <= 3 && betType >= 1)
						{
							i =1;
						}
						else
						{
							System.out.println("Please enter a valid options between 1-3"); 
							i=0;
						}
					}
					if (betType == 3)
					{
						while(i != 1) 
						{
							System.out.println("Enter the desire number between 1-36: ");
							number = scan.nextInt();
							if(number >= Wheel.MIN_NUM && number <= Wheel.MAX_NUM)
							{
								i=1;
							}
							else
							{
								System.out.println("Please enter a valid number between 1-36.");
								i =0;
							}
						}
					}
					while(i != 1)
					{
						System.out.print(" How much to bet: ");
						bet = scan.nextInt();
						if(bet >= Wheel.MIN_BET && bet <= Wheel.MAX_BET)
						{
							money = money - bet;
							i =1;
						}
						else
						{
							System.out.println("Please enter a valid entry");
							i =0;
						}

					}
					break;
				case 1:
					System.out.println("Enter the amount you want to reload by: ");
					RELOAD_AMOUNT = scan.nextInt();
					money += RELOAD_AMOUNT;
					System.out.println("Money available: " + money);
					break;
				case 2:
					
					break;
				}
			}
			else
			{
				System.out.println("Please enter a valid entry");
				i =0;
			}
		}
	} // method makeBet
	public void payment()
	{
		if (Wheel.payoff(bet, betType, number) > 0)
		{
			System.out.println(" won $" + Wheel.payoff(bet, betType, number));
			money = money + Wheel.payoff(bet, betType, number);
		}
		if (Wheel.payoff(bet, betType, number) < 0)
		{
			System.out.println(" lost $" + -Wheel.payoff(bet, betType, number));
		}
	}
	public int getWinning() // Keep updating self
	{
		if (reloads > 0) 
		{
			winning = money - (initialmoney + RELOAD_AMOUNT);
			return winning;
		}
		else
		{
			winning = money - initialmoney;	
			return winning;
		}
	}
		
	//=====================================================================
	//  Determines if the player wants to play again.
	//=====================================================================
	public boolean playAgain(Scanner scan)
	{
		String answer;
		System.out.print (" Keep playing [y/n]? ");
		answer = scan.next();
		return (answer.equals("y") || answer.equals("Y"));
	}  // method playAgain

	public int getCount() // get tries 
	{
		return betCount++;
	}

	//=====================================================================
	//  Return relevant information about the player.
	//=====================================================================
	public String toString()
	{
		if (winning >= 0)
		{
			String result = " After " + betCount +" bets, "+ " has won $" + winning;
			return result;
		}
		else
		{
			String result = " After " + betCount +" bets, "+" has lost $" + -winning;
			return result;
		}
	}  // method toString
}