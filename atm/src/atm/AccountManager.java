package atm;

import java.util.ArrayList;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<>();
	
	// Account 에 대한
	
	// C reate
	public void createAccount(String id, String accountNumber, int money) {
	    // 이미 존재하는 계좌 번호인 경우 생성 불가
	    for (Account account : list) {
	        if (account.getAccountNumber().equals(accountNumber)) {
	            System.out.println("[메세지] 이미 존재하는 계좌입니다.");
	            return;
	        }
	    }

	    // 계좌 생성 제한 검사
	    int count = 0;
	    for (Account account : list) {
	        if (account.getId().equals(id)) {
	            count++;
	            if (count >= Account.LIMIT) {
	                System.out.println("[메세지] 계좌 생성 제한을 초과하였습니다.");
	                return;
	            }
	        }
	    }

	    // 계좌 생성
	    Account account = new Account(id, accountNumber, money);
	    list.add(account);
	    System.out.println("[계좌생성] 계좌가 생성되었습니다.");
	}
	
	// R ead
	// U pdate
	// D elete
	

}
