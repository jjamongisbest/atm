package atm;

public class Account {
	
	public static int LIMIT = 3;
	
	private String userId , accountNumber;
	private int money;
	private int accountCount;
	
	public Account(String id, String accountNumber, int money) {
		this.userId = id;
		this.accountNumber = accountNumber;
		this.money = money;
		this.accountCount = 1;
	}
	
	public String getId() {
		return this.userId;
	}
	
	public void setId(String id) {
		this.userId = id;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public int getAccountCount() {
		return this.accountCount;
	}
	
	public void setAccountCount(int accountCount) {
		this.accountCount = accountCount;
	}
	
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
