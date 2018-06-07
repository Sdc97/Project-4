/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: Super.java
	Date: 6/5/2018
	Description: Polymorphic relationship with player. 
	Obtain rewards for Super VIP. Also contain name and ID.

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;

import java.util.Scanner;

public class Super extends VIP
{
	private String name,id;
	private int superBonus;
	private int count = super.getCount();

	public Super(String playerName,String playerid, int initialMoney)
	{
		super(playerName, playerid, initialMoney);
		name = playerName;
		id= playerid;
	}
	public int superBonus()
	{
		if(count >= 5 && count <= 10)
		{
			superBonus = 10;
		}
		else if(count >= 11 && count <= 20)
		{
			superBonus = 25;
		}
		else if(count < 20)
		{
			superBonus = 50;
		}
		else
		{
			superBonus = 0;
		}
		return superBonus;
	}
	public String toString()
	{
		String result = " || The Vip:" + name +" || ID: "+ id;
		return result;
	}

	public void payment() {
		for (int i = 0; i < bets.size(); i++) {
			winningsThisRound.add(Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i)));
			if (Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i)) > bet) {
				money = money + Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i));
				System.out.println(name + " won bet number " + (i+1));
			} else {
				System.out.println(name + " lost bet number " + (i+1));
			}
		}
	}
	
	public boolean isSuper() {
		return true;
	}

	public String getName() {
		return name;
	}
}
