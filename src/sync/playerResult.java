package sync;

public class playerResult {
	int trans, betAmount, pay;
	String name, betType;

	public playerResult(int trans, String name, int betAmount, String betType, int pay) {
		this.trans = trans;
		this.name = name;
		this.betAmount = betAmount;
		this.betType = betType;
		this.pay = pay;
	}
}
