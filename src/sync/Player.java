/*  Java Program: Advance Roulette
	Modified by: Steven Calvert, Boon C., Alex Neoh 
	Class: Player.java
	Date: 6/5/2018
	Description: Allow player to make choices and calculate 
	payment and the result of each round

	I certify that the code below is modified by me.
	Exception(s): N/A
 */
package sync;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
	protected int RELOAD_AMOUNT = 0;
	protected int total_reload = 0;
	protected int money;
	protected int bet;
	protected ArrayList<Integer> bets = new ArrayList<Integer>();
	protected int betsThisRound;
	final protected int maxBetsPerRound = 3;
	protected int betType;
	protected ArrayList<Integer> betTypesArr = new ArrayList<Integer>();
	protected int number;
	protected ArrayList<Integer> numberBetsArr = new ArrayList<Integer>(); 
	protected ArrayList<Integer> winningsThisRound = new ArrayList<Integer>();
	private int winning;
	private int initialmoney;
	protected int choice;
	protected String userInput;
	private int count = 0;
	private int playing = 0;
	private double betTotal = 0;
	private int skips = 0;
	private String name = "Player";
	public int housewinning;
	protected boolean playedRound;
	private int playerNumber;

	public Player(int initialMoney) {
		money = initialMoney;
		initialmoney = money;
	}

	public int getMoney() {
		return money;
	}

	public void makeBet(Scanner scan, int minBet, int maxBet) {
		scan = new Scanner(System.in);
		playedRound = true;
		boolean finished = false;
		while (!finished) {
			System.out.println("1.Enter bet");
			System.out.println("2.Reload");
			System.out.println("3.Skip this round");
			System.out.println("4.Exit Game");
			System.out.print("\nOption --> ");
			try {
				choice = scan.nextInt();
				if (choice >= 1 && choice <= 4) {
					switch (choice) {
					case 1:
						skips = 0;
						betsThisRound = 0;
						bets.clear();
						betTypesArr.clear();
						numberBetsArr.clear();
						winningsThisRound.clear();
						do {
							number = 0;
							count++;
							System.out.println("Balance: " + money + " chips");
							System.out.print("Amount of bet: ");
							bet = scan.nextInt();
							while (bet < minBet || bet > money || bet > maxBet) {
								System.out.println("Bet is invalid. Please ensure the bet is in the betting range.");
								System.out.print("Amount of bet: ");
								bet = scan.nextInt();
							}
							bets.add(bet);
							money = money - bet;
							betTotal += bet;

							Wheel.betOptions();
							System.out.print("Option --> ");
							betType = scan.nextInt();
							while (betType < 1 || betType > 3) {
								System.out.print("Please enter a correct bet type: ");
								betType = scan.nextInt();
							}
							betTypesArr.add(betType);

							if (betType == 3) {
								System.out.print("Which number to bet on? Between " + Wheel.MIN_NUM + " and "
										+ Wheel.MAX_NUM + ": ");
								number = scan.nextInt();
								while (number < Wheel.MIN_NUM || number > Wheel.MAX_NUM) {
									System.out.print("That is not a valid number to bet on. \nRemember, the bet "
											+ "must be between " + Wheel.MIN_NUM + " and " + Wheel.MAX_NUM + ": ");
									number = scan.nextInt();
								}
							}
							numberBetsArr.add(number);
							System.out.print("Make another bet?(y/n) --> ");
							userInput = scan.next();
							if ((userInput.equals("y") || userInput.equals("Y")) && betsThisRound < maxBetsPerRound) {
								betsThisRound++;
							}
							if ((userInput.equals("y") || userInput.equals("Y")) && betsThisRound >= maxBetsPerRound) {
								System.out.println("The maximum bets per round is " + maxBetsPerRound);
							}
						} while ((userInput.equals("y") || userInput.equals("Y")) && betsThisRound < maxBetsPerRound);
						finished = true;
						break;
					case 2:
						System.out.println("Enter the amount you want to reload by: ");
						RELOAD_AMOUNT = scan.nextInt();
						money += RELOAD_AMOUNT;
						total_reload += RELOAD_AMOUNT;
						System.out.println("Money available: " + money);
						makeBet(scan, minBet, maxBet);
						break;
					case 3:
						skips++;
						playedRound = false;
						if (skips > 2) {
							System.out.println("Skipped too many rounds... Removing player from game.");
							playing = 1;
						}
						finished = true;
						break;
					case 4:
						playing = 1;
						finished = true;
						break;
					}
				} else {
					System.out.println("\nPlease enter a valid entry");
					makeBet(scan, minBet, maxBet);
				}
			} catch (InputMismatchException exception) {
				System.out.println("\nInvalid input received.\n");
				scan = new Scanner(System.in);
			}
		}
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

	public int betTotalThisRound() {
		int betTotals = 0;
		for(int i = 0; i < bets.size(); i++) {
			betTotals += bets.get(i);
		}
		return betTotals;
	}

	public int wonThisRound() {
		int total = 0;
		for(int i = 0; i < bets.size(); i++) { // TODO fix house winning calculations
			total += Wheel.payoff(bets.get(i), betTypesArr.get(i), numberBetsArr.get(i));
		}
		return total;
	}

	public ArrayList<Integer> getBets() {
		return bets;
	}
	
	public ArrayList<String> getBetTypes() {
		ArrayList<String> strBetTypes = new ArrayList<String>();
		for(int i = 0; i < betTypesArr.size(); i++) {
			if(betTypesArr.get(i) == 1) {
				strBetTypes.add("B");
			} else if (betTypesArr.get(i) == 2) {
				strBetTypes.add("R");
			} else {
				strBetTypes.add(numberBetsArr.get(i).toString());
			}
		}
		return strBetTypes;
	}
	
	public void setPlayerNumber(int num) {
		playerNumber = num;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public ArrayList<Integer> getRoundWinnings() {
		return winningsThisRound;
	}
	
	public int getNumOfBets() {
		return betsThisRound;
	}

	public double getbetTotal() {
		return betTotal;
	}
	
	public void addMoney(int m) {
		money += m;
	}
	
	public int bonus() {
		return 0;
	}
	public int superBonus() {
		return 0;
	}
	
	public boolean isSuper() {
		return false;
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
		String result = " || Initital game balance: " + initialmoney;
		result += " || Ending game balance: " + money;
		result += " || Winning/Losing amount: " + getWinning();
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