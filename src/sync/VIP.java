package sync;

import java.util.Scanner;

public class VIP extends Player
{
	private String name,id;
	private double bonus;
	public VIP(String playerName,String playerid, int initialMoney) 
	{
		super(initialMoney);
		name = playerName;
		id= playerid;
	}
	public double bonus()
	{
		bonus = super.getbetTotal()*0.5;
		return bonus;
	}
	public String toString()
	{
		String result = "The Vip:" + name +" || ID: "+ id;
		result += " Rewards: " + bonus;
		result += super.toString();
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
