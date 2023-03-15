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
				leave();
			else if (sel == 4)
				createAccount();
			else if (sel == 5)
				deleteAccount();
			else if (sel == 0)
				//logOut();
				break;
		}

	}
	

	private void printMainMenu() {
		System.out.println("[" + this.brandName + " ATM]");

		System.out.println("[1] 로그인");
		System.out.println("[2] 회원가입");
		System.out.println("[3] 회원탈퇴");
		System.out.println("[4] 계좌신청");
		System.out.println("[5] 계좌철회");
		System.out.println("[0] 종료");
		System.out.print("[메뉴선택] : ");

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
		System.out.print("id : ");
		String id = this.sc.next();
		System.out.print("password : ");
		String password = this.sc.next();
		
		// 복제본 반환 받음  
		User user = this.um.getUserById(id);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				if(user.getAccountSize() < Account.LIMIT) {
					Account account = this.am.createAccount(new Account(id));
					this.um.setUser(user, account);
				}
			}
			else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}
		else {
			System.out.println("회원정보를 확인하세요.");
		}
	}

	private void deleteAccount() {
		if(!isLoggedIn()) {
			System.out.println("로그인 후 이용 가능합니다.");
			return;
		}
		
		System.out.print("삭제할 계좌의 ID를 입력하세요: ");
		String id = this.sc.next();
		
		Account account = this.am.getAccountById(id);
		if(account != null) {
			User user = this.um.getUserByAccount(account);
			if(user != null) {
				user.removeAccount(account);
				this.am.deleteAccount(account);
				System.out.println("계좌가 삭제되었습니다.");
			}
		} else {
			System.out.println("일치하는 계좌가 없습니다.");
		}
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

		if (checkDuplId(id) && checkDuplPassword(password)) {
			um.deleteUserById(id);
		} else
			System.err.println("[메세지] 유호하지 않은 회원정보입니다..");
	}

	private void login() {
		System.out.print("[로그인] ID : ");
		String id = sc.next();
		System.out.print("[로그인] PW : ");
		String password = sc.next();

		int index = -1;

		User user = this.um.getUserById(id);

		if(user != null && user.getPassword().equals(password)) {
			this.log = um.indexOfById(id);
			System.out.println("[메세지]"+ user.getName() + " 님 환영합니다. HI!");
		} else {
			System.err.println("[메세지] 로그인 실패! 회원정보를 확인하세요");
		}
	}

	private void logOut() {
		this.log = -1;
		System.out.println("[메세지] 로그아웃이 완료되었습니다. BYE.");
	}

}
