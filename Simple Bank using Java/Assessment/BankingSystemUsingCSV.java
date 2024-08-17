//RojinSinghMahat(2417733)
package Assessment;

import java.util.*;
import java.io.*;

public class BankingSystemUsingCSV {
	public static void main(String[] args) {
		String file = "C:\\Users\\rojin\\Downloads\\newAccounts.csv";
					
		ReadAccounts readAccounts = new ReadAccounts(file);
		Transaction transfer = new Transaction();
		
		LinkedList<Account> accounts = new LinkedList<>();
		LinkedList<String> firstNames = new LinkedList<>();
		LinkedList<String> lastNames = new LinkedList<>();
		LinkedList<Integer> accountList = new LinkedList<>();
		LinkedList<Integer> balanceList = new LinkedList<>();
		try {
			firstNames.addAll(readAccounts.getFirstNames());
			lastNames.addAll(readAccounts.getLastNames());
			accountList.addAll(readAccounts.getAccounts());
			balanceList.addAll(readAccounts.getBalances());		
		
		int accNum = accountList.size();
		for (int i = 0; i<accNum;i++) {
			String first = firstNames.get(i);
			String last = lastNames.get(i);
			int acc = accountList.get(i);
			int bal = balanceList.get(i);
			
			accounts.add(new Account(first, last, acc, bal));
		}
		// 9. Testing procedures
        // Example: deposit to the first account
        Account account1 = accounts.get(0);
        account1.deposit(250);
        System.out.println("Balance of account1 after deposit: " + account1.getBalance());

        // Example: withdraw from the second account
        Account account2 = accounts.get(1);
        account2.withdraw(100);
        System.out.println("Balance of account2 after withdrawal: " + account2.getBalance());

        // Example: transfer from the third account to the fourth account
        Account account3 = accounts.get(2);
        Account account4 = accounts.get(3);
        transfer.transfer(account3, account4, 200);
        System.out.println("Final balance of account3: " + account3.getBalance());
        System.out.println("Final balance of account4: " + account4.getBalance());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ReadAccounts{
	String url;
	
	public ReadAccounts(String url){
		this.url = url;
	}
	public LinkedList<String> getFirstNames() throws IOException{
		LinkedList<String> firstNames = new LinkedList<>();
		BufferedReader reader = new BufferedReader(new FileReader(url));
		String line;
		while((line=reader.readLine()) != null) {
			String[] data = line.split(",");
			firstNames.add(data[0]);
		}
		reader.close();
		return firstNames;
	}
	public LinkedList<String> getLastNames() throws IOException{
		LinkedList<String> lastNames = new LinkedList<>();
		BufferedReader reader = new BufferedReader(new FileReader(url));
		String line;
		while((line = reader.readLine())!=null) {
			String[] data = line.split(",");
			lastNames.add(data[1]);
		}
		reader.close();
		return lastNames;
	}
	public LinkedList<Integer> getAccounts() throws IOException{
		LinkedList<Integer> accounts = new LinkedList<>();
		BufferedReader reader = new BufferedReader(new FileReader(url));
		String line;
		while((line = reader.readLine())!=null) {
			String[] data = line.split(",");
			accounts.add(Integer.parseInt(data[2]));
		}
		reader.close();
		return accounts;
	}
	public LinkedList<Integer> getBalances() throws IOException{
		LinkedList<Integer> balances = new LinkedList<>();
		BufferedReader reader = new BufferedReader(new FileReader(url));
		String line;
		while((line = reader.readLine())!=null) {
			String[] data = line.split(",");
			balances.add(Integer.parseInt(data[3]));
		}
		reader.close();
		return balances;
	}
}
