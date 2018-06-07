/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: VIP.java
	Date: 6/5/2018
	Description: Polymorphic relationship with player. 
	Obtain rewards for regular VIP. Also contain name and ID.

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;

import java.util.Scanner;

public class VIP extends Player
{
	private String name,id;
	protected int bonus;
	public VIP(String playerName,String playerid, int initialMoney) 
	{
		super(initialMoney);
		name = playerName;
		id= playerid;
	}
	public int bonus()
	{
		bonus = (int)Math.ceil(super.getbetTotal()*0.05);
		return bonus;
	}
	public String toString()
	{
		String result = " || The Vip:" + name +" || ID: "+ id;
		result += super.toString();
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

	public String getName() {
		return name;
	}
}
