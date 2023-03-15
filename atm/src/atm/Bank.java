package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import atm2.Account;
import atm2.User;

public class Bank {

	private UserManager um;
	private AccountManager am;
	private Scanner sc;
	private int log;

	private String brandName;

	// Banking 관련 메소드

	public Bank(String brandName) {
		this.brandName = brandName;
		this.um = new UserManager();
		this.am = new AccountManager();
		this.sc = new Scanner(System.in);
		this.log = -1;
	}

	private int inputNumber() {
		int number = -1;

		String input = this.sc.next();

		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return number;
	}

	public void run() {

		while (true) {
			printMainMenu();

			int sel = inputNumber();

			if (sel == 1)
				login();
			else if (sel == 2)
				signUp();
			else if (sel == 3)
				fileSystem();
			else if (sel == 0)
				break;
		}

	}

	private void printLogInMenu() {
		System.out.println("[" + this.brandName + " ATM]");

		System.out.println("[1] 회원탈퇴");
		System.out.println("[2] 뱅킹시스템");
		System.out.println("[0] 로그아웃");
		System.out.print("[메뉴선택] : ");
	}

	private void printMainMenu() {
		System.out.println("[" + this.brandName + " ATM]");

		System.out.println("[1] 로그인"); // printLogInMenu()
		System.out.println("[2] 회원가입");
		System.out.println("[3] 파일기능"); // save, load
		System.out.println("[0] 종료");
		System.out.print("[메뉴선택] : ");
	}

	private void printFileMenu() {
		System.out.println("[파일기능]");
		System.out.println("[1] 파일저장");
		System.out.println("[2] 파일로드");
		System.out.println("[0] 뒤로가기");
		System.out.print("[메뉴선택] : ");
	}

	private void printBankingMenu() {
		System.out.println("[뱅킹기능]");
		System.out.println("[1] 입금");
		System.out.println("[2]	출금");
		System.out.println("[3] 이체");
		System.out.println("[4] 조회");
		System.out.println("[5]	계좌신청");
		System.out.println("[6]	계좌철회");
		System.out.println("[0] 뒤로가기");
		System.out.print("[메뉴선택] : ");
	}

	private void createAccount() {
		System.out.print("id : ");
		String id = this.sc.next();
		System.out.print("password : ");
		String password = this.sc.next();

		// 복제본 반환 받음
		User user = this.um.getUserById(id);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				if (user.getAccountSize() < Account.LIMIT) {
					Account account = this.am.createAccount(new Account(id));
					this.um.setUser(user, account);
				}
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("회원정보를 확인하세요.");
		}
	}

	private void deleteAccount() {
		User target = this.um.getUser(this.log);
		target.printAcc();
		System.out.print("[계좌삭제] 삭제할 계좌 : ");
		int sel = inputNumber() - 1;
		
		if(sel >=0 && sel < target.getSize()) {
			this.um.deleteUser(sel);
		} else
			System.err.println("[메세지] 계좌 삭제 실패");
	}

	private boolean isLoggedIn() { // 로그인 되어있으면 true
		return this.log != -1;
	}

	private void signUp() {
		if (!isLoggedIn()) {
			System.out.print("[회원가입] ID : ");
			String id = sc.next();
			System.out.print("[회원가입] PW : ");
			String password = sc.next();
			System.out.print("[회원가입] NAME : ");
			String name = sc.next();

			User user = new User(id, password, name);

			if (this.um.addUser(user) != null)
				System.out.println("[메세지] 회원가입을 축하합니다!");
			else
				System.err.println("[메세지] 유효하지 않거나 이미 가입된 아이디 입니다.");

		} else
			System.err.println("[메세지] 로그아웃 후 이용해주세요.");
	}

	private void leave() {
		System.out.print("[회원탈퇴] ID : ");
		String id = sc.next();
		System.out.print("[회원탈퇴] PW : ");
		String password = sc.next();

	}

	private void login() {
		System.out.print("[로그인] ID : ");
		String id = sc.next();
		System.out.print("[로그인] PW : ");
		String password = sc.next();

		int index = -1;

		User user = this.um.getUserById(id);

		if (user != null && user.getPassword().equals(password)) {
			this.log = um.indexOfById(id);
			System.out.println("[메세지]" + user.getName() + " 님 환영합니다. HI!");
			while (true) {
				printLogInMenu();

				int sel = inputNumber();

				if (sel == 1)
					leave();
				else if (sel == 2)
					banking();
				else if (sel == 0) {
					logOut();
					break;
				}
			}
		} else {
			System.err.println("[메세지] 로그인 실패! 회원정보를 확인하세요");
		}
	}

	private void banking() {
		while (true) {
			printBankingMenu();

			int sel = inputNumber();

			if (sel == 1)
				deposit();
			else if (sel == 2)
				withdraw();
			else if (sel == 3)
				transfer();
			else if (sel == 4)
				checkAccount();
			else if (sel == 5)
				createAccount();
			else if (sel == 6)
				deleteAccount();
			else if (sel == 0)
				break;
		}
	}

	private void fileSystem() {
		while (true) {
			printFileMenu();

			int sel = inputNumber();

			if (sel == 1)
				saveFile();
			else if (sel == 2)
				loadFile();
			else if (sel == 0)
				break;
		}
	}

	private void logOut() {
		this.log = -1;
		System.out.println("[메세지] 로그아웃이 완료되었습니다. BYE.");
	}

}
