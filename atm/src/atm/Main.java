package atm;

public class Main {
	public static void main(String[] args) {

		Bank bank = new Bank("COCOA BANK");
		while (true) {
			bank.run();
		}
	}
}
