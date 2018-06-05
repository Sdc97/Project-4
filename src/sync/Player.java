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

class Player {
	protected int RELOAD_AMOUNT = 0;
	protected int total_reload = 0;
	protected int money;
	protected int bet;
	protected int betType;
	protected int number;
	private int winning;
	private int initialmoney;
	protected int choice;
	private int count = 0;
	private int playing = 0;
	private double betTotal = 0;
	private String name = "Player";
	public int housewinning;
	protected boolean playedRound;

	public Player(int initialMoney) {
		money = initialMoney;
		initialmoney = money;
	}

	public int getMoney() {
		return money;
	}

	public void makeBet(Scanner scan, int minBet, int maxBet) {
		playedRound = true;
		System.out.println("1.Enter bet");
		System.out.println("2.Reload");
		System.out.println("3.Skip this round");
		System.out.println("4.Exit Game");
		System.out.print("\nOption --> ");
		choice = scan.nextInt();
		if (choice >= 1 && choice <= 4) {
			switch (choice) {
			case 1:
				System.out.println("Balance: " + money + " chips");
				System.out.print("Amount of bet: ");
				bet = scan.nextInt();
				while (bet < minBet || bet > money || bet > maxBet) {
					System.out.println("Bet is invalid. Please enter a valid bet amount!");
					System.out.print("Amount of bet: ");
					bet = scan.nextInt();
				}
				money = money - bet;

				Wheel.betOptions();
				System.out.print("Option --> ");
				betType = scan.nextInt();
				while (betType < 1 || betType > 5) {
					System.out.print("Please enter a correct bet type: ");
					betType = scan.nextInt();
				}

				if (betType == 3) {
					System.out.print("Which number to bet on? Between " + Wheel.MIN_NUM + " and "
							+ Wheel.MAX_NUM + ": ");
					number = scan.nextInt();
					System.out.println(number);
					while (number < Wheel.MIN_NUM || number > Wheel.MAX_NUM) {
						System.out.print("That is not a valid number to bet on. \nRemember, the bet "
								+ "must be between " + Wheel.MIN_NUM + " and " + Wheel.MAX_NUM + ": ");
						number = scan.nextInt();
					}
				}
				if (betType == 4) {
					number = 37;
				}
				if(betType == 5) {
					number = 38;
				}
				break;
			case 2:
				System.out.println("Enter the amount you want to reload by: ");
				RELOAD_AMOUNT = scan.nextInt();
				money += RELOAD_AMOUNT;
				total_reload += RELOAD_AMOUNT;
				System.out.println("Money available: " + money);
				makeBet(scan,minBet,maxBet);
				break;
			case 3:
				playedRound = false;
				
				break;
			case 4:
				playing = 1;
				break;
			}
		} else {
			System.out.println("\nPlease enter a valid entry");
			makeBet(scan,minBet,maxBet);
		}
	}

	public void payment() {
		if (Wheel.payoff(bet, betType, number) > bet) {
			money = money + Wheel.payoff(bet, betType, number);
			System.out.println(name + " won!");
		} else {
			System.out.println(name + " lost...");
		}
	}
	
	public int wonThisRound() {
		return bet;
	}

	public double getbetTotal() {
		betTotal += bet;
		return betTotal;
	}

	public int getCount() {
		return count;
	}

	public int getWinning() {
		winning = money - (initialmoney + total_reload);
		return winning;
	}

	public int getHouseWinning() {
		housewinning = (initialmoney + total_reload) - money;
		return housewinning;
	}

	public String toString() {
		String result = "|| Initital game balance: " + initialmoney;
		result += "|| Ending game balance: " + money;
		result += "|| Winning/Losing amount: " + getWinning();// House winning/losing
		return result;
	}

	public boolean inThisRound() {
		return playedRound;
	}

	public boolean isPlaying() {
		if (playing == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
}