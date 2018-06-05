package sync;

import java.util.Scanner;

public class Super extends Player
{
	private String name,id;
	private double bonus;
	private int count = super.getCount();
	
	public Super(String playerName,String playerid, int initialMoney)
	{
		super(initialMoney);
		name = playerName;
		id= playerid;
	}
	public double bonus()
	{
		if(count >= 5 && count <= 10)
		{
			bonus = 10;
		}
		else if(count >= 11 && count <= 20)
		{
			bonus = 25;
		}
		else if(count < 20)
		{
			bonus = 50;
		}
		else
		{
			bonus = 0;
		}
		bonus += super.getbetTotal()*0.5;
		return bonus;
	}
	public String toString()
	{
		String result = "The Vip:" + name +"|| ID: "+ id;
		result += "Rewards: $" + bonus;
		return result;
	}
	
	public void payment()
	{
		if (Wheel.payoff(bet, betType, number) > bet)
		{
			money = money + Wheel.payoff(bet, betType, number);
			System.out.println(name + " won!");
		} else {
			System.out.println(name + " lost...");
		}
	}
	
	public void makeBet(Scanner scan)
	{
		playedRound = true;
		System.out.println(name + " has " + money + " chips");
			System.out.println("1.Enter bet");
			System.out.println("2.Reload");
			System.out.println("3.Skip this round");
			System.out.println("4.Exit Game");
			System.out.print("\nOption --> ");
			choice = scan.nextInt();
			if(choice >= 1 && choice <= 4)
			{
				switch(choice)
				{ 
				case 1:
					System.out.println(name + " has " + money + " chips");
			      	System.out.print("How much will " + name + " bet: ");
			      	bet = scan.nextInt();
			      	System.out.println(bet); // Only for use with text file input 
			      	while (bet < Wheel.MIN_BET || bet > money) {
			      		System.out.println("Bet is invalid. Please enter a valid bet amount!");
			      		System.out.print("How much will " + name + " bet: ");
			          	bet = scan.nextInt();
			      	}
			      	money = money - bet;
			      	
			      	Wheel.betOptions();
			      	System.out.print("What is " + name + "s choice?: ");
			      	betType = scan.nextInt();
			      	System.out.println(betType); 
			      	while (betType < 1 || betType > 3) {
			      		System.out.println("Please enter a correct bet type.");
			      		betType = scan.nextInt();
			      	}
			      	
			      	if (betType == 3) {
			      		System.out.print("What number would " + name + " like to bet on? Between " + Wheel.MIN_NUM + " and " + Wheel.MAX_NUM + ": ");
			      		number = scan.nextInt();
			      		System.out.println(number);
			      		while (number < Wheel.MIN_NUM || number > Wheel.MAX_NUM) {
			      			System.out.print("That is not a valid number to bet on. \nRemember, the bet "
			      					+ "must be between " + Wheel.MIN_NUM + " and " + Wheel.MAX_NUM + ": ");
			      			number = scan.nextInt();
			      		}
			      	}
					break;
				case 2:
					System.out.println("Enter the amount you want to reload by: ");
					RELOAD_AMOUNT = scan.nextInt();
					money += RELOAD_AMOUNT;
					System.out.println("Money available: " + money);
					break;
				case 3:
					playedRound = false;
					break;
				case 4:
					KeepPlaying(scan);
					break;
				}
			}
			else
			{
				System.out.println("Please enter a valid entry");
			}
	}
}
