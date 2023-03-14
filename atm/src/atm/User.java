package atm;

import java.util.ArrayList;

public class User {
	private ArrayList<Account> accs;
	private String id, password, name;
	
	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public ArrayList<Account> getAcc() {
		return this.accs;
	}

	public void setAcc(ArrayList<Account> accs) {
		this.accs = accs;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAccSize() {
		return this.accs.size();
	}

}
