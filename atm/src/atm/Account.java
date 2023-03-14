package atm;

public class Account {
	
	public static int LIMIT = 3;
	
	private int userCode;
	private String accountNumber;
	private int money;
	
	public Account(int userCode, String accountNumber, int money) {
		this.userCode = userCode;
		this.accountNumber = accountNumber;
		this.money = money;
	}
	
	public int getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
