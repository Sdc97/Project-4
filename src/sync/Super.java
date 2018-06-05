package sync;

import java.util.Scanner;

public class Super extends VIP
{
	private String name,id;
	private double bonus;
	private int count = super.getCount();
	
	public Super(String playerName,String playerid, int initialMoney)
	{
		super(playerName, playerid, initialMoney);
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
	
	public String getName() {
		return name;
	}
}
