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
		bonus = Math.ceil(super.getbetTotal()*0.5);
		return bonus;
	}
	public String toString()
	{
		String result = "The Vip:" + name +" || ID: "+ id;
		result += " Rewards: " + bonus;
		result += super.toString();
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
