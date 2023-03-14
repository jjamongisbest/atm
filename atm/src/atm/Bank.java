package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {

	private UserManager um;
	private AccountManager am;
	private Scanner sc;
	private int log;

	private String name;

	// Banking 관련 메소드

	public Bank(String name) {
		this.name = name;
		this.um = new UserManager();
		this.am = new AccountManager();
		this.sc = new Scanner(System.in);
		this.log = -1;
	}

	public void run() {
		mainMenu();
	}

	private void printMenu() {
		System.out.println("[" + this.name + " ATM]");
		if (isLoggedIn()) {
			System.out.printf("[환영합니다 %s님!]\n", um.getList().get(this.log).getName());
		}
		System.out.println("[1] 로그인");
		System.out.println("[2] 회원가입");
		System.out.println("[3] 회원탈퇴");
		System.out.println("[4] 계좌신청");
		System.out.println("[5] 계좌철회");
		System.out.println("[0] 로그아웃");
		System.out.print("[메뉴선택] : ");

	}

	private boolean checkDuplAccount(String accountNumber) {
		for (Account account : am.getList()) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkAccountCount(String id) {
		// 계좌 생성 제한 검사
		int count = 0;
		for (Account account : am.getList()) {
			if (account.getId().equals(id)) {
				count++;
				if (count >= Account.LIMIT) {
					return false;
				}
			}
		}
		return true;
	}

	private String setAccount() {
		Random ran = new Random();

		int r = ran.nextInt(8999) + 1000;
		int n = ran.nextInt(8999) + 1000;

		String data = r + "-" + n;
		
		return data;
	}

	private void createAccount() {
		if (isLoggedIn()) {
			String accountNumber =  setAccount();

			if (checkDuplAccount(accountNumber)) {
				ArrayList<User> userList = um.getList();
				String id = userList.get(this.log).getId();
				if (checkAccountCount(id)) {
					int money = 0;
					Account acc = new Account(id, accountNumber, money);
					am.createAccount(acc);
					acc.setAccountSize(acc.getAccountSize() + 1);
					um.getList().get(this.log).setAcc(am.getList());;
					System.out.println("[계좌생성] 계좌생성을 완료하였습니다.");
				} else
					System.out.println("[메세지] 계좌 생성 제한을 초과하였습니다.");
			} else
				System.out.println("[메세지] 이미 존재하는 계좌입니다.");

		} else
			System.out.println("[메세지] 로그인 후 이용가능한 서비스 입니다.");
	}

	private void printMyAccount(String id) {
		int index = 0;
		ArrayList<Account> list = am.getList();
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				System.out.printf("[%d] %s\n", ++index, list.get(i).getAccountNumber());
	}

	private void deleteAccount() {
		if (isLoggedIn()) {
			String id = um.getList().get(this.log).getId();
			printMyAccount(id);

			System.out.print("[계좌삭제] 삭제할 넘버 : ");
			int sel = sc.nextInt() - 1;

			int accSize = um.getList().get(this.log).getAccSize();
			if (sel >= 0 && sel < accSize) {
				am.deleteAccount(sel);
				System.out.println("[메세지] 계좌 삭제가 완료되었습니다.");
			}

		} else
			System.out.println("[메세지] 로그인 후 이용가능한 서비스 입니다.");
	}

	private boolean isLoggedIn() { // 로그인 되어있으면 true
		return this.log != -1;
	}

	private boolean checkDuplId(String id) { // 중복 시 true 반환
		ArrayList<User> userList = um.getList();
		for (User user : userList)
			if (user.getId().equals(id))
				return true;

		return false;
	}

	private boolean checkDuplPassword(String password) { // 중복 시 true 반환
		ArrayList<User> userList = um.getList();
		for (User user : userList)
			if (user.getPassword().equals(password))
				return true;

		return false;
	}

	private void signUp() {
		if (!isLoggedIn()) {
			System.out.print("[회원가입] ID : ");
			String id = sc.next();
			System.out.print("[회원가입] PW : ");
			String password = sc.next();
			System.out.print("[회원가입] NAME : ");
			String name = sc.next();

			if (!checkDuplId(id)) {
				User user = new User(id, password, name);
				um.addUser(user);
				System.out.println("[메세지] 회원가입을 축하합니다!");
			} else
				System.out.println("[메세지] 유효하지 않거나 이미 가입된 아이디 입니다.");
		} else
			System.out.println("[메세지] 로그아웃 후 이용해주세요.");
	}

	private void leave() {
		System.out.print("[회원탈퇴] ID : ");
		String id = sc.next();
		System.out.print("[회원탈퇴] PW : ");
		String password = sc.next();

		if (checkDuplId(id) && checkDuplPassword(password)) {
			um.deleteUserById(id);
		} else
			System.out.println("[메세지] 유호하지 않은 회원정보입니다..");
	}

	private void login() {
		System.out.print("[로그인] ID : ");
		String id = sc.next();
		System.out.print("[로그인] PW : ");
		String password = sc.next();

		int index = -1;

		ArrayList<User> userList = um.getList();

		for (int i = 0; i < userList.size(); i++)
			if (userList.get(i).getId().equals(id))
				index = i;

		if (index != -1) {

			if (userList.get(index).getPassword().equals(password))
				this.log = index;

		} else
			System.out.println("[메세지] 존재하지 않는 아이디 입니다.");
	}

	private void logOut() {
		this.log = -1;
		System.out.println("[메세지] 로그아웃이 완료되었습니다.BYE.");
	}

	private void mainMenu() {
		printMenu();

		int sel = sc.nextInt();

		if (sel == 1)
			login();
		else if (sel == 2)
			signUp();
		else if (sel == 3)
			leave();
		else if (sel == 4)
			createAccount();
		else if (sel == 5)
			deleteAccount();
		else if (sel == 0)
			logOut();
	}

}
