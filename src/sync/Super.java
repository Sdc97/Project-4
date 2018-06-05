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
		bonus += Math.ceil(super.getbetTotal()*0.5);
		return bonus;
	}
	public String toString()
	{
		String result = "The Vip:" + name +"|| ID: "+ id;
		result += "Rewards: $" + bonus;
		return result;
	}
	
	public void payment() {
		for (int i = 0; i < bets.size(); i++) {
			if (Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i)) > bet) {
				money = money + Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i));
				System.out.println(name + " won bet number " + (i+1));
			} else {
				System.out.println(name + " lost bet number " + (i+1));
			}
		}
	}
	
	public String getName() {
		return name;
	}
}
