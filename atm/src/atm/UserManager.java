package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<>();

	// User 에 대한

	// C reate
	public void addUser(User user) {
		list.add(user);
	}

	// R ead
	public User getUser(int index) {
		User user = list.get(index);

		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();

		// 사본제공
		User reqObj = new User(id, password, name);
		reqObj.setAcc(user.getAcc());
		return reqObj;
	}

	public User getUser(String id) {
		int index = -1; 
		
		for (int i = 0; i < getList().size(); i++)
			if (getList().get(i).getId().equals(id))
				index = i;
		
		return getUser(index);
	}

	// U pdate
	public void setUser(int index, User user) {
		list.set(index, user);
	}

	// D elete
	public void deleteUser(int index) {
		list.remove(index);
	}

	public void deleteUserById(String id) {
		int index = -1;
		for (int i = 0; i < getList().size(); i++)
			if (getList().get(i).getId().equals(id))
				index = i;

		deleteUser(index);
	}

	public ArrayList<User> getList() {
		return list;
	}

}
