package sync;

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
		String result = "The Vip:" + name +"|| ID: "+ id;
		result += "Rewards: " + bonus;
		return result;
	}
}
