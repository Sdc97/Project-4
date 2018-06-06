/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: Transactions.java
	Date: 6/5/2018
	Description: Keep tracks of all values for the end of game report

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;


public class Transactions 
{	
	private int trans, betAmount, pay,name;
	private String betType;

	public Transactions(int trans, int name, int betAmount, String betType, int pay) 
	{
		this.trans = trans;
		this.name = name;
		this.betAmount = betAmount;
		this.betType = betType;
		this.pay = pay;
	}

	public int getTrans()
	{
		return trans;
	}

	public int getName()
	{
		return name;
	}

	public int getBetAmount()
	{
		return betAmount;
	}

	public String getBetType()
	{
		return betType;
	}

	public int getPay()
	{
		return pay;
	}
}
