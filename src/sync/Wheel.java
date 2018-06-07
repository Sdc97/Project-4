
// Class Wheel for CSCI 145 Project 2 Spring 18

// Modified by: Steven Calvert, Boon C, Alex Neoh 

//************************************************************************
//   Class Wheel represents a roulette wheel and its operations.  Its
//   data and methods are static because there is only one wheel.
//************************************************************************
package sync;
import java.util.Random;

class Wheel {
	// public name constants -- accessible to others
	public final static int BLACK = 0; // even numbers
	public final static int RED = 1; // odd numbers
	public final static int GREEN = 2; // 00 OR 0
	public final static int NUMBER = 3; // number bet
	public final static int MIN_NUM = 1; // smallest number to bet
	public final static int MAX_NUM = 36; // largest number to bet
	public final static int MAX_BET = 10; // largest amount to bet
	public final static int MIN_BET = 1; // smallest number to bet
	public final static int RELOAD_VALUE = 50;

	// private name constants -- internal use only
	private final static int MAX_POSITIONS = 38; // number of positions on wheel
	private final static int NUMBER_PAYOFF = 35; // payoff for number bet
	private final static int COLOR_PAYOFF = 2; // payoff for color bet

	// private variables -- internal use only
	private static int ballPosition;
	private static int color; // GREEN, RED, OR BLACK

	// =====================================================================
	// Presents welcome message
	// =====================================================================
	public static void welcomeMessage() {
		System.out.println("Welcome to a advance version of roulette game.");
		System.out.println("You can place a bet on black, red, or a number.");
		System.out.println("A color bet is paid " + COLOR_PAYOFF + " times the bet amount.");
		System.out.println("A number bet is paid " + NUMBER_PAYOFF + " times the bet amount.");
		System.out.println("You can bet on a number from " + MIN_NUM + " to " + MAX_NUM + ".");
		System.out.println("Gamble responsibly.  Have fun and good luck!\n");
	}

	// =====================================================================
	// Presents betting options
	// =====================================================================
	public static void betOptions() {
		System.out.println("Betting Options:");
		System.out.println("    1. Bet on black (even numbers)");
		System.out.println("    2. Bet on red (odd numbers)");
		System.out.println("    3. Bet on a number between " + MIN_NUM + " and " + MAX_NUM);
		System.out.println();
	}

	public static void spin() {
		System.out.println();
		Random rng = new Random();
		ballPosition = rng.nextInt(MAX_POSITIONS) + 1;
		if (ballPosition > 36) {
			color = 2;
			if (ballPosition == 37) {
				System.out.println("Color is green, number is 0");
			} else {
				System.out.println("Color is green, number is 00");
			}
		} else if (ballPosition % 2 == 0) {
			color = 0;
			System.out.println("Color is black, number is " + ballPosition);
		} else {
			color = 1;
			System.out.println("Color is red, number is " + ballPosition);
		}
	}

	public static String getResult() {
		if (ballPosition > 36) {
			if (ballPosition == 37) {
				return "Green 0";
			} else {
				return "Green 00";
			}
		} else if (ballPosition % 2 == 0) {
			return "Black " + ballPosition;
		} else {
			return "Red " + ballPosition;
		}
	}
	
	public static int payoff(int betAmount, int betType, int numberBet) {
		int payout = 0;
		switch (betType) {
		case 1:
			if (color == 0) {
				payout = betAmount * COLOR_PAYOFF;
			} else {
				payout = 0;
			}
			break;
		case 2:
			if (color == 1) {
				payout = betAmount * COLOR_PAYOFF;
			} else {
				payout = 0;
			}
			break;
		case 3:
			if (numberBet == ballPosition) {
				payout = betAmount * 13;
			} else {
				payout = 0;
			}
			break;
		default:
			System.out.println("404: Balls not found");
			break;
		}
		return payout;
	}
}
