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

		// 사본제공
		User reqObj = new User();
		return reqObj;
	}

	public User getUser(String id) {
		int index = -1; // 호출되는 메서드 존재 추가해야됨
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
		for (int i = 0; i < list.size(); i++) {

		}
	}

	public ArrayList<User> getList() {
		return list;
	}
}
