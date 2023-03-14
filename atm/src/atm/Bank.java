package atm;

import java.util.ArrayList;
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

	// ATM 프로젝트
	//
	// - 회원가입 / 탈퇴
	// - 계좌신청 / 철회 (1인 3계좌 까지)
	// - 로그인

	private void printMenu() {
		System.out.println("[" + this.name + " ATM]");
		System.out.println("[1] 로그인");
		System.out.println("[2] 회원가입");
		System.out.println("[3] 회원탈퇴");
		System.out.println("[4] 계좌신청");
		System.out.println("[5] 계좌철회");
		System.out.println("[0] 로그아웃");

	}

	private void createAccount() {
	}

	private void deleteAccount() {
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
		
		if(checkDuplId(id)&&checkDuplPassword(password)) {
			
		}

	}

	private void mainMenu() {
		printMenu();

		int sel = sc.nextInt();
//
//		if (sel == 1)
//			login();
		else if (sel == 2)
			signUp();
		else if(sel == 3)
			leave();
//		else if(sel == 4)
//			createAccount();
//		else if(sel == 5)
//			deleteAccount();
//		else if(sel == 0)
//			logOut();
	}

}
