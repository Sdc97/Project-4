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

class Player
{
	private int RELOAD_AMOUNT = 0;
	private int money,bet;
	private int betType;
	private int number;
	private int winning;
	private int initialmoney;
	private int choice;
	private int count = 0;
	private int playing = 0;
	private double betTotal = 0;
	public int housewinning;

	public Player (int initialMoney)
	{
		money = initialMoney;
		initialmoney = money;
	}

	public int getMoney()
	{
		return money;
	}  

	public void makeBet(Scanner scan)
	{
		playing = 0;
		
		while(playing != 1)//Menu
		{
			System.out.println("1.Enter bet");
			System.out.println("2.Reload");
			System.out.println("3.Skip this round");
			System.out.println("4.Exit Game");
			choice = scan.nextInt();
			if(choice >= 1 && choice <= 4)
			{
				switch(choice)
				{ 
				case 1:
					Wheel.betOptions();
					System.out.println("Enter your choice of bet: ");
					betType = scan.nextInt();
					switch(betType) {
					case 1:
						
					}
					if(betType <= 3 && betType >= 1)
					{
						playing =1;
						count++;
					}
					else
					{
						System.out.println("Please enter a valid options between 1-3");
					}
					if (betType == 3)//if bet option is 3
					{
						System.out.println("Enter the desired number between 1-36: ");
						number = scan.nextInt();
						if(number >= Wheel.MIN_NUM && number <= Wheel.MAX_NUM)
						{
							playing=1;
						}
						else
						{
							System.out.println("Please enter a valid number between 1-36.");
							playing =0;
						}
					}
					while(playing != 1)//Bet amount
					{
						System.out.print("How much to bet: ");
						bet = scan.nextInt();
						if(bet > 0)
						{
							money = money - bet;
							playing =1;
						}
						else
						{
							System.out.println("Please enter a valid entry");
							playing =0;
						}

					}
					break;
				case 2:
					System.out.println("Enter the amount you want to reload by: ");
					RELOAD_AMOUNT = scan.nextInt();
					money += RELOAD_AMOUNT;
					System.out.println("Money available: " + money);
					break;
				case 4:
					KeepPlaying(scan);
					break;
				}
			}
			else
			{
				System.out.println("Please enter a valid entry");
				playing = 0;
			}
		}
	} 
	public void payment()
	{
		if (Wheel.payoff(bet, betType, number) > 0)
		{
			money = money + Wheel.payoff(bet, betType, number);
		}
	}
	
	public double getbetTotal()
	{
		betTotal += bet;
		return betTotal;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public int getWinning()
	{
			winning = money - (initialmoney + RELOAD_AMOUNT);
			return winning;	
	}
	
	public int getHouseWinning()
	{
			housewinning = (initialmoney + RELOAD_AMOUNT) - money;
			return housewinning;	
	}
	
	public boolean KeepPlaying(Scanner scan)
	{
		String answer;
		System.out.print (" You sure you want to quit [y/n]? ");
		answer = scan.next();
		if (answer.equals("y") || answer.equals("Y")) {
			
		}
		return (answer.equals("y") || answer.equals("Y"));
	}  
	
	public String toString()
	{
		String result = "Initital game balance: " + initialmoney;
		result += "Ending game balance: " + money;
		result += "Winning/Losing amount: " + housewinning;//House winning/losing
		return result;
	}
	
	public boolean isPlaying() {
		if(playing == 0) {
			return true;
		} else {
			return false;
		}
	}
}