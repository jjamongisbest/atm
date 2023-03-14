package atm;

import java.util.ArrayList;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<>();

	// C reate
	public void createAccount(Account account) {
		list.add(account);
	}

	// R ead

	public Account getAccount(int index) {
		Account account = list.get(index);

		String id = account.getId();
		String accountNumber = account.getAccountNumber();
		int money = account.getMoney();

		Account reqObj = new Account(id, accountNumber, money);
		return reqObj;
	}

	// U pdate
	public void setAccount(int index, Account account) {
		list.set(index, account);
	}

	// D elete
	public void deleteAccount(int index) {
		list.remove(index);
	}

	public ArrayList<Account> getList() {
		return list;
	}

}
