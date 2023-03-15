package atm;

import java.util.ArrayList;

public class User {
	private String id, password, name;

	// new 객체가 아님 -> AccountManager.list 안에 있는 인스턴스
	private ArrayList<Account> accs;

	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = new ArrayList<Account>();
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public int getAccountSize() {
		return this.accs.size();
	}

	public void addAccount(Account account) {
		this.accs.add(account);
	}

	public Account getAccount(int index) {
		return this.accs.get(index);
	}

	@Override
	public String toString() {
		return String.format("%s / %s [%s]", this.id, this.password, this.name);
	}

	public ArrayList<Account> getAccountList() {
		return (ArrayList<Account>) this.accs.clone();
	}

	public void printAcc() {
		for (int i = 0; i < this.accs.size(); i++) {
			System.out.printf("[%d] %s\n", i + 1, this.accs.get(i).getAccNum());
		}
	}
	
	public int getSize() {
		return this.accs.size();
	}

}
